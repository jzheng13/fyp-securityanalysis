:- ensure_loaded(['example_policy']).

%% Example for test database : Countermeasures %%

% -- counter/4
% defines procedures to correct vulnerabilities, A for assertions and R for
% retractions
% 1. publicUser -> set username privacy
% 2. publicEmail -> set email privacy
% 3. commonPw, weakPw, nameInPw, userInPw, pwExpired -> change password

counter(Account, publicUser, A, R) :-
    vulExists(Account, publicUser),
    R = userpublic(Account),
    A = ''.
counter(Account, publicEmail, A, R) :-
    vulExists(Account, publicEmail),
    accountEmail(Account, Email, public),
    R = accountEmail(Account, Email, public),
    A = accountEmail(Account, Email, private).
counter(Account, Vulnerability, A, R) :-
    vulExists(Account, Vulnerability),
    Vulnerability \== publicUser, 
    Vulnerability \== publicEmail,
    Vulnerability \== userSimEmail,
    accountLogin(Account, Username, Password),
    R = accountLogin(Account, Username, Password),
    randomPassword(NewPW),
    A = accountLogin(Account, Username, NewPW).

% -- counterConn/6
% defines procedures to break down connections between accounts which are 
% leading to exploits
% 1. samePw -> change password
% 2. infoPublic -> remove information/change privacy settings

counterConn(A1, A2, password, same, A, R) :-
    accountConn(A1, A2, password, same),
    accountLogin(A1, Username, Password),
    R = accountLogin(A1, Username, Password),
    randomPassword(NewPW),
    A = accountLogin(A1, Username, NewPW).

counterConn(A1, A2, Info, public, A, R) :-
    accountConn(A1, A2, Info, public),
    publicInfo(A2, L),
    R = publicInfo(A2, L),
    removeFrom(NewInfo, Info, L),
    A = publicInfo(A2, NewInfo).

% -- counterMeasures/3
% to verify that countermeasures work, query should return false when run

/*
counterMeasures(Person, Account, RMeasures, AMeasures) :-
    forall(member(RM, RMeasures), retract(RM)),
    forall(member(AM, AMeasures), asserta(AM)),
    policyViolation(Person, Account).
*/
    