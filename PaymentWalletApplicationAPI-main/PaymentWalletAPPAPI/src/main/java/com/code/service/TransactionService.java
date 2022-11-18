package com.code.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import com.code.exception.CustomerNotException;
import com.code.exception.TransactionNotFoundException;
import com.code.exception.UserNotLogedinException;
import com.code.model.Transaction;
import com.code.model.TransactionType;
import com.code.model.Wallet;

public interface TransactionService {
	
	public List<Transaction> viewAlltransaction(String  uniqueId)throws UserNotLogedinException, TransactionNotFoundException ;
	
	public List<Transaction> viewTranscationByDate(String from, String to , String uniqueId)  throws UserNotLogedinException,TransactionNotFoundException ;
		
	public List<Transaction> viewAllTransactionbyTransactionType(String uniqueId,TransactionType type) throws UserNotLogedinException, TransactionNotFoundException;


}
