%% Example for test database : Accounts %%


% -- allAccounts/1
allAccounts(L) :-
    findall(Account, hasAccount(_, _, Account), L).


% -- hasAccount/3
% list of accounts of user, mapped to a symbol
hasAccount(google, "ce.paul0506@gmail.com", google1).
hasAccount(microsoft, "ce.paul0506@outlook.com", microsoft1).
hasAccount(apple, "ce.paul0506@gmail.com", apple1).
hasAccount(yahoo, "ce.paul0506@yahoo.com", yahoo1).
hasAccount(facebook, "ce.paul0506@gmail.com", facebook1).
hasAccount(twitter, "ce.paul0506@gmail.com", twitter1).
hasAccount(linkedin, "ce.paul0506@gmail.com", linkedin1).
hasAccount(dropbox, "ce.paul0506@gmail.com", dropbox1).
hasAccount(ebay, "ce.paul0506@gmail.com", ebay1).
hasAccount(amazon, "ce.paul0506@gmail.com", amazon1).
hasAccount(airbnb, "ce.paul0506@gmail.com", airbnb1).
hasAccount(expedia, "ce.paul0506@gmail.com", expedia1).
hasAccount(tripadvisor, "ce.paul0506@gmail.com", tripadvisor1).
hasAccount(yelp, "ce.paul0506@gmail.com", yelp1).
hasAccount(tumblr, "ce.paul0506@gmail.com", tumblr1).
hasAccount(instagram, "ce.paul0506", instagram1).
hasAccount(pinterest, "ce.paul0506@gmail.com", pinterest1).
hasAccount(foursquare, "ce.paul0506@gmail.com", foursquare1).
hasAccount(quora, "ce.paul0506@gmail.com", quora1).
hasAccount(stackoverflow, "ce.paul0506@gmail.com", stackoverflow1).
hasAccount(steam, "crazyp2017", steam1).

hasAccount(google, "crazyp2017@gmail.com", google2).
hasAccount(reddit, "aclip4lyfe", reddit1).
hasAccount(facebook, "crazyp2017@gmail.com", facebook2).
hasAccount(wordpress, "crazypblog", wordpress1).
hasAccount(asos, "crazyp2017@gmail.com", asos1).
hasAccount(goodreads, "crazyp2017@mail.com", goodreads1).
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
accountLogin(twitter1, "ce.paul0506@gmail.com", "adobe123").
accountLogin(twitter1, "cp0506", "adobe123").
accountLogin(linkedin1, "ce.paul0506@gmail.com", "password123").
accountLogin(dropbox1, "ce.paul0506@gmail.com", "password123").
accountLogin(ebay1, "ce.paul0506@gmail.com", "CPebpw2017.W").
accountLogin(ebay1, "cp0506", "CPebpw2017.W").
accountLogin(amazon1, "ce.paul0506@gmail.com", "CPamaz85pw.L").
accountLogin(yelp1, "ce.paul0506@gmail.com", "password123").
accountLogin(tumblr1, "ce.paul0506@gmail.com", "chrispaul0506").
accountLogin(instagram1, "ce.paul0506", "adobe123").
accountLogin(quora1, "ce.paul0506@gmail.com", "password123").
accountLogin(steam1, "crazyp2017", "Acpwfaca2017?").

accountLogin(google2, "crazyp2017@gmail.com", "justarandompassword").
accountLogin(reddit1, "aclip4lyfe", "password123").
accountLogin(facebook2, "crazyp2017@gmail.com", "crazyp0506").
accountLogin(wordpress1, "crazypblog", "cp2017blogpw").
accountLogin(wordpress1, "crazyp2017@gmail.com", "cp2017blogpw").
accountLogin(twitchtv1, "crazyp0085", "cptwitchpw").


% -- singleSignOn/2
% list of accounts with sso authentication, maps symbol of account to symbol of
% sso account
singleSignOn(airbnb1, google1).
singleSignOn(expedia1, facebook1).
singleSignOn(tripadvisor1, google1).
singleSignOn(pinterest1, facebook1).
singleSignOn(foursquare1, facebook1).
singleSignOn(quora1, facebook1).
singleSignOn(stackoverflow1, facebook1).
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
accountEmail(twitter1, google1, private).
accountEmail(linkedin1, google1, public).
accountEmail(dropbox1, google1, private).
accountEmail(ebay1, google1, private).
accountEmail(amazon1, google1, private).
accountEmail(airbnb1, google1, private).
accountEmail(expedia1, google1, private).
accountEmail(tripadvisor1, google1, private).
accountEmail(yelp1, google1, private).
accountEmail(tumblr1, google1, private).
accountEmail(instagram1, google1, private).
accountEmail(pinterest1, google1, private).
accountEmail(quora1, google1, private).
accountEmail(foursquare1, google1, private).
accountEmail(stackoverflow1, google1, private).
accountEmail(steam1, google1, private).

accountEmail(google2, google1, private).
accountEmail(reddit1, google2, private). 
accountEmail(facebook2, google2, public).
accountEmail(wordpress1, google2, private).
accountEmail(asos1, google2, private).
accountEmail(goodreads1, google2, private).
accountEmail(espngo1, google2, private).
accountEmail(twitchtv1, google2, private).
accountEmail(goal1, google2, private).

% -- userPublic/1
userPublic(steam1).
userPublic(reddit1).
userPublic(wordpress1).
userPublic(twitchtv1).
userPublic(instagram1).


% -- publicInfo/2
% list of information publicly available for the different accounts
publicInfo(google1, [name]).
publicInfo(microsoft1, [name]).
publicInfo(apple1, []).
publicInfo(yahoo1, [name]).
publicInfo(facebook1, [name, birthday, current_city, hometown, workplace, university]).
publicInfo(twitter1, [name]).
publicInfo(linkedin1, [name, workplace, university]).
publicInfo(dropbox1, [name]).
publicInfo(ebay1, []).
publicInfo(amazon1, []).
publicInfo(airbnb1, [firstname]).
publicInfo(expedia1, []).
publicInfo(tripadvisor1, [firstname]).
publicInfo(yelp1, [firstname, age, current_city]).
publicInfo(tumblr1, []).
publicInfo(instagram1, [name]).
publicInfo(pinterest1, [name, birthday, current_city, hometown, workplace, university]).
publicInfo(foursquare1, [name, birthday, current_city, hometown, workplace, university]).
publicInfo(quora1, [name, email, birthday, current_city, hometown, workplace, university]).
publicInfo(stackoverflow1, [name, birthday, current_city, hometown, workplace, university]).
publicInfo(steam1, []).

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
privateInfo(twitter1, []).
privateInfo(linkedin1, []).
privateInfo(dropbox1, []).
privateInfo(ebay1, [name, mobile, address, card]).
privateInfo(amazon1, [name, mobile, address, card]).
privateInfo(airbnb1, []).
privateInfo(expedia1, [name, birthday, mobile, address]).
privateInfo(tripadvisor1, [name, current_city]).
privateInfo(yelp1, [name, birthday]).
privateInfo(tumblr1, [name]).
privateInfo(instagram1, []).
privateInfo(pinterest1, []).
privateInfo(foursquare1, []).
privateInfo(quora1, []).
privateInfo(stackoverflow1, []).
privateInfo(steam1, [email, location]).

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
resetInfo(apple1, [birthday, hometown, job], _).
resetInfo(yahoo1, [username, email], google1).
resetInfo(facebook1, [email], google1).
resetInfo(twitter1, [username], google1).
resetInfo(twitter1, [email], google1).
resetInfo(linkedin1, [email], google1).
resetInfo(dropbox1, [email], google1).
resetInfo(ebay1, [email], google1).
resetInfo(amazon1, [email], google1).
resetInfo(airbnb1, [email], google1).
resetInfo(expedia1, [email], google1).
resetInfo(tripadvisor1, [email], google1).
resetInfo(yelp1, [email], google1).
resetInfo(tumblr1, [email], google1).
resetInfo(instagram1, [email], google1).
resetInfo(pinterest1, [email], google1).
resetInfo(foursquare1, [email], google1).
resetInfo(quora1, [email], google1).
resetInfo(stackoverflow1, [email], google1).
resetInfo(steam1, [username], google1).
resetInfo(steam1, [email], google1).
resetInfo(steam1, [mobile], google1).

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

