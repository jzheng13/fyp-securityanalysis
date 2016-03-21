:- set_prolog_flag(double_quotes, codes).

%% Helper functions %%


% -- today/3
% get today's date
today(Y, M, D) :-
    get_time(Stamp),
    stamp_date_time(Stamp, DateTime, local),
    date_time_value(date, DateTime, date(Y, M, D)).

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