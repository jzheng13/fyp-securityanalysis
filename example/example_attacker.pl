:- ensure_loaded(['example_utils.pl', 'example_accounts.pl', 'example.pl']).

%% Example for test database : Attacker %%

% -- hasAccess/2
% checks access level of accounts
hasAccess(Account, userAccess) :-
    hasAccount(_, _, Account).

hasAccess(Account, attackerAccess) :-
    hasAccount(_, _, Account),
    knows(attacker, Account, username),
    knows(attacker, Account, password).

hasAccess(Account, attackerAccess) :-
    hasAccount(_, _, Account),
    singleSignOn(Account, SSOAccount),
    hasAccess(SSOAccount, attackerAccess).

hasAccess(Account, attackerAccess) :-
    hasAccount(_, _, Account),
    resetInfo(Account, Info, RecAcc),
    knowsAll(attacker, Account, Info),
    hasAccess(RecAcc, attackerAccess).

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

knowsInfo(attacker, Account, username, _) :-
    vulProperty(Vulnerability, userKnown),
    vulExists(Account, Vulnerability).

knowsInfo(attacker, Account1, username, L) :-
    accountConn(Account1, Account2, sameUser),
    \+ visited(Account2, L),
    knowsInfo(attacker, Account2, username, [Account1 | L]).

%knowsInfo(attacker, Account, password, _) :-
%    vulProperty(Vulnerability, pwKnown),
%    vulExists(Account, Vulnerability).

%knowsInfo(attacker, Account1, password, L) :-
%    accountConn(Account1, Account2, samePw),
%    \+ visited(Account2, L),
%    knowsInfo(attacker, Account2, password, [Account1 | L]).



% attacker knows personal information of user if the information is public
% or can be obtained through other means.   
%knowsInfo(attacker, _, Info, _) :-
%    Info \== username,
%    Info \== password,
%    (public(Info);
%    canRetrieve(Info)).


% public/1
% a piece of information is public if it is publicly available on an
% account
public(I) :-
    publicInfo(_, Info),
    member(I, Info).

public(I) :-
    publicInfo(_, Info),
    subset(I, IS),
    member(IS, Info).

    
% canRetrieve/1
% a piece of information can be retrieved by exploiting links across
% accounts
canRetrieve(I) :-
    privateInfo(Account, Info),
    member(I, Info),
    hasAccess(Account, attackerAccess).
