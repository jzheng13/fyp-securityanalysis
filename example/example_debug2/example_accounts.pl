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


% -- accountLogin/3
% list of login credentials
accountLogin(google1, "ce.paul0506@gmail.com", "cp85goog!3PW").
accountLogin(microsoft1, "ce.paul0506@outlook.com", "ChrisPaul0506").
accountLogin(apple1, "ce.paul0506@gmail.com", "ChrisPaul0506").
accountLogin(yahoo1, "ce.paul0506@yahoo.com", "chrispaul0506").
accountLogin(facebook1, "ce.paul0506@gmail.com", "adobe123").
accountLogin(steam1, "crazyp2017", "Acpwfaca2017?").


% -- pwLastModified/2
% sets date of password last modified
pwLastModified(google1, (2015, 6, 9)).
pwLastModified(microsoft1, (2016, 3, 4)).
pwLastModified(apple1, (2016, 3, 4)).
pwLastModified(yahoo1, (2016, 3, 4)).
pwLastModified(facebook1, (2016, 3, 4)).
pwLastModified(steam1, (2016, 3, 11)).


% -- singleSignOn/2
% list of accounts with sso authentication, maps symbol of account to symbol of
% sso account
singleSignOn(expedia1, facebook1).


% -- accountEmail/3
% list of linked emails of accounts and privacy setting
accountEmail(google1, microsoft1, private).
accountEmail(microsoft1, google1, private).
accountEmail(apple1, google1, private).
accountEmail(yahoo1, google1, private).
accountEmail(facebook1, google1, public).
accountEmail(expedia1, google1, private).
accountEmail(steam1, google1, private).

% -- userPublic/1
userPublic(steam1).


% -- publicInfo/2
% list of information publicly available for the different accounts
publicInfo(google1, [name]).
publicInfo(microsoft1, [name]).
publicInfo(apple1, []).
publicInfo(yahoo1, [name]).
publicInfo(facebook1, [name, birthday, current_city, hometown, workplace, university]).
publicInfo(expedia1, []).
publicInfo(steam1, []).


% -- privateInfo/2
% list of information available with access to account
privateInfo(google1, [birthday]).
privateInfo(microsoft1, [birthday]).
privateInfo(apple1, [name, birthday, current_city]).
privateInfo(yahoo1, [mobile, birthday]).
privateInfo(facebook1, []).
privateInfo(expedia1, [name, birthday, mobile, address]).
privateInfo(steam1, [email, location]).


% -- resetInfo/3
% for each account, information + account access required for password reset
resetInfo(google1, [username, email], microsoft1). 
resetInfo(microsoft1, [username, email], google1).
resetInfo(apple1, [email], google1).
resetInfo(apple1, [birthday, hometown, job], _).
resetInfo(yahoo1, [username, email], google1).
resetInfo(facebook1, [email], google1).
resetInfo(expedia1, [email], google1).
resetInfo(steam1, [username], google1).
resetInfo(steam1, [email], google1).
resetInfo(steam1, [mobile], google1).