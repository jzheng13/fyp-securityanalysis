:- set_prolog_flag(double_quotes, codes).

%% Helper functions %%


% -- isSubstring/2
% checks or gets substring S1 from S2

isSubstring(S, S) :- !.

isSubstring(S1, S2) :-
    append(_, T, S2),
    append(S1, _, T),
    S1 \= [].


% -- getStringBefore/3
% tail recursive function with returns string prefix before a certain
% character, does not work for multiple occurences of the character 
% (takes first as splitting point)

getStringBefore([], [C | _], [C]).
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