%% Example for test database : Accounts %%


% -- hasAccount/3
% list of accounts of user, mapped to a symbol
hasAccount(google, "ce.paul0506@gmail.com", google1).
hasAccount(microsoft, "ce.paul0506@outlook.com", microsoft1).
hasAccount(apple, "ce.paul0506@gmail.com", apple1).
hasAccount(yahoo, "ce.paul0506@yahoo.com", yahoo1).
hasAccount(facebook, "ce.paul0506@gmail.com", facebook1).
hasAccount(expedia, "ce.paul0506@gmail.com", expedia1).
hasAccount(steam, "crazyp2017", steam1).
hasAccount(twitter, "ce.paul0506@gmail.com", twitter1).

hasAccount(google, "crazyp2017@gmail.com", google2).
hasAccount(reddit, "aclip4lyfe", reddit1).
hasAccount(facebook, "crazyp2017@gmail.com", facebook2).
hasAccount(wordpress, "crazypblog", wordpress1).
hasAccount(asos, "crazyp2017@gmail.com", asos1).
hasAccount(goodreads, "crazyp2017@gmail.com", goodreads1).
hasAccount(espngo, "WclipsW", espngo1).
hasAccount(twitchtv, "crazyp0085", twitchtv1).
hasAccount(goal, "crazyp2017@gmail.com", goal1).

% -- accountLogin/3
% list of login credentials
accountLogin(google1, "ce.paul0506@gmail.com", "cp85goog!3PW").
accountLogin(microsoft1, "ce.paul0506@outlook.com", "ChrisPaul0506").
accountLogin(apple1, "ce.paul0506@gmail.com", "ChrisPaul0506").
accountLogin(yahoo1, "ce.paul0506@yahoo.com", "chrispaul0506").
accountLogin(facebook1, "ce.paul0506@gmail.com", "adobe123").
accountLogin(steam1, "crazyp2017", "Acpwfaca2017?").
accountLogin(twitter1, "ce.paul0506@gmail.com", "adobe123").
accountLogin(twitter1, "cp0506", "adobe123").

accountLogin(google2, "crazyp2017@gmail.com", "justarandompassword").
accountLogin(reddit1, "aclip4lyfe", "password123").
accountLogin(facebook2, "crazyp2017@gmail.com", "crazyp0506").
accountLogin(wordpress1, "crazypblog", "cp2017blogpw").
accountLogin(wordpress1, "crazyp2017@gmail.com", "cp2017blogpw").
accountLogin(twitchtv1, "crazyp0085", "cptwitchpw").


% -- pwLastModified/2
% sets date of password last modified
pwLastModified(google1, (2016, 6, 5)).
pwLastModified(microsoft1, (2016, 5, 4)).
pwLastModified(apple1, (2016, 5, 4)).
pwLastModified(yahoo1, (2016, 5, 4)).
pwLastModified(facebook1, (2016, 5, 4)).
pwLastModified(steam1, (2016, 5, 11)).
pwLastModified(twitter1, (2016, 5, 4)).

pwLastModified(google2, (2016, 5, 11)).
pwLastModified(reddit1, (2016, 5, 11)).
pwLastModified(facebook2, (2016, 5, 11)).
pwLastModified(wordpress1, (2016, 5, 11)).
pwLastModified(twitchtv1, (2016, 5, 11)).


% -- pwContains/2
% sets information required to break password
pwContains(microsoft1, [name, birthday]).
pwContains(apple1, [name, birthday]).
pwContains(yahoo1, [name, birthday]).


% -- singleSignOn/2
% list of accounts with sso authentication, maps symbol of account to symbol of
% sso account
singleSignOn(expedia1, facebook1).

singleSignOn(asos1, google2).
singleSignOn(goodreads1, facebook2).
singleSignOn(espngo1, facebook2).
singleSignOn(twitchtv1, facebook2).
singleSignOn(goal1, facebook2).


% -- accountEmail/3
% list of linked emails of accounts and privacy setting
accountEmail(google1, microsoft1, private).
accountEmail(microsoft1, google1, private).
accountEmail(apple1, google1, private).
accountEmail(yahoo1, google1, private).
accountEmail(facebook1, google1, public).
accountEmail(expedia1, google1, private).
accountEmail(steam1, google1, private).
accountEmail(twitter1, google1, private).


% -- userPublic/1
userPublic(steam1).
userPublic(reddit1).
userPublic(wordpress1).
userPublic(twitchtv1).


% -- publicInfo/2
% list of information publicly available for the different accounts
publicInfo(google1, [name]).
publicInfo(microsoft1, [name]).
publicInfo(apple1, []).
publicInfo(yahoo1, [name]).
publicInfo(facebook1, [name, birthday, current_city, hometown, workplace, university]).
publicInfo(expedia1, []).
publicInfo(steam1, []).
publicInfo(twitter1, [name]).

publicInfo(google2, [name]).
publicInfo(reddit1, []).
publicInfo(facebook2, [name, gender]).
publicInfo(wordpress1, []).
publicInfo(asos1, []).
publicInfo(goodreads1, [name, books, location, fav_bauthors, fav_bgenres]).
publicInfo(espngo1, [name]).
publicInfo(twitchtv1, []).
publicInfo(goal1, []).


% -- privateInfo/2
% list of information available with access to account
privateInfo(google1, [birthday]).
privateInfo(microsoft1, [birthday]).
privateInfo(apple1, [name, birthday, current_city]).
privateInfo(yahoo1, [mobile, birthday]).
privateInfo(facebook1, []).
privateInfo(expedia1, [name, birthday, mobile, address]).
privateInfo(steam1, [email, location]).
privateInfo(twitter1, []).

privateInfo(google2, [birthday]).
privateInfo(reddit1, [friends]).
privateInfo(facebook2, [birthday]).
privateInfo(wordpress1, []).
privateInfo(asos1, [name, birthday, gender, location, address, card]).
privateInfo(goodreads1, [birthday]).
privateInfo(espngo1, [username, birthday, fav_teams, fav_sport]).
privateInfo(twitchtv1, []).
privateInfo(goal1, [birthday, location]).


% -- resetInfo/3
% for each account, information + account access required for password reset
resetInfo(google1, [username, email], microsoft1). 
resetInfo(microsoft1, [username, email], google1).
resetInfo(apple1, [email], google1).
resetInfo(apple1, [birthday, hometown, job], default).
resetInfo(yahoo1, [username, email], google1).
resetInfo(facebook1, [email], google1).
resetInfo(expedia1, [email], google1).
resetInfo(steam1, [username], google1).
resetInfo(steam1, [email], google1).
resetInfo(steam1, [mobile], google1).
resetInfo(twitter1, [username], google1).
resetInfo(twitter1, [email], google1).

resetInfo(google2, [username, email], google1).
resetInfo(reddit1, [username, email], google2).
resetInfo(facebook2, [email], google2).
resetInfo(wordpress1, [email], google2).
resetInfo(asos1, [email], google2).
resetInfo(goodreads1, [email], google2).
resetInfo(espngo1, [email], google2).
resetInfo(espngo1, [username], google2).
resetInfo(twitchtv1, [username], google2).
resetInfo(goal1, [email], google2).