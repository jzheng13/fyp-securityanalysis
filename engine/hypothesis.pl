:- ensure_loaded(['policy']).

:- dynamic bugHyp/4.

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
  asserta(publicInfo(default, Info)).

% -- assumeAccess/2
% assume a person has access to a particular account
assumeAccess(Account, Person) :-
  asserta(hasAccess(Account, Person)).