%% Example for test database : User %%


% -- userInfo/2
% map variables(atoms) to constants(strings)
userInfo(firstname, "Christopher").
userInfo(shortened, "Chris").
userInfo(lastname, "Paul").
userInfo(gender, "Male").
userInfo(birthday, "6 May 1985").
userInfo(mobile, "07510537578").
userInfo(current_city, "Los Angeles, California").
userInfo(hometown, "Winston-Salem, North Carolina"). 
userInfo(location, "United States").
userInfo(workplace, "Los Angeles Clippers").
userInfo(job, "basketball player").
userInfo(address, "CA19005").


% -- subset/2
subset(shortened, firstname).
subset(location, current_city).
subset(location, address).