%% Example for test database : Accounts %%


% -- hasAccount/3
% list of accounts of user
hasAccount(google, "ce.paul0506@gmail.com", userAccess).
hasAccount(microsoft, "ce.paul0506@outlook.com", userAccess).
hasAccount(apple, "ce.paul0506@gmail.com", userAccess).
hasAccount(yahoo, "ce.paul0506@yahoo.com", userAccess).
hasAccount(facebook, "ce.paul0506@gmail.com", userAccess).
hasAccount(twitter, "ce.paul0506@gmail.com", userAccess).
hasAccount(linkedin, "ce.paul0506@gmail.com", userAccess).
hasAccount(dropbox, "ce.paul0506@gmail.com", userAccess).
hasAccount(ebay, "ce.paul0506@gmail.com", userAccess).
hasAccount(amazon, "ce.paul0506@gmail.com", userAccess).
hasAccount(airbnb, "ce.paul0506@gmail.com", userAccess).
hasAccount(expedia, "ce.paul0506@gmail.com", userAccess).
hasAccount(tripadvisor, "ce.paul0506@gmail.com", userAccess).
hasAccount(yelp, "ce.paul0506@gmail.com", userAccess).
hasAccount(tumblr, "ce.paul0506@gmail.com", userAccess).
hasAccount(instagram, "ce.paul0506", userAccess).
hasAccount(pinterest, "ce.paul0506@gmail.com", userAccess).
hasAccount(foursquare, "ce.paul0506@gmail.com", userAccess).
hasAccount(quora, "ce.paul0506@gmail.com", userAccess).
hasAccount(stackoverflow, "ce.paul0506@gmail.com", userAccess).


% -- accountLogin/3
% list of login credentials
accountLogin(google, "ce.paul0506@gmail.com", "chrispaul").
accountLogin(microsoft, "ce.paul0506@outlook.com", "ChrisPaul0506").
accountLogin(apple, "ce.paul0506@gmail.com", "ChrisPaul0506").
accountLogin(yahoo, "ce.paul0506@yahoo.com", "chrispaul0506").
accountLogin(facebook, "ce.paul0506@gmail.com", "adobe123").
accountLogin(twitter, "ce.paul0506@gmail.com", "adobe123").
accountLogin(twitter, "cp0506", "adobe123").
accountLogin(linkedin, "ce.paul0506@gmail.com", "password123").
accountLogin(dropbox, "ce.paul0506@gmail.com", "password123").
accountLogin(ebay, "ce.paul0506@gmail.com", "chrispaul123").
accountLogin(ebay, "cp0506", "chrispaul123").
accountLogin(amazon, "ce.paul0506@gmail.com", "chrispaul0506").
accountLogin(yelp, "ce.paul0506@gmail.com", "password123").
accountLogin(tumblr, "ce.paul0506@gmail.com", "chrispaul0506").
accountLogin(instagram, "ce.paul0506", "adobe123").
accountLogin(quora, "ce.paul0506@gmail.com", "password123").


% -- singleSignOn/4
% list of accounts with sso authentication
singleSignOn(airbnb, "ce.paul0506@gmail.com", google, "ce.paul0506@gmail.com").
singleSignOn(expedia, "ce.paul0506@gmail.com", facebook, "ce.paul0506@gmail.com").
singleSignOn(tripadvisor, "ce.paul0506@gmail.com", google, "ce.paul0506@gmail.com").
singleSignOn(pinterest, "ce.paul0506@gmail.com", facebook, "ce.paul0506@gmail.com").
singleSignOn(foursquare, "ce.paul0506@gmail.com", facebook, "ce.paul0506@gmail.com").
singleSignOn(quora, "ce.paul0506@gmail.com", facebook, "ce.paul0506@gmail.com").
singleSignOn(stackoverflow, "ce.paul0506@gmail.com", facebook, "ce.paul0506@gmail.com").


% -- accountEmail/5
% list of linked emails of accounts and privacy setting
accountEmail(google, "ce.paul0506@gmail.com", microsoft, "ce.paul0506@outlook.com", private).
accountEmail(microsoft, "ce.paul0506@outlook.com", google, "ce.paul0506@gmail.com", private).
accountEmail(apple, "ce.paul0506@gmail.com", google, "ce.paul0506@gmail.com", private).
accountEmail(yahoo, "ce.paul0506@yahoo.com", google, "ce.paul0506@gmail.com", private).
accountEmail(facebook, "ce.paul0506@gmail.com", google, "ce.paul0506@gmail.com", public).
accountEmail(twitter, "ce.paul0506@gmail.com", google, "ce.paul0506@gmail.com", private).
accountEmail(linkedin, "ce.paul0506@gmail.com", google, "ce.paul0506@gmail.com", public).
accountEmail(dropbox, "ce.paul0506@gmail.com", google, "ce.paul0506@gmail.com", private).
accountEmail(ebay, "ce.paul0506@gmail.com", google, "ce.paul0506@gmail.com", private).
accountEmail(amazon, "ce.paul0506@gmail.com", google, "ce.paul0506@gmail.com", private).
accountEmail(airbnb, "ce.paul0506@gmail.com", google, "ce.paul0506@gmail.com", private).
accountEmail(expedia, "ce.paul0506@gmail.com", google, "ce.paul0506@gmail.com", private).
accountEmail(tripadvisor, "ce.paul0506@gmail.com", google, "ce.paul0506@gmail.com", private).
accountEmail(yelp, "ce.paul0506@gmail.com", google, "ce.paul0506@gmail.com", private).
accountEmail(tumblr, "ce.paul0506@gmail.com", google, "ce.paul0506@gmail.com", private).
accountEmail(instagram, "ce.paul0506", google, "ce.paul0506@gmail.com", private).
accountEmail(pinterest, "ce.paul0506@gmail.com", google, "ce.paul0506@gmail.com", private).
accountEmail(quora, "ce.paul0506@gmail.com", google, "ce.paul0506@gmail.com", private).
accountEmail(foursquare, "ce.paul0506@gmail.com", google, "ce.paul0506@gmail.com", private).
accountEmail(stackoverflow, "ce.paul0506@gmail.com", google, "ce.paul0506@gmail.com", private).


% -- publicInfo/3
% list of information publicly available for the different accounts
publicInfo(google, "ce.paul0506@gmail.com", [name]).
publicInfo(microsoft, "ce.paul0506@outlook.com", [name]).
publicInfo(apple, "ce.paul0506@gmail.com", []).
publicInfo(yahoo, "ce.paul0506@yahoo.com", [name]).
publicInfo(facebook, "ce.paul0506@gmail.com", [name, birthday, current_city, hometown, workplace, university]).
publicInfo(twitter, "ce.paul0506@gmail.com", [name]).
publicInfo(linkedin, "ce.paul0506@gmail.com", [name, workplace, university]).
publicInfo(dropbox, "ce.paul0506@gmail.com", [name]).
publicInfo(ebay, "ce.paul0506@gmail.com", []).
publicInfo(amazon, "ce.paul0506@gmail.com", []).
publicInfo(airbnb, "ce.paul0506@gmail.com", [firstname]).
publicInfo(expedia, "ce.paul0506@gmail.com", []).
publicInfo(tripadvisor, "ce.paul0506@gmail.com", [firstname]).
publicInfo(yelp, "ce.paul0506@gmail.com", [firstname, age, current_city]).
publicInfo(tumblr, "ce.paul0506@gmail.com", []).
publicInfo(instagram, "ce.paul0506", [name]).
publicInfo(pinterest, "ce.paul0506@gmail.com", [name, birthday, current_city, hometown, workplace, university]).
publicInfo(foursquare, "ce.paul0506@gmail.com", [name, birthday, current_city, hometown, workplace, university]).
publicInfo(quora, "ce.paul0506@gmail.com", [name, birthday, current_city, hometown, workplace, university]).
publicInfo(stackoverflow, "ce.paul0506@gmail.com", [name, birthday, current_city, hometown, workplace, university]).


% -- privateInfo/3
% list of information available with access to account
privateInfo(google, "ce.paul0506@gmail.com", [birthday]).
privateInfo(microsoft, "ce.paul0506@outlook.com", [birthday]).
privateInfo(apple, "ce.paul0506@gmail.com", [name, birthday, current_city]).
privateInfo(yahoo, "ce.paul0506@yahoo.com", [mobile, birthday]).
privateInfo(facebook, "ce.paul0506@gmail.com", []).
privateInfo(twitter, "ce.paul0506@gmail.com", []).
privateInfo(linkedin, "ce.paul0506@gmail.com", []).
privateInfo(dropbox, "ce.paul0506@gmail.com", []).
privateInfo(ebay, "ce.paul0506@gmail.com", [name, mobile, address]).
privateInfo(amazon, "ce.paul0506@gmail.com", [name, mobile, address]).
privateInfo(airbnb, "ce.paul0506@gmail.com", []).
privateInfo(expedia, "ce.paul0506@gmail.com", [name, birthday, mobile, address]).
privateInfo(tripadvisor, "ce.paul0506@gmail.com", [name, current_city]).
privateInfo(yelp, "ce.paul0506@gmail.com", [name, birthday]).
privateInfo(tumblr, "ce.paul0506@gmail.com", [name]).
privateInfo(instagram, "ce.paul0506", []).
privateInfo(pinterest, "ce.paul0506@gmail.com", []).
privateInfo(foursquare, "ce.paul0506@gmail.com", []).
privateInfo(quora, "ce.paul0506@gmail.com", []).
privateInfo(stackoverflow, "ce.paul0506@gmail.com", []).



% -- recoveryInfo/5
% for each account, information + account access required for password reset
recoveryInfo(google, "ce.paul0506@gmail.com", [username, email], microsoft, "ce.paul0506@outlook.com"). 
recoveryInfo(microsoft, "ce.paul0506@outlook.com", [username, email], google, "ce.paul0506@gmail.com").
recoveryInfo(apple, "ce.paul0506@gmail.com", [email], google, "ce.paul0506@gmail.com").
recoveryInfo(apple, "ce.paul0506@gmail.com", [birthday, hometown, job], _, _).
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
recoveryInfo(yelp, "ce.paul0506@gmail.com", [email], google, "ce.paul0506@gmail.com").
recoveryInfo(tumblr, "ce.paul0506@gmail.com", [email], google, "ce.paul0506@gmail.com").
recoveryInfo(instagram, "ce.paul0506@gmail.com", [email], google, "ce.paul0506@gmail.com").
recoveryInfo(pinterest, "ce.paul0506@gmail.com", [email], google, "ce.paul0506@gmail.com").
recoveryInfo(foursquare, "ce.paul0506@gmail.com", [email], google, "ce.paul0506@gmail.com").
recoveryInfo(quora, "ce.paul0506@gmail.com", [email], google, "ce.paul0506@gmail.com").
recoveryInfo(stackoverflow, "ce.paul0506@gmail.com", [email], google, "ce.paul0506@gmail.com").
