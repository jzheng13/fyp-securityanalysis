:- ensure_loaded(['./example_utils.pl', './example_accounts.pl', './example.pl']).

% (hasAccess(Account, attacker), write('Compromised='), writeln(Account), fail; true).

%% Example for test database : Attacker %%

% -- hasAccess/2
% checks access level of accounts
hasAccess(Account, user) :-
    hasAccount(_, _, Account).

hasAccess(Account, attacker) :-
    hasAccessTo(Account, attacker, []).

hasAccessTo(Account, Person, _) :-
    knows(Person, Account, password), 
    knows(Person, Account, username).

hasAccessTo(Account, Person, _) :-
    singleSignOn(Account, SSOAccount),
    hasAccessTo(Person, SSOAccount, _).

hasAccessTo(Account, Person, _) :-
    knows(Person, Account, username),
    vulExists(Account, Vulnerability),
    vulProperty(Vulnerability, password, fromUser).

nhasAccessTo(Account, Person, L) :-
    resetInfo(Account, Info, RecAcc), 
    knowsAll(Person, Account, Info), fail,
    \+ member(RecAcc, L),
    hasAccessTo(RecAcc, Person, [Account | L]).


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
    knowsInfo(Person, Account, Info, []).

% knowsInfo/4
% helper function to avoid revisiting accounts

knowsInfo(attacker, Account, Field, _) :-
    vulExists(Account, Vulnerability),
    vulProperty(Vulnerability, Field, known).

knowsInfo(attacker, Account1, username, L) :-
    hasAccount(_, _, Account2),
    accountConn(Account1, Account2, username, same),
    \+ member(Account2, L),
    knowsInfo(attacker, Account2, username, [Account1 | L]).

knowsInfo(attacker, Account1, password, L) :-
    hasAccount(_, _, Account2),
    accountConn(Account1, Account2, password, same),
    \+ member(Account2, L),
    knowsInfo(attacker, Account2, password, [Account1 | L]).

knowsInfo(attacker, Account1, email, L) :-
    hasAccount(_, _, Account2),
    accountConn(Account1, Account2, email, same),
    \+ member(Account2, L),
    knowsInfo(attacker, Account2, email, [Account1 | L]).
    


% attacker knows personal information of user if the information is public
% or can be obtained through other means.   
knowsInfo(attacker, Account, Info, _) :-
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

    
% canRetrieve/1
% a piece of information can be retrieved by exploiting links across
% accounts
canRetrieve(Account, I) :-
    privateInfo(Account2, Info),
    Account \== Account2,
    member(I, Info),
    hasAccess(Account2, attacker).
