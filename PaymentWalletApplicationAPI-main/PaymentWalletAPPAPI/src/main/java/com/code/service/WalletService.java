package com.code.service;

import java.math.BigDecimal;

import java.util.*;

import javax.naming.InsufficientResourcesException;

import com.code.exception.BeneficiaryDetailException;
import com.code.exception.CustomerNotException;
import com.code.exception.InsufficientBalanceException;
import com.code.exception.LoginException;
import com.code.model.BeneficiaryDetail;
import com.code.model.Customer;
import com.code.model.Transaction;
import com.code.model.Wallet;

public interface WalletService {

	public  Double showBalance(String mobileNo) throws CustomerNotException, LoginException;
	
	public Transaction fundTransfer(String sourceMoblieNo,String targetMobileNo,Double amout,String uniqueId) throws CustomerNotException, BeneficiaryDetailException, LoginException,InsufficientBalanceException;
	
	public Transaction depositeAmount(String uniqueId,Double amount) throws CustomerNotException, LoginException, InsufficientResourcesException, InsufficientBalanceException;
	
	public List<BeneficiaryDetail> getList(String uniqueId) throws CustomerNotException, LoginException, BeneficiaryDetailException;
	
	public Customer addMoney(String uniqueId, Double amount) throws Exception;
	
}
