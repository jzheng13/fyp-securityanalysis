:- ensure_loaded(['example_attacker']).

%% Example for test database : Security Policy %%

% -- allow/2
% currently only allowing user access to all accounts, and attacker access to
% default account (placeholder for null)
allow(_, user).
allow(default, attacker).


% -- policyViolation/2
% vulnerability in the system if unauthorised parties can access accounts
% they are not allowed to
policyViolation(Account, Access) :-
    hasAccess(Account, Access),
    \+ allow(Account, Access).