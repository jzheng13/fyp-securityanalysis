
%% Helper functions %%

forall(A, B) :-
    \+ A, \+ B.

isSubstring([], _).
isSubstring([H | T1], [H | T2]) :-
    isSubstring(T1, T2).
isSubstring(S, [_ | T]) :-
    isSubstring(S, T).

getStringBefore(P, S, C) :-
    isSubstring([P | C], S).