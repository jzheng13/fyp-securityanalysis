:- ensure_loaded(['attacker_tabled']).

%% Example for test database : Security Policy %%

% -- allow/2
% currently only allowing user access
allow(_, user).
allow(default, _).


% -- policyViolation/2
% vulnerability in the system if unauthorised parties can access accounts
% they are not allowed to
policyViolation(Account, Access) :-
    hasAccess(Account, Access),
    \+ allow(Account, Access).