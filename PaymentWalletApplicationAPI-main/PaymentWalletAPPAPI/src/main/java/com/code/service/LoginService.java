package com.code.service;

import com.code.exception.LoginException;
import com.code.model.LogIn;

public interface LoginService {
	
	public String logInAccount(LogIn loginData) throws LoginException;
	public String logOutFromAccount(String key) throws LoginException;
}
