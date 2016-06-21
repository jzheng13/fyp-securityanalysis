:- ensure_loaded(['example_policy']).

:- dynamic bugHyp/4.

%% Example for test database : Hypothetical Analysis %%

% -- bugHyp/3
% static clause for inserting hypothetical bugs
% e.g. bugHyp(google1, samplebug, email, known).

% -- checkTwo/6
% checks if hypothetical bugs in accounts result in policy violation
checkTwo(P, Account, Account1, Bug1, Field1, Property1, Account2, Bug2, Field2, Property2) :-
    asserta(bugHyp(Account1, Bug1, Field1, Property1)),
    asserta(bugHyp(Account2, Bug2, Field2, Property2)),
    policyViolation(Account, P).