
%% Example for test database : Connections %%



% -- same username

accountConn(Acc1, Username, Acc2, Username, sameUser) :-
    accountLogin(Acc1, Username, _),
    accountLogin(Acc2, Username, _).


% -- same password

accountConn(Acc1, Username1, Acc2, Username2, samePw) :-
    accountLogin(Acc1, Username1, Password),
    accountLogin(Acc2, Username2, Password).

