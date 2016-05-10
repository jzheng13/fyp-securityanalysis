:- ensure_loaded(['example_policy']).

:- dynamic bugHyp/4.
:- dynamic issuedInfo/1.
:- dynamic assumedAccess/2.

%% Example for test database : Hypothetical Analysis %%

% -- bugHyp/3
% static clause for inserting hypothetical bugs
% e.g. bugHyp(google1, samplebug, email, known).

% -- insertVul/4
% inserts hypothetical vulnerabilities into the system
insertVul(Account, ID, Field, Property) :-
  asserta(bugHyp(Account, ID, Field, Property)).

% -- giveInfo/1
% issuing information to attacker
giveInfo(Info) :-
  asserta(issuedInfo(Info)).

% -- assumeAccess/2
% assume a person has access to a particular account
assumeAccess(Account, Person) :-
  asserta(assumedAccess(Account, Person)).