:- load_files('example_utils.pl').

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

% -- public username
vulExists(Account, Username, publicUser) :-
    publicInfo(Account, Username, Info),
    member(username, Info).
vulExists(Account, Username, publicUser) :-
    accountEmail(Account, Username, _, Username, public).
vulProperty(publicUser, userKnown).


% -- commonly-used password
vulExists(Account, Username, commonPw) :-
    accountLogin(Account, Username, Password),
    commonlyUsedPws(CommonlyUsedPasswords),
    member(Password, CommonlyUsedPasswords).
vulProperty(commonPw, pwKnown).


% -- password contains name

vulExists(Account, Username, nameInPw) :-
    userInfo(firstname, First),
    userInfo(lastname, Last),
    append(First, Last, Name),
    accountLogin(Account, Username, Password),
    isSubstring(Name, Password).
vulProperty(nameInPw, weakPw).

% -- information known

vulExists(Account, Username, publicBdae) :-
    publicInfo(Account, Username, Info),
    member(birthday, Info).
vulProperty(publicBdae, bdaeKnown).

vulExists(Account, Username, publicMobile) :-
    publicInfo(Account, Username, Info),
    member(mobile, Info).
vulProperty(publicMobile, numKnown).

vulExists(Account, Username, publicLoc) :-
    publicInfo(Account, Username, Info),
    member(current_city, Info).
vulProperty(publicLoc, locKnown).

vulExists(Account, Username, publicHT) :-
    publicInfo(Account, Username, Info),
    member(hometown, Info).
vulProperty(publicHT, htKnown).

vulExists(Account, Username, publicWork) :-
    publicInfo(Account, Username, Info),
    member(workplace, Info).
vulProperty(publicWork, wpKnown).

vulExists(Account, Username, publicJob) :-
    publicInfo(Account, Username, Info),
    member(job, Info).
vulProperty(publicWork, jobKnown).

vulExists(Account, Username, publicAdd) :-
    publicInfo(Account, Username, Info),
    member(adress, Info).
vulProperty(publicWork, addKnown).



%% Example for test database : User %%


% -- Map variables to constants
userInfo(firstname, "Chris").
userInfo(lastname, "Paul").
userInfo(birthday, "6 May 1985").
userInfo(mobile, "07510537578").
userInfo(current_city, "Los Angeles, California").
userInfo(hometown, "Winston-Salem, North Carolina"). 
userInfo(workplace, "Los Angeles Clippers").
userInfo(job, "basketball player").
userInfo(address, "CA19005").



%% Example for test database : Accounts %%



% -- Google
hasAccount(google, "ce.paul0506@gmail.com", userAccess).
accountLogin(google, "ce.paul0506@gmail.com", "chrispaul").
accountEmail(google, "ce.paul0506@gmail.com", microsoft, "ce.paul0506@outlook.com", private).
publicInfo(google, "ce.paul0506@gmail.com", [name]).
privateInfo(google, "ce.paul0506@gmail.com", [birthday]).


% -- Microsoft
hasAccount(microsoft, "ce.paul0506@outlook.com", userAccess).
accountLogin(microsoft, "ce.paul0506@outlook.com", "ChrisPaul0506").
accountEmail(microsoft, "ce.paul0506@outlook.com", google, "ce.paul0506@gmail.com", private).
publicInfo(microsoft, "ce.paul0506@outlook.com", [name]).
privateInfo(microsoft, "ce.paul0506@outlook.com", [birthday]).



% -- Yahoo
hasAccount(yahoo, "ce.paul0506@yahoo.com", userAccess).
accountLogin(yahoo, "ce.paul0506@yahoo.com", "chrispaul0506").
accountEmail(yahoo, "ce.paul0506@yahoo.com", google, "ce.paul0506@gmail.com", private).
publicInfo(yahoo, "ce.paul0506@yahoo.com", [name]).
privateInfo(yahoo, "ce.paul0506@yahoo.com", [mobile, birthday]).


% -- Facebook
hasAccount(facebook, "ce.paul0506@gmail.com", userAccess).
accountLogin(facebook, "ce.paul0506@gmail.com", "adobe123").
accountEmail(facebook, "ce.paul0506@gmail.com", google, "ce.paul0506@gmail.com", public).
publicInfo(facebook, "ce.paul0506@gmail.com", [name, birthday, current_city, hometown, workplace, university]).
privateInfo(facebook, "ce.paul0506@gmail.com", []).


% -- Twitter
hasAccount(twitter, "ce.paul0506@gmail.com", userAccess).
accountLogin(twitter, "ce.paul0506@gmail.com", "adobe123").
accountLogin(twitter, "cp0506", "adobe123").
accountEmail(twitter, "ce.paul0506@gmail.com", google, "ce.paul0506@gmail.com", private).
publicInfo(twitter, "ce.paul0506@gmail.com", [name]).
privateInfo(twitter, "ce.paul0506@gmail.com", []).


% -- LinkedIn
hasAccount(linkedin, "ce.paul0506@gmail.com", userAccess).
accountLogin(linkedin, "ce.paul0506@gmail.com", "password123").
accountEmail(linkedin, "ce.paul0506@gmail.com", google, "ce.paul0506@gmail.com", public).
publicInfo(linkedin, "ce.paul0506@gmail.com", [name, workplace, university]).
privateInfo(linkedin, "ce.paul0506@gmail.com", []).


% -- Dropbox
hasAccount(dropbox, "ce.paul0506@gmail.com", userAccess).
accountLogin(dropbox, "ce.paul0506@gmail.com", "password123").
accountEmail(dropbox, "ce.paul0506@gmail.com", google, "ce.paul0506@gmail.com", private).
publicInfo(dropbox, "ce.paul0506@gmail.com", [name]).
privateInfo(dropbox, "ce.paul0506@gmail.com", []).


% -- eBay
hasAccount(ebay, "ce.paul0506@gmail.com", userAccess).
accountLogin(ebay, "ce.paul0506@gmail.com", "chrispaul123").
accountLogin(ebay, "cp0506", "chrispaul123").
accountEmail(ebay, "ce.paul0506@gmail.com", google, "ce.paul0506@gmail.com", private).
publicInfo(ebay, "ce.paul0506@gmail.com", []).
privateInfo(ebay, "ce.paul0506@gmail.com", [name, mobile, address]).


% -- Amazon
hasAccount(amazon, "ce.paul0506@gmail.com", userAccess).
accountLogin(amazon, "ce.paul0506@gmail.com", "chrispaul0506").
accountEmail(amazon, "ce.paul0506@gmail.com", google, "ce.paul0506@gmail.com", private).
publicInfo(amazon, "ce.paul0506@gmail.com", []).
privateInfo(amazon, "ce.paul0506@gmail.com", [name, mobile, address]).


% -- Airbnb
hasAccount(airbnb, "ce.paul0506@gmail.com", userAccess).
singleSignOn(airbnb, "ce.paul0506@gmail.com", google, "ce.paul0506@gmail.com").
accountEmail(airbnb, "ce.paul0506@gmail.com", google, "ce.paul0506@gmail.com", private).
publicInfo(airbnb, "ce.paul0506@gmail.com", [firstname]).
privateInfo(airbnb, "ce.paul0506@gmail.com", []).


% -- Expedia
hasAccount(expedia, "ce.paul0506@gmail.com", userAccess).
singleSignOn(expedia, "ce.paul0506@gmail.com", facebook, "ce.paul0506@gmail.com").
accountEmail(expedia, "ce.paul0506@gmail.com", google, "ce.paul0506@gmail.com", private).
publicInfo(expedia, "ce.paul0506@gmail.com", []).
privateInfo(expedia, "ce.paul0506@gmail.com", [name, birthday, mobile, address]).


% -- TripAdvisor
hasAccount(tripadvisor, "ce.paul0506@gmail.com", userAccess).
singleSignOn(tripadvisor, "ce.paul0506@gmail.com", google, "ce.paul0506@gmail.com").
accountEmail(tripadvisor, "ce.paul0506@gmail.com", google, "ce.paul0506@gmail.com", private).
publicInfo(tripadvisor, "ce.paul0506@gmail.com", [firstname]).
privateInfo(tripadvisor, "ce.paul0506@gmail.com", [name, current_city]).


% -- Apple
hasAccount(apple, "ce.paul0506@gmail.com", userAccess).
accountLogin(apple, "ce.paul0506@gmail.com", "ChrisPaul0506").
accountEmail(apple, "ce.paul0506@gmail.com", google, "ce.paul0506@gmail.com", private).
publicInfo(apple, "ce.paul0506@gmail.com", []).
privateInfo(apple, "ce.paul0506@gmail.com", [name, birthday, current_city]).


% -- Yelp
hasAccount(yelp, "ce.paul0506@gmail.com", userAccess).
accountLogin(yelp, "ce.paul0506@gmail.com", "password123").
accountEmail(yelp, "ce.paul0506@gmail.com", google, "ce.paul0506@gmail.com", private).
publicInfo(yelp, "ce.paul0506@gmail.com", [firstname, age, current_city]).
privateInfo(yelp, "ce.paul0506@gmail.com", [name, birthday]).


% -- Tumblr
hasAccount(tumblr, "ce.paul0506@gmail.com", userAccess).
accountLogin(tumblr, "ce.paul0506@gmail.com", "chrispaul0506").
accountEmail(tumblr, "ce.paul0506@gmail.com", google, "ce.paul0506@gmail.com", private).
publicInfo(tumblr, "ce.paul0506@gmail.com", []).
privateInfo(tumblr, "ce.paul0506@gmail.com", [name]).


% -- Instagram
hasAccount(instagram, "ce.paul0506", userAccess).
accountLogin(instagram, "ce.paul0506", "adobe123").
accountEmail(instagram, "ce.paul0506", google, "ce.paul0506@gmail.com", private).
publicInfo(instagram, "ce.paul0506", [name]).
privateInfo(instagram, "ce.paul0506", []).


% -- Pinterest
hasAccount(pinterest, "ce.paul0506@gmail.com", userAccess).
singleSignOn(pinterest, "ce.paul0506@gmail.com", facebook, "ce.paul0506@gmail.com").
accountEmail(pinterest, "ce.paul0506@gmail.com", google, "ce.paul0506@gmail.com", private).
publicInfo(pinterest, "ce.paul0506@gmail.com", [name, birthday, current_city, hometown, workplace, university]).
privateInfo(pinterest, "ce.paul0506@gmail.com", []).


% -- FourSquare
hasAccount(foursquare, "ce.paul0506@gmail.com", userAccess).
singleSignOn(foursquare, "ce.paul0506@gmail.com", facebook, "ce.paul0506@gmail.com").
accountEmail(foursquare, "ce.paul0506@gmail.com", google, "ce.paul0506@gmail.com", private).
publicInfo(foursquare, "ce.paul0506@gmail.com", [name, birthday, current_city, hometown, workplace, university]).
privateInfo(foursquare, "ce.paul0506@gmail.com", []).


% -- Quora
hasAccount(quora, "ce.paul0506@gmail.com", userAccess).
accountLogin(quora, "ce.paul0506@gmail.com", "password123").
singleSignOn(quora, "ce.paul0506@gmail.com", facebook, "ce.paul0506@gmail.com").
accountEmail(quora, "ce.paul0506@gmail.com", google, "ce.paul0506@gmail.com", private).
publicInfo(quora, "ce.paul0506@gmail.com", [name, birthday, current_city, hometown, workplace, university]).
privateInfo(quora, "ce.paul0506@gmail.com", []).


% -- Stack Overflow
hasAccount(stackoverflow, "ce.paul0506@gmail.com", userAccess).
singleSignOn(stackoverflow, "ce.paul0506@gmail.com", facebook, "ce.paul0506@gmail.com").
accountEmail(stackoverflow, "ce.paul0506@gmail.com", google, "ce.paul0506@gmail.com", private).
publicInfo(stackoverflow, "ce.paul0506@gmail.com", [name, birthday, current_city, hometown, workplace, university]).
privateInfo(stackoverflow, "ce.paul0506@gmail.com", []).



%% Example for test database : Password Recovery Information %%



recoveryInfo(google, "ce.paul0506@gmail.com", [username, email], microsoft, "ce.paul0506@outlook.com"). 

recoveryInfo(microsoft, "ce.paul0506@outlook.com", [username, email], google, "ce.paul0506@gmail.com").

recoveryInfo(yahoo, "ce.paul0506@yahoo.com", [username, email], google, "ce.paul0506@gmail.com").

recoveryInfo(facebook, "ce.paul0506@gmail.com", [email], google, "ce.paul0506@gmail.com").

recoveryInfo(twitter, "ce.paul0506@gmail.com", [username], google, "ce.paul0506@gmail.com").
recoveryInfo(twitter, "ce.paul0506@gmail.com", [email], google, "ce.paul0506@gmail.com").

recoveryInfo(linkedin, "ce.paul0506@gmail.com", [email], google, "ce.paul0506@gmail.com").

recoveryInfo(dropbox, "ce.paul0506@gmail.com", [email], google, "ce.paul0506@gmail.com").

recoveryInfo(ebay, "ce.paul0506@gmail.com", [email], google, "ce.paul0506@gmail.com").

recoveryInfo(amazon, "ce.paul0506@gmail.com", [email], google, "ce.paul0506@gmail.com").

recoveryInfo(airbnb, "ce.paul0506@gmail.com", [email], google, "ce.paul0506@gmail.com").

recoveryInfo(expedia, "ce.paul0506@gmail.com", [email], google, "ce.paul0506@gmail.com").

recoveryInfo(tripadvisor, "ce.paul0506@gmail.com", [email], google, "ce.paul0506@gmail.com").

recoveryInfo(apple, "ce.paul0506@gmail.com", [email], google, "ce.paul0506@gmail.com").
recoveryInfo(apple, "ce.paul0506@gmail.com", [birthday, hometown, job], _, _).

recoveryInfo(yelp, "ce.paul0506@gmail.com", [email], google, "ce.paul0506@gmail.com").

recoveryInfo(tumblr, "ce.paul0506@gmail.com", [email], google, "ce.paul0506@gmail.com").

recoveryInfo(instagram, "ce.paul0506@gmail.com", [email], google, "ce.paul0506@gmail.com").

recoveryInfo(pinterest, "ce.paul0506@gmail.com", [email], google, "ce.paul0506@gmail.com").

recoveryInfo(foursquare, "ce.paul0506@gmail.com", [email], google, "ce.paul0506@gmail.com").

recoveryInfo(quora, "ce.paul0506@gmail.com", [email], google, "ce.paul0506@gmail.com").

recoveryInfo(stackoverflow, "ce.paul0506@gmail.com", [email], google, "ce.paul0506@gmail.com").


%% Example for test database : Connections %%



% -- same username

accountConn(Acc1, Username1, Acc2, Username2, sameUser) :-
    accountLogin(Acc1, Username1, _),
    accountLogin(Acc2, Username2, _),
    getStringBefore(User, Username1, "@"),
    getStringBefore(User, Username2, "@").


% -- same password

accountConn(Acc1, Username1, Acc2, Username2, samePw) :-
    accountLogin(Acc1, Username1, Password),
    accountLogin(Acc2, Username2, Password).


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