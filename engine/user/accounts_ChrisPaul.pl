%% ACCOUNTS INFO: Chris Paul

hasAccount(google, "ce.paul0506@gmail.com", google1).
hasAccount(microsoft, "ce.paul0506@outlook.com", microsoft1).
hasAccount(apple, "ce.paul0506@gmail.com", apple1).
hasAccount(facebook, "ce.paul0506@gmail.com", facebook1).
hasAccount(expedia, "ce.paul0506@gmail.com", expedia1).
hasAccount(steam, "crazyp2017", steam1).
hasAccount(twitter, "cp0506", twitter1).
hasAccount(twitter, "ce.paul0506@gmail.com", twitter1).
hasAccount(apple, "ce.paul0506@gmail.com", apple1).
hasAccount(steam, "crazyp2017", steam1).
hasAccount(steam, "crazyp2017", steam1).


accountLogin(google1, "ce.paul0506@gmail.com", "cp85goog!3PW").
accountLogin(microsoft1, "ce.paul0506@outlook.com", "ChrisPaul0506").
accountLogin(apple1, "ce.paul0506@gmail.com", "ChrisPaul0506").
accountLogin(facebook1, "ce.paul0506@gmail.com", "adobe123").
accountLogin(expedia1, "ce.paul0506@gmail.com", "Acpwfaca2017?").
accountLogin(steam1, "crazyp2017", "adobe123").
accountLogin(twitter1, "cp0506", "adobe123").
accountLogin(twitter1, "ce.paul0506@gmail.com", "adobe123").
accountLogin(apple1, "ce.paul0506@gmail.com", "ChrisPaul0506").
accountLogin(steam1, "crazyp2017", "adobe123").
accountLogin(steam1, "crazyp2017", "adobe123").

pwLastModified(google1, (2015, 6, 9)).
pwLastModified(microsoft1, (2016, 3, 4)).
pwLastModified(apple1, (2016, 3, 4)).
pwLastModified(facebook1, (2016, 3, 4)).
pwLastModified(expedia1, (2016, 3, 4)).
pwLastModified(steam1, (2016, 3, 4)).
pwLastModified(twitter1, (2016, 3, 4)).
pwLastModified(twitter1, (2016, 3, 4)).
pwLastModified(apple1, (2016, 3, 4)).
pwLastModified(steam1, (2016, 3, 4)).
pwLastModified(steam1, (2016, 3, 4)).
singleSignOn("google1, ").
singleSignOn("microsoft1, ").
singleSignOn("apple1, ").
singleSignOn("facebook1, ").
singleSignOn("expedia1, "facebook1).
singleSignOn("steam1, ").
singleSignOn("twitter1, ").
singleSignOn("twitter1, ").
singleSignOn("apple1, ").
singleSignOn("steam1, ").
singleSignOn("steam1, ").
accountEmail(google1, microsoft1, private).
accountEmail(microsoft1, google1, private).
accountEmail(apple1, google1, private).
accountEmail(facebook1, google1, public).
accountEmail(expedia1, google1, private).
accountEmail(steam1, google1, private).
accountEmail(twitter1, google1, private).
accountEmail(twitter1, google1, private).
accountEmail(apple1, google1, private).
accountEmail(steam1, google1, private).
accountEmail(steam1, google1, private).
userPublic(steam1).
userPublic(twitter1).
userPublic(steam1).
userPublic(steam1).
publicInfo(google1, [name]).
publicInfo(microsoft1, [name]).
publicInfo(apple1, []).
publicInfo(facebook1, [name, birthday, currentCity]).
publicInfo(expedia1, []).
publicInfo(steam1, []).
publicInfo(twitter1, [name]).
publicInfo(twitter1, [name]).
publicInfo(apple1, []).
publicInfo(steam1, []).
publicInfo(steam1, []).
privateInfo(google1, [birthday]).
privateInfo(microsoft1, [birthday]).
privateInfo(apple1, [name, birthday, currentCity]).
privateInfo(facebook1, []).
privateInfo(expedia1, [name, birthday, currentCity]).
privateInfo(steam1, []).
privateInfo(twitter1, [currentCity]).
privateInfo(twitter1, []).
privateInfo(apple1, [name, birthday, currentCity]).
privateInfo(steam1, []).
privateInfo(steam1, []).
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
