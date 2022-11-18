package com.code.service;

import java.util.Set;

import com.code.exception.BankAccountNotExsists;
import com.code.exception.BankAlreadyAdded;
import com.code.exception.NotAnyBankAddedYet;
import com.code.exception.UserNotLogedinException;
import com.code.model.BankAccount;

public interface BankAccountService {
	
	public BankAccount addBank(BankAccount bankAccount,String uniqueId) throws UserNotLogedinException,BankAlreadyAdded;

	public BankAccount removeBank(Integer accountNumber,String uniqueId) throws BankAccountNotExsists,UserNotLogedinException;
	
	public BankAccount viewBankAccountI(Integer accountNumber,String uniqueId) throws BankAccountNotExsists,UserNotLogedinException ;
	
	public BankAccount viewAllAccount(String uniqueId) throws UserNotLogedinException,NotAnyBankAddedYet, BankAccountNotExsists;
}
