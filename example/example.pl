:- load_files(['example_utils.pl', 'example_user.pl', 'example_accounts.pl']).

%% Example for test database : Vulnerabilities %%

% -- List of commonly used passwords 2013-15, from SplashData 

commonlyUsedPws(["123456", "password", "12345", 
                 "12345678", "qwerty", "123456789",
				 "1234", "baseball", "dragon",
                 "football", "1234567", "monkey",
                 "letmein", "abc123", "111111",
                 "mustang", "access", "shadow",
                 "master", "michael", "superman", 
                 "696969", "123123", "batman",
                 "trustno1", "welcome", "1234567890",
                 "1qaz2wsx", "login", "princess",
                 "qwertyuiop", "solo", "passw0rd",
                 "starwars", "iloveyou", "adobe123",
                 "admin", "photoshop", "sunshine",
                 "password1", "azerty", "000000"]).


% -- vulExists/3

% username is public
vulExists(Account, Username, publicUser) :-
    publicInfo(Account, Username, Info),
    member(username, Info).
vulExists(Account, Username, publicUser) :-
    accountEmail(Account, Username, _, Username, public).


% password is a commonly used password, easily obtained through dictionary
% attacks
vulExists(Account, Username, commonPw) :-
    accountLogin(Account, Username, Password),
    commonlyUsedPws(CommonlyUsedPasswords),
    member(Password, CommonlyUsedPasswords).


% password contains name of user, can also be obtained through dictionary
% attacks (if name is known)
vulExists(Account, Username, nameInPw) :-
    userInfo(firstname, First),
    userInfo(shortened, SFirst),
    userInfo(lastname, Last),
    accountLogin(Account, Username, Password),
    (isSubstring(First, Password); isSubstring(SFirst, Password),
    isSubstring(Last, Password).


% password contains username for that account -> only one is required for
% attack
vulExists(Account, Username, userInPw) :-
    accountLogin(Account, Username, Password),
    ((getStringBefore(User, Username, "@"), isSubstring(User, Password));
    isSubstring(Username, Password)).


% weak password, a strong password should have length > 8 chars, contains
% uppercase and lowercase letters, number as well as symbols (and ideally
% should not have a complete word)
vulExists(Account, Username, weakPw) :-
    accountLogin(Account, Username, Password,
    fulfillReq(Password).




%% Example for test database : Connections %%



% -- same username

accountConn(Acc1, Username1, Acc2, Username2, sameUser) :-
    accountLogin(Acc1, Username1, _),
    accountLogin(Acc2, Username2, _),
    Acc1 \== Acc2,
    getStringBefore(User, Username1, "@"),
    getStringBefore(User, Username2, "@").


% -- same password

accountConn(Acc1, Username1, Acc2, Username2, samePw) :-
    accountLogin(Acc1, Username1, Password),
    accountLogin(Acc2, Username2, Password),
    Acc1 \== Acc2.


% -- recovery

accountConn(Acc1, Username1, RecAcc, RecEmail, recovery) :-
    accountLogin(Acc1, Username1, _),
    accountEmail(Acc1, Username1, RecAcc, RecEmail, _).


%% Example for test database : Interactions %%



hasAccount(Account, Username, attackerAccess) :-
    vulExists(Account, Username, Id1),
    vulProperty(Id1, userKnown),
    vulExists(Account, Username, Id2),
    vulProperty(Id2, pwKnown).



%% Example for test database : Security Policy %%



allow(_, _, userAccess).


policyViolation(Account, Username, Access) :-
    hasAccount(Account, Username, Access),
    \+ allow(Account, Username, Access).