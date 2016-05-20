:- ensure_loaded(['utils.pl', 'engine.pl', 'hypothesis.pl']).

:- dynamic issuedInfo/1.
:- dynamic assumedAccess/2.

:- table hasAccess/2, knows/3.

% (hasAccess(Account, attacker), write('Compromised='), writeln(Account), fail; true).

%% Example for test database : Attacker %%

% -- hasAccess/2
% checks access level of accounts

hasAccess(Account, user) :-
    hasAccount(_, _, Account).

hasAccess(default, _).

hasAccess(Account, Person, _) :-
    assumedAccess(Account, Person).

hasAccess(Account, Person) :-
    knows(Person, Account, password), 
    knows(Person, Account, username).

hasAccess(Account, Person) :-
    singleSignOn(Account, SSOAccount),
    hasAccess(Person, SSOAccount).

hasAccess(Account, Person) :-
    resetInfo(Account, Info, RecAcc), 
    knowsAll(Person, Account, Info),
    hasAccess(RecAcc, Person).


% knowsAll/3
% tail recursive function to know all of information
knowsAll(_, _, [], _).
knowsAll(attacker, Account, [I | Info]) :-
    knows(attacker, Account, I),
    knowsAll(attacker, Account, Info).

% knows/3
% an attackers knows login credentials and email if they can be obtained due to
% vulnerabilities or through account connections

knows(attacker, Account, Field) :-
    vulExists(Account, Vulnerability),
    vulProperty(Vulnerability, Field, known).

knows(attacker, Account, Field) :-
    accountConn(Account1, Account2, Field, same),
    knows(attacker, Account2, Field).

knows(attacker, Account, username) :-
    vulExists(Account, userInPw),
    knows(attacker, account, username).

knows(attacker, Account, password) :-
    vulExists(Account, userInPw),
    knows(attacker, Account, username).

knows(attacker, Account, username) :-
    vulExists(Account, userSimEmail),
    knows(attacker, Account, email).

knows(attacker, Account, password) :-
    pwContains(Account, Info),
    knowsAll(attacker, Account, Info).

% attacker knows personal information of user if the information is public
% or can be obtained through other means.   
knows(attacker, Account, Info) :-
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
canRetrieve(Acc1, I, L) :-
    subset(I, Info), 
    privateInfo(Acc2, PrivateInfo),
    member(Info, PrivateInfo),
    \+ member(Acc2, L),
    Acc2 \== Acc1,
    hasAccessTo(Acc2, attacker).
