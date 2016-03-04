
%% Example for test database : Accounts %%



% -- Google
hasAccount(google, 'ce.paul0506@gmail.com', userAccess).
accountLogin(google, 'ce.paul0506@gmail.com', 'chrispaul').
accountEmail(google, 'ce.paul0506@gmail.com', microsoft, 'ce.paul0506@outlook.com', private).
publicInfo(google, 'ce.paul0506@gmail.com', [name]).
privateInfo(google, 'ce.paul0506@gmail.com', [birthday]).


% -- Microsoft
hasAccount(microsoft, 'ce.paul0506@outlook.com', userAccess).
accountLogin(microsoft, 'ce.paul0506@outlook.com', 'ChrisPaul0506').
accountEmail(microsoft, 'ce.paul0506@outlook.com', google, 'ce.paul0506@gmail.com', private).
publicInfo(microsoft, 'ce.paul0506@outlook.com', [name]).
privateInfo(microsoft, 'ce.paul0506@outlook.com', [birthday]).



% -- Yahoo
hasAccount(yahoo, 'ce.paul0506@yahoo.com', userAccess).
accountLogin(yahoo, 'ce.paul0506@yahoo.com', 'chrispaul0506').
accountEmail(yahoo, 'ce.paul0506@yahoo.com', google, 'ce.paul0506@gmail.com', private).
publicInfo(yahoo, 'ce.paul0506@yahoo.com', [name]).
privateInfo(yahoo, 'ce.paul0506@yahoo.com', [mobile, birthday]).


% -- Facebook
hasAccount(facebook, 'ce.paul0506@gmail.com', userAccess).
accountLogin(facebook, 'ce.paul0506@gmail.com', 'adobe123').
accountEmail(facebook, 'ce.paul0506@gmail.com', google, 'ce.paul0506@gmail.com', public).
publicInfo(facebook, 'ce.paul0506@gmail.com', [name, birthday, current_city, hometown, workplace, university]).
privateInfo(facebook, 'ce.paul0506@gmail.com', []).


% -- Twitter
hasAccount(twitter, 'ce.paul0506@gmail.com', userAccess).
accountLogin(twitter, 'ce.paul0506@gmail.com', 'adobe123').
accountLogin(twitter, 'cp0506', 'adobe123').
accountEmail(twitter, 'ce.paul0506@gmail.com', google, 'ce.paul0506@gmail.com', private).
publicInfo(twitter, 'ce.paul0506@gmail.com', [name]).
privateInfo(twitter, 'ce.paul0506@gmail.com', []).


% -- LinkedIn
hasAccount(linkedin, 'ce.paul0506@gmail.com', userAccess).
accountLogin(linkedin, 'ce.paul0506@gmail.com', 'password123').
accountEmail(linkedin, 'ce.paul0506@gmail.com', google, 'ce.paul0506@gmail.com', public).
publicInfo(linkedin, 'ce.paul0506@gmail.com', [name, workplace, university]).
privateInfo(linkedin, 'ce.paul0506@gmail.com', []).


% -- Dropbox
hasAccount(dropbox, 'ce.paul0506@gmail.com', userAccess).
accountLogin(dropbox, 'ce.paul0506@gmail.com', 'password123').
accountEmail(dropbox, 'ce.paul0506@gmail.com', google, 'ce.paul0506@gmail.com', private).
publicInfo(dropbox, 'ce.paul0506@gmail.com', [name]).
privateInfo(dropbox, 'ce.paul0506@gmail.com', []).


% -- eBay
hasAccount(ebay, 'ce.paul0506@gmail.com', userAccess).
accountLogin(ebay, 'ce.paul0506@gmail.com', 'chrispaul123').
accountLogin(ebay, 'cp0506', 'chrispaul123').
accountEmail(ebay, 'ce.paul0506@gmail.com', google, 'ce.paul0506@gmail.com', private).
publicInfo(ebay, 'ce.paul0506@gmail.com', []).
privateInfo(ebay, 'ce.paul0506@gmail.com', [name, mobile, address]).


% -- Amazon
hasAccount(amazon, 'ce.paul0506@gmail.com', userAccess).
accountLogin(amazon, 'ce.paul0506@gmail.com', 'chrispaul0506').
accountEmail(amazon, 'ce.paul0506@gmail.com', google, 'ce.paul0506@gmail.com', private).
publicInfo(amazon, 'ce.paul0506@gmail.com', []).
privateInfo(amazon, 'ce.paul0506@gmail.com', [name, mobile, address]).


% -- Airbnb
hasAccount(airbnb, 'ce.paul0506@gmail.com', userAccess).
singleSignOn(airbnb, 'ce.paul0506@gmail.com', google, 'ce.paul0506@gmail.com').
accountEmail(airbnb, 'ce.paul0506@gmail.com', google, 'ce.paul0506@gmail.com', private).
publicInfo(airbnb, 'ce.paul0506@gmail.com', [firstname]).
privateInfo(airbnb, 'ce.paul0506@gmail.com', []).


% -- Expedia
hasAccount(expedia, 'ce.paul0506@gmail.com', userAccess).
singleSignOn(expedia, 'ce.paul0506@gmail.com', facebook, 'ce.paul0506@gmail.com').
accountEmail(expedia, 'ce.paul0506@gmail.com', google, 'ce.paul0506@gmail.com', private).
publicInfo(expedia, 'ce.paul0506@gmail.com', []).
privateInfo(expedia, 'ce.paul0506@gmail.com', [name, birthday, mobile, address]).


% -- TripAdvisor
hasAccount(tripadvisor, 'ce.paul0506@gmail.com', userAccess).
singleSignOn(tripadvisor, 'ce.paul0506@gmail.com', google, 'ce.paul0506@gmail.com').
accountEmail(tripadvisor, 'ce.paul0506@gmail.com', google, 'ce.paul0506@gmail.com', private).
publicInfo(tripadvisor, 'ce.paul0506@gmail.com', [firstname]).
privateInfo(tripadvisor, 'ce.paul0506@gmail.com', [name, current_city]).


% -- Apple
hasAccount(apple, 'ce.paul0506@gmail.com', userAccess).
accountLogin(apple, 'ce.paul0506@gmail.com', 'ChrisPaul0506').
accountEmail(apple, 'ce.paul0506@gmail.com', google, 'ce.paul0506@gmail.com', private).
publicInfo(apple, 'ce.paul0506@gmail.com', []).
privateInfo(apple, 'ce.paul0506@gmail.com', [name, birthday, current_city]).


% -- Yelp
hasAccount(yelp, 'ce.paul0506@gmail.com', userAccess).
accountLogin(yelp, 'ce.paul0506@gmail.com', 'password123').
accountEmail(yelp, 'ce.paul0506@gmail.com', google, 'ce.paul0506@gmail.com', private).
publicInfo(yelp, 'ce.paul0506@gmail.com', [firstname, age, current_city]).
privateInfo(yelp, 'ce.paul0506@gmail.com', [name, birthday]).


% -- Tumblr
hasAccount(tumblr, 'ce.paul0506@gmail.com', userAccess).
accountLogin(tumblr, 'ce.paul0506@gmail.com', 'chrispaul0506').
accountEmail(tumblr 'ce.paul0506@gmail.com', google, 'ce.paul0506@gmail.com', private).
publicInfo(tumblr, 'ce.paul0506@gmail.com', []).
privateInfo(tumblr, 'ce.paul0506@gmail.com', [name]).


% -- Instagram
hasAccount(instagram, 'ce.paul0506', userAccess).
accountLogin(instagram, 'ce.paul0506', 'adobe123').
accountEmail(instagram, 'ce.paul0506', google, 'ce.paul0506@gmail.com', private).
publicInfo(instagram, 'ce.paul0506', [name]).
privateInfo(instagram, 'ce.paul0506', []).


% -- Pinterest
hasAccount(pinterest, 'ce.paul0506@gmail.com', userAccess).
singleSignOn(pinterest, 'ce.paul0506@gmail.com', facebook, 'ce.paul0506@gmail.com').
accountEmail(pinterest, 'ce.paul0506@gmail.com', google, 'ce.paul0506@gmail.com', private).
publicInfo(pinterest, 'ce.paul0506@gmail.com', [name, birthday, current_city, hometown, workplace, university]).
privateInfo(pinterest, 'ce.paul0506@gmail.com', []).


% -- FourSquare
hasAccount(foursquare, 'ce.paul0506@gmail.com', userAccess).
singleSignOn(foursquare, 'ce.paul0506@gmail.com', facebook, 'ce.paul0506@gmail.com').
accountEmail(foursquare, 'ce.paul0506@gmail.com', google, 'ce.paul0506@gmail.com', private).
publicInfo(foursquare, 'ce.paul0506@gmail.com', [name, birthday, current_city, hometown, workplace, university]).
privateInfo(foursquare, 'ce.paul0506@gmail.com', []).


% -- Quora
hasAccount(quora, 'ce.paul0506@gmail.com', userAccess).
accountLogin(quora, 'ce.paul0506@gmail.com', 'password123').
singleSignOn(quora, 'ce.paul0506@gmail.com', facebook, 'ce.paul0506@gmail.com').
accountEmail(quora, 'ce.paul0506@gmail.com', google, 'ce.paul0506@gmail.com', private).
publicInfo(quora, 'ce.paul0506@gmail.com', [name, birthday, current_city, hometown, workplace, university]).
privateInfo(quora, 'ce.paul0506@gmail.com', []).


% -- Stack Overflow
hasAccount(stackoverflow, 'ce.paul0506@gmail.com', userAccess).
singleSignOn(stackoverflow, 'ce.paul0506@gmail.com', facebook, 'ce.paul0506@gmail.com').
accountEmail(stackoverflow, 'ce.paul0506@gmail.com', google, 'ce.paul0506@gmail.com', private).
publicInfo(stackoverflow, 'ce.paul0506@gmail.com', [name, birthday, current_city, hometown, workplace, university]).
privateInfo(stackoverflow, 'ce.paul0506@gmail.com', []).




