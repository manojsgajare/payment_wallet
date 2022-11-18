package com.code.service;

import com.code.exception.LoginException;
import com.code.model.*;

public interface CustomerService {
	
	public Customer createNewSignUp(Customer signUp) throws LoginException;;
	
	public Customer updateSignUpDetails(Customer signUp,String key) throws LoginException;
}
