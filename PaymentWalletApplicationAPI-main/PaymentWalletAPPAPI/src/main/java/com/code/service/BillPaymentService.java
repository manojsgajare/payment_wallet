package com.code.service;

import java.util.Set;

import com.code.exception.InsufficientBalanceException;
import com.code.exception.UserNotLogedinException;
import com.code.model.BillPayment;

public interface BillPaymentService {
	
	public BillPayment makeBillPayment(BillPayment billpayment,String uniqueId) throws InsufficientBalanceException, UserNotLogedinException;

	public Set<BillPayment> viewBillPayments(String uniqueId) throws UserNotLogedinException;
}
