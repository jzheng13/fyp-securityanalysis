:- ensure_loaded(['./example_utils.pl', './example.pl']).

% (hasAccess(Account, attacker), write('Compromised='), writeln(Account), fail; true).

%% Example for test database : Attacker %%

% -- hasAccess/2
% checks access level of accounts

hasAccess(Account, user) :-
    hasAccount(_, _, Account).

hasAccess(Account, attacker) :-
    hasAccessTo(Account, attacker, []).

hasAccessTo(default, _, _).

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

knowsInformation(attacker, Account, Field, _) :-
    vulExists(Account, Vulnerability),
    vulProperty(Vulnerability, Field, known).

knowsInformation(attacker, Account, username, L) :-
    vulExists(Account, userSimEmail),
    knows(attacker, Account, email, L).

knowsInformation(attacker, Account, password, L) :-
    vulExists(Account, userInPw),
    knows(attacker, Account, username, L).

knowsInformation(attacker, Account, password, L) :-
    vulExists(Account, nameInPw),
    knows(attacker, Account, name, L).

knowsInformation(attacker, Account, email, _) :-
    vulExists(Account, userSimEmail),
    vulExists(Account, publicUser).

knowsInfo(attacker, Account, Field, L) :-
    knowsInformation(attacker, Account, Field, L).

knowsInfo(attacker, Account1, Field, L) :-
    accountConn(Account1, Account2, Field, same),
    knowsInformation(attacker, Account2, Field, [Account1 | L]).


% attacker knows personal information of user if the information is public
% or can be obtained through other means.   
knowsInfo(attacker, Account, Info, _, L) :-
    Info \== username,
    Info \== password,
    Info \== email,
    (public(Info);
    canRetrieve(Account, Info, L)).


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

    
% canRetrieve/1
% a piece of information can be retrieved by exploiting links across
% accounts
canRetrieve(Account, I, L) :-
    privateInfo(Account2, Info),
    Account \== Account2,
    member(I, Info),
    hasAccessTo(Account2, attacker, [Account | L]).
