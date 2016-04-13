:- ensure_loaded(['example_utils.pl', 'example_user.pl', 'example_accounts.pl']).

:- dynamic bugHyp/4.

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
                 "password1", "azerty", "000000", 
                 "password123"]).


% -- vulExists/2

% username is public
vulExists(Account, publicUser) :-
    userPublic(Account).

% email is public
vulExists(Account, publicEmail) :-
    accountEmail(Account, _, public).

% username same or similar to email -> know one know the other
vulExists(Account, userSimEmail) :-
    accountLogin(Account, Username, _),
    accountEmail(Account, EmailAcc, _),
    accountLogin(EmailAcc, Username, _).
vulExists(Account, userSimEmail) :-
    accountLogin(Account, Username, _),
    accountEmail(Account, EmailAcc, _),
    accountLogin(EmailAcc, Email, _),
    getStringBefore(Same, Username, "@"),
    getStringBefore(Same, Email, "@").

% password is a commonly used password, easily obtained through dictionary
% attacks
vulExists(Account, commonPw) :-
    accountLogin(Account, _, Password),
    commonlyUsedPws(CommonlyUsedPasswords),
    member(Password, CommonlyUsedPasswords).

% password contains name of user, can also be obtained through dictionary
% attacks (if name is known)
vulExists(Account, nameInPw) :-
    userInfo(firstname, First),
    userInfo(shortened, SFirst),
    userInfo(lastname, Last),
    accountLogin(Account, _, Password),
    (isSubstring(First, Password); isSubstring(SFirst, Password)),
    isSubstring(Last, Password).

% weak password, a strong password should have length > 8 chars, contains
% uppercase and lowercase letters, number as well as symbols (and ideally
% should not have a complete word)
vulExists(Account, weakPw) :-
    accountLogin(Account, _, Password),
    \+ fulfillReq(Password).

% password contains username for that account -> only one is required for
% attack
vulExists(Account, userInPw) :-
    accountLogin(Account, Username, Password),
    ((getStringBefore(User, Username, "@"), isSubstring(User, Password));
    isSubstring(Username, Password)).

% password should be change after a certain period of time
vulExists(Account, pwExpired) :-
    pwLastModified(Account, (Y, M, D)),
    today(TY, TM, TD),
    (TY - Y) * 365 + (TM - M) * 30 + TD - D > 180.

% for hypothetical analysis
vulExists(Account, VulID) :-
    bugHyp(Account, VulID, _, _).


% -- vulProperty/3
% describles consequences of each vulnerability
vulProperty(publicUser, username, known).
vulProperty(publicEmail, email, known).
vulProperty(commonPw, password, known).
vulProperty(nameInPw, password, known).
vulProperty(weakPw, password, vulnerable).
vulProperty(userInPw, password, fromUser).
vulProperty(pwExpired, password, known).
vulProperty(VulID, Field, Property) :-
    bugHyp(_, VulID, Field, Property).


%% Example for test database : Connections and Interactions %%


% -- accountConn/4

% same username
accountConn(Acc1, Acc2, username, same) :-
    accountLogin(Acc1, Username1, _),
    accountLogin(Acc2, Username2, _),
    Acc1 \== Acc2,
    getStringBefore(User1, Username1, "@"),
    getStringBefore(User2, Username2, "@"),
    User1 == User2.


% same password
accountConn(Acc1, Acc2, password, same) :-
    accountLogin(Acc1, _, Password),
    accountLogin(Acc2, _, Password),
    Acc1 \== Acc2.


% same email
accountConn(Acc1, Acc2, email, same) :-
    accountEmail(Acc1, RecAcc, _),
    accountEmail(Acc2, RecAcc, _),
    Acc1 \== Acc2.


% information for recovery public on another account
accountConn(Acc1, Acc2, Info, public) :-
    resetInfo(Acc1, InfoRequired, _),
    member(Info, InfoRequired),
    publicInfo(Acc2, PublicInfo),
    member(Info, PublicInfo),
    Acc1 \== Acc2.
    
