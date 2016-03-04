
%% Example for test database : Vulnerabilities %%



% -- List of commonly used passwords 2013-15, from SplashData 

commonlyUsedPws(['123456', 'password', '12345', 
                 '12345678', 'qwerty', '123456789',
				 '1234', 'baseball', 'dragon',
                 'football', '1234567', 'monkey',
                 'letmein', 'abc123', '111111',
                 'mustang', 'access', 'shadow',
                 'master', 'michael', 'superman', 
                 '696969', '123123', 'batman',
                 'trustno1', 'welcome', '1234567890',
                 '1qaz2wsx', 'login', 'princess',
                 'qwertyuiop', 'solo', 'passw0rd',
                 'starwars', 'iloveyou', 'adobe123',
                 'admin', 'photoshop', 'sunshine',
                 'password1', 'azerty', '000000']).

% -- commonly-used password
vulExists(Account, Username, commonPw) :-
    accountLogin(Account, Username, Password),
    member(password, CommonlyUsedPasswords),
    commonlyUsedPws(CommonlyUsedPasswords).
vulProperty(commonPw, pwKnown).
