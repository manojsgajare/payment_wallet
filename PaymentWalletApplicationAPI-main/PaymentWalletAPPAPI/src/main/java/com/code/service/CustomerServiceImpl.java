package com.code.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.exception.LoginException;
import com.code.model.Customer;
import com.code.model.Wallet;
import com.code.repository.CustomerDAO;
import com.code.repository.WalletDao;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerDAO signUpDAO;
	
	@Autowired
	private WalletDao walletDao;
 	
	@Autowired
	private CurrentUserSessionService getCurrentLoginUserSession;

	@Override
	public Customer createNewSignUp(Customer newSignUp) throws LoginException {
		System.out.println(newSignUp.toString());
		Optional<Customer> opt = signUpDAO.findByUserName(newSignUp.getUserName());
		if(opt.isPresent())
		{
			throw new LoginException("User Already Exist!");
		}
		Wallet wallet = new Wallet();

		wallet.setBalance(0.0);
		wallet.setCustomer(newSignUp);
		newSignUp.setWallet(wallet);
		
		return signUpDAO.save(newSignUp);
	}

	@Override
	public Customer updateSignUpDetails(Customer signUp, String key) throws LoginException {
		Customer signUpDetails = getCurrentLoginUserSession.getSignUpDetails(key);
		
		if(signUpDetails == null)
		{
			throw new LoginException("UnAuthorized!!! No User Found....Try To login first!");
		}
		
		if(signUpDetails.getUserId() == signUp.getUserId())
			{
			signUpDAO.save(signUp);
			return signUp;
			}
		else
			throw new LoginException("Can't change UserId!!");
	}

	
	

}
