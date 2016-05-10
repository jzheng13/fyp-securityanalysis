:- ensure_loaded(['./example_utils.pl', './example.pl']).

% (hasAccess(Account, attacker), write('Compromised='), writeln(Account), fail; true).

% -- hasAccess/2
% checks access level of accounts

hasAccess(Account, user) :-
    hasAccount(_, _, Account).

hasAccess(Account, attacker) :-
    hasAccessTo(Account, attacker, []).

hasAccessTo(default, _, _).

hasAccessTo(Account, Person, _) :-
    assumedAccess(Account, Person).

hasAccessTo(Account, Person, L) :-
    knows(Person, Account, password, L), 
    knows(Person, Account, username, L).

hasAccessTo(Account, Person, L) :-
    singleSignOn(Account, SSOAccount),
    \+ member(SSOAccount, L),
    hasAccessTo(Person, SSOAccount, [Account| L]).

hasAccessTo(Account, Person, L) :-
    resetInfo(Account, Info, RecAcc), 
    knowsAll(Person, Account, Info, L),
    \+ member(RecAcc, L),
    hasAccessTo(RecAcc, Person, [Account | L]).


% knowsAll/3
% tail recursive function to know all of information
knowsAll(_, _, [], _).
knowsAll(attacker, Account, [I | Info], L) :-
    knows(attacker, Account, I, L),
    knowsAll(attacker, Account, Info, L).

% knows/3
% an attackers knows login credentials and email if they can be obtained due to
% vulnerabilities or through account connections

knows(Person, Account, Info, L) :-
    knowsInfo(Person, Account, Info, L).

% knowsInfo/4
% helper function to avoid revisiting accounts

knowsInformation(attacker, Account, Field) :-
    vulExists(Account, Vulnerability),
    vulProperty(Vulnerability, Field, known).

knowsInfo(attacker, Account, Field, _) :-
    knowsInformation(attacker, Account, Field).

knowsInfo(attacker, Account1, Field, _) :-
    accountConn(Account1, Account2, Field, same),
    knowsInformation(attacker, Account2, Field).

knowsInfo(attacker, Account, password, L) :-
    vulExists(Account, userInPw),
    knows(attacker, Account, username, L).

knowsInfo(attacker, Account, username, L) :-
    vulExists(Account, userSimEmail),
    knows(attacker, Account, email, L).

knowsInfo(attacker, Account, password, L) :-
    vulExists(Account, nameInPw),
    knows(attacker, Account, name, L).


% attacker knows personal information of user if the information is public
% or can be obtained through other means.   
knowsInfo(attacker, Account, Info, L) :-
    Info \== username,
    Info \== password,
    Info \== email,
    (public(Info)).
%    ;    canRetrieve(Account, Info, L)).


% public/1
% a piece of information is public if it is publicly available on an
% account
public(I) :-
    publicInfo(_, Info),
    member(I, Info).

public(I) :-
    publicInfo(_, Info),
    member(IS, Info),
    subset(I, IS).

public(I) :-
    issuedInfo(I).

    
% canRetrieve/1
% a piece of information can be retrieved by exploiting links across
% accounts
ncanRetrieve(Account, I, L) :-
    privateInfo(Account2, Info),
    Account \== Account2,
    member(I, Info),
    hasAccessTo(Account2, attacker, [Account | L]).
