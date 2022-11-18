package com.code.service;

import com.code.exception.LoginException;
import com.code.model.CurrentSessionUser;
import com.code.model.Customer;

public interface CurrentUserSessionService {
	public CurrentSessionUser getCurrentUserSession(String key) throws LoginException;;
	public Integer getCurrentUserSessionId(String key) throws LoginException;;
	
	public Customer getSignUpDetails(String key) throws LoginException;;
}
