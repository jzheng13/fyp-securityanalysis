:- set_prolog_flag(double_quotes, codes).
:- ensure_loaded(['example_utils.pl', 'example_accounts.pl', 'example.pl']).

:- begin_tests(utils).

% tests not passing when run from file but works fine when typed in manually
% test not passing
test(isSubstring) :-
    isSubstring("a", "a"),
    isSubstring("abc", "abcdef"),
    isSubstring("abc", "aaabcccc"),
    isSubstring("abc", "aabbccabc").

% test not passing
test(getStringBefore) :-
    getStringBefore("abc", "abc@somewhere", "@"),
    getStringBefore("abc", "abcdd", "d"),
    getStringBefore("", "abc", "a").

% test not passing
test(fulfillReq) :-
    fulfillReq("1!abcAbc23wS"),
    fulfillReq("abcdefwS00?").

:- end_tests(utils).

:- begin_tests(vulnerabilities).

test(publicUser) :-
    vulExists(instagram1, publicUser),
    vulExists(steam1, publicUser),
    vulExists(reddit1, publicUser),
    vulExists(wordpress1, publicUser).

test(commonPw) :-
    vulExists(facebook1, commonPw),
    vulExists(twitter1, commonPw),
    vulExists(linkedin1, commonPw),
    vulExists(yelp1, commonPw),
    vulExists(dropbox1, commonPw),
    vulExists(instagram1, commonPw),
    vulExists(quora1, commonPw),
    vulExists(reddit1, commonPw).

test(nameInPw) :-
    vulExists(microsoft1, nameInPw),
    vulExists(apple1, nameInPw).

test(weakPw) :-
    vulExists(microsoft1, weakPw),
    vulExists(apple1, weakPw),
    vulExists(yahoo1, weakPw),
    vulExists(facebook1, weakPw),
    vulExists(twitter1, weakPw),
    vulExists(twitter1, weakPw),
    vulExists(linkedin1, weakPw),
    vulExists(dropbox1, weakPw),
    vulExists(tumblr1, weakPw),
    vulExists(instagram1, weakPw),
    vulExists(quora1, weakPw),
    vulExists(google2, weakPw),
    vulExists(reddit1, weakPw),
    vulExists(facebook2, weakPw),
    vulExists(wordpress1, weakPw),
    vulExists(wordpress1, weakPw),
    vulExists(twitchtv1, weakPw).

test(pwExpired) :-
    vulExists(google1, pwExpired).

:- end_tests(vulnerabilities).

:- begin_tests(connections).

% test not passing (due to getStringBefore not passing)
test(sameUser) :-
    accountConn(google1, microsoft1, sameUser),
    accountConn(instagram1, google1, sameUser),
    accountCoon(google2, steam1, sameUser).

test(samePw) :-
    accountConn(apple1, microsoft1, samePw),
    accountConn(linkedin1, dropbox1, samePw),
    accountConn(facebook1, twitter1, samePw).

test(recovery) :-
    accountConn(google1, microsoft1, recovery),
    accountConn(reddit1, google2, recovery),
    accountConn(yahoo1, google1, recovery).

test(infoPublic) :-
    accountConn(apple1, facebook1, infoPublic).

:- end_tests(connections).
