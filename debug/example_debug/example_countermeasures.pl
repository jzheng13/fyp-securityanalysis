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
    accountLogin(Account, Username, Password),
    R = accountLogin(Account, Username, Password),
    randomPassword(NewPW),
    A = accountLogin(Account, Username, NewPW).

% -- counterConn/2
% defines procedures to break down connections between accounts which are 
% leading to exploits
% 1. samePw -> change password
% 2. infoPublic -> remove information/change privacy settings

counterConn(A1, A2, samePw, A, R) :-
    accountConn(A1, A2, samePw),
    accountLogin(A1, Username, Password),
    R = accountLogin(Account, Username, Password),
    randomPassword(NewPW),
    A = accountLogin(Account, Username, NewPW).

/*
TODO: Need utility function to remove item from list
      Need to get type info from accountConn
counterConn(A1, A2, infoPublic, A, R) :-
    accountConn(A1, A2, infoPublic),
    publicInfo(A2, L),
    R = publicInfo(A2, L),

    A = publicInfo(A2, L\Info).
*/

% -- counterMeasures/3
% to verify that countermeasures work, query should return false when run

/*
counterMeasures(Person, Account, RMeasures, AMeasures) :-
    forall(member(RM, RMeasures), retract(RM)),
    forall(member(AM, AMeasures), asserta(AM)),
    policyViolation(Person, Account).
*/
    