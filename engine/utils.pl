% :- use_module(['abduction/abduction.pl']).

:- import append/3, member/2 from basics.
:- import datime/1 from standard.

%% Helper functions %%


% -- today/3
% get today's date
today(Y, M, D) :-
    datime(datime(Y, M, D, _, _, _)).

% -- isSubstring/2
% checks or gets substring S1 from S2
isSubstring(S, S) :- !.

isSubstring(S1, S2) :-
    append(_, T, S2),
    append(S1, _, T),
    S1 \= [].


% -- getStringBefore/3
% tail recursive function with returns string prefix before a certain
% character
getStringBefore([], [C | _], [C]) :- !.
getStringBefore([], [], _).
getStringBefore([H | T1], [H | T2], [C]) :-
    getStringBefore(T1, T2, [C]).


% -- fulfillReq/1
% check if password has
% 1. length >= 8
% 2. contains uppercase, lowercase letters, numbers and symbols
fulfillReq(P) :-
    length(P, L),
    L >= 8,
    contains(P, upper),
    contains(P, lower),
    contains(P, digit),
    (contains(P, punct); contains(P, space)).


% -- contains/2
% makes use of built in swipl function to check character types contained
% in a string
contains([P | _], T) :-
    char_type(P, T).
contains([_ | PS], T) :-
    contains(PS, T).

% -- randomPassword/1
% generates random, strong password
randomPassword(P) :-
    random_member(L1, "abcdefghijklmnopqrstuvwxyz"),
    random_member(L2, "abcdefghijklmnopqrstuvwxyz"),
    random_member(U1, "ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
    random_member(U2, "ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
    random_member(D1, "1234567890"),
    random_member(D2, "1234567890"),
    random_member(S1, ";'./[]!@$^&*()"),
    random_member(S2, ";'./[]!@$^&*()"),
    random_permutation([L1, L2, U1, U2, D1, D2, S1, S2], P).

% -- removeFrom/3
% remove element from list
removeFrom([], _, []).
removeFrom(T, X, [X | T]) :-
    removeFrom(T, X, T).
removeFrom([H | T1], X, [H | T2]) :-
    H \== X,
    removeFrom(T1, X, T2). 

% -- countSolutions/2
% count number of solutions for a query
countSolutions(Count, Query) :-
    aggregate_all(count, Query, Count).

% -- countUniqueSolns/3
% count number of solutions for a query
countUniqueSolns(Count, Query, Variable) :-
   aggregate_all(count, Variable, Query, Count).