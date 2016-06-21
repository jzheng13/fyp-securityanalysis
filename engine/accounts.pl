%% ACCOUNTS INFO: Chris Paul

hasAccount(google, "ce.paul0506@gmail.com", google1).
hasAccount(microsoft, "ce.paul0506@outlook.com", microsoft1).
hasAccount(apple, "ce.paul0506@gmail.com", apple1).
hasAccount(facebook, "ce.paul0506@gmail.com", facebook1).
hasAccount(expedia, "ce.paul0506@gmail.com", expedia1).
hasAccount(steam, "crazyp2017", steam1).
hasAccount(twitter, "cp0506", twitter1).
hasAccount(twitter, "ce.paul0506@gmail.com", twitter1).


accountLogin(google1, "ce.paul0506@gmail.com", "cp85goog!3PW").
accountLogin(microsoft1, "ce.paul0506@outlook.com", "ChrisPaul0506").
accountLogin(apple1, "ce.paul0506@gmail.com", "ChrisPaul0506").
accountLogin(facebook1, "ce.paul0506@gmail.com", "adobe123").
accountLogin(expedia1, "ce.paul0506@gmail.com", "").
accountLogin(steam1, "crazyp2017", "adobe123").
accountLogin(twitter1, "cp0506", "adobe123").
accountLogin(twitter1, "ce.paul0506@gmail.com", "adobe123").


pwLastModified(google1, (2016, 05, 09)).
pwLastModified(microsoft1, (2016, 05, 04)).
pwLastModified(apple1, (2016, 05, 04)).
pwLastModified(facebook1, (2016, 05, 04)).
pwLastModified(expedia1, (2016, 05, 04)).
pwLastModified(steam1, (2016, 05, 04)).
pwLastModified(twitter1, (2016, 05, 04)).
pwLastModified(twitter1, (2016, 05, 04)).

pwContains(microsoft1, [name, birthday]).
pwContains(apple1, [name, birthday]).

singleSignOn(expedia1, facebook1).

accountEmail(google1, microsoft1, private).
accountEmail(microsoft1, google1, private).
accountEmail(apple1, google1, private).
accountEmail(facebook1, google1, public).
accountEmail(expedia1, google1, private).
accountEmail(steam1, google1, private).
accountEmail(twitter1, google1, private).

userPublic(steam1).
userPublic(twitter1).

publicInfo(google1, [name]).
publicInfo(microsoft1, [name]).
publicInfo(apple1, []).
publicInfo(facebook1, [name, birthday, currentCity]).
publicInfo(expedia1, []).
publicInfo(steam1, []).
publicInfo(twitter1, [name]).

privateInfo(google1, [birthday]).
privateInfo(microsoft1, [birthday]).
privateInfo(apple1, [name, birthday, currentCity]).
privateInfo(facebook1, []).
privateInfo(expedia1, [name, birthday, currentCity]).
privateInfo(steam1, []).
privateInfo(twitter1, [currentCity]).

resetInfo(google1, [username, email], microsoft1).
resetInfo(microsoft1, [username, email], google1).
resetInfo(apple1, [email], google1).
resetInfo(facebook1, [email], google1).
resetInfo(expedia1, [email], google1).
resetInfo(steam1, [username], google1).
resetInfo(twitter1, [username], google1).
resetInfo(twitter1, [email], google1).
resetInfo(apple1, [birthday, hometown, job], default).
resetInfo(steam1, [email], google1).
resetInfo(steam1, [mobile], google1).
