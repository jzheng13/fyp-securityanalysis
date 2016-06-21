:- ensure_loaded(['utils.pl', 'engine.pl', 'hypothesis.pl']).
:- use_module(library(tabling)).

:- dynamic issuedInfo/1.
:- dynamic assumedAccess/2.
:- table hasAccess/2, knowsInfo/3.

% (hasAccess(Account, attacker), write('Compromised='), writeln(Account), fail; true).

%% Example for test database : Attacker %%

% -- hasAccess/2
% checks access level of accounts

hasAccess(Account, user) :-
    hasAccount(_, _, Account).

hasAccess(default, _).

hasAccess(Account, Person) :-
    assumedAccess(Account, Person).

hasAccess(Account, Person) :-
    knows(Person, Account, password), 
    knows(Person, Account, username).

hasAccess(Account, Person) :-
    singleSignOn(Account, SSOAccount),
    hasAccess(SSOAccount, Person).

hasAccess(Account, Person) :-
    resetInfo(Account, Info, RecAcc), 
    knowsAll(Person, Account, Info),
    hasAccess(RecAcc, Person).


% knowsAll/3
% tail recursive function to know all of information
knowsAll(_, _, []).
knowsAll(attacker, Account, [I | Info]) :-
    knows(attacker, Account, I),
    knowsAll(attacker, Account, Info).

% knows/3
% an attackers knows login credentials and email if they can be obtained due to
% vulnerabilities or through account connections

knows(Person, Account, Info) :-
    knowsInfo(Person, Account, Info).

% knowsInfo/4
% helper function to avoid revisiting accounts

knowsInfo(attacker, Account, Field) :-
    vulExists(Account, Vulnerability),
    vulProperty(Vulnerability, Field, known).

knowsInfo(attacker, Account1, Field) :-
    accountConn(Account1, Account2, Field, same),
    knowsInfo(attacker, Account2, Field).

knowsInfo(attacker, Account, password) :-
    vulExists(Account, userInPw),
    knowsInfo(attacker, Account, username).

knowsInfo(attacker, Account, username) :-
    vulExists(Account, userSimEmail),
    knowsInfo(attacker, Account, email).

knowsInfo(attacker, Account, password) :-
    pwContains(Account, Info),
    knowsAll(attacker, Account, Info).


% attacker knows personal information of user if the information is public
% or can be obtained through other means.   
knowsInfo(attacker, Account, Info) :-
    Info \== username,
    Info \== password,
    Info \== email,
    (public(Info);
    canRetrieve(Account, Info)).


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
canRetrieve(Acc1, I) :-
    subset(I, Info), 
    privateInfo(Acc2, PrivateInfo),
    member(Info, PrivateInfo),
    Acc2 \== Acc1,
    hasAccess(Acc2, attacker).
