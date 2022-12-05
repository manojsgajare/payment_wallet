package com.code.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.exception.InsufficientBalanceException;
import com.code.exception.UserNotLogedinException;
import com.code.model.BillPayment;
import com.code.model.CurrentSessionUser;
import com.code.model.Customer;
import com.code.model.Transaction;
import com.code.model.Wallet;
import com.code.repository.BankAccountDao;
import com.code.repository.BillPaymentDao;
import com.code.repository.CustomerDAO;
import com.code.repository.SessionDAO;
import com.code.repository.TransactionDao;
import com.code.repository.WalletDao;

@Service
public class BillPaymentServiceImpl implements BillPaymentService {

	@Autowired
	private BillPaymentDao billDao;

	@Autowired
	private SessionDAO sessionDao;

	@Autowired
	private CustomerDAO cDao;

	@Autowired
	private BankAccountDao bankAccoundDao;

	@Autowired
	private WalletDao walletDao;

	@Autowired
	private TransactionDao transactionDao;

	@Override
	public BillPayment makeBillPayment(BillPayment billpayment, String uniqueId)
			throws InsufficientBalanceException, UserNotLogedinException {
		Optional<CurrentSessionUser> currentUser = sessionDao.findByUuid(uniqueId);

		if (!currentUser.isPresent()) {
			throw new UserNotLogedinException("Please Login first");
		}

		Optional<Customer> customer = cDao.findById(currentUser.get().getUserId());
		Wallet wallet = customer.get().getWallet();

		if (wallet.getBalance() < billpayment.getAmount()) {
			throw new InsufficientBalanceException("Insufficient balance in wallet, Add money to your wallet");
		}

		wallet.setBalance(wallet.getBalance() - billpayment.getAmount());
		walletDao.save(wallet);

		billpayment.setWalletId(wallet.getWalletId());
		billpayment.setTime(LocalDateTime.now());

		BillPayment completedPayment = billDao.save(billpayment);

		if (completedPayment != null) {
			Transaction transaction = new Transaction();
			transaction.setDescription(billpayment.getBilltype() + " successfull");
			transaction.setAmount(billpayment.getAmount());
			transaction.setTransactionDate(LocalDateTime.now());
			transaction.setTransactionType(billpayment.getTransactionType());
			transaction.setWalletId(wallet.getWalletId());
			wallet.getTransaction().add(transaction);
			transactionDao.save(transaction);
		}
		System.out.println(billpayment);
		return completedPayment;
	}

	@Override
	public Set<BillPayment> viewBillPayments(String uniqueId) throws UserNotLogedinException {

		Optional<CurrentSessionUser> currentUser = sessionDao.findByUuid(uniqueId);

		if (!currentUser.isPresent()) {
			throw new UserNotLogedinException("Please Login first");
		}

		Optional<Customer> customer = cDao.findById(currentUser.get().getUserId());
		Wallet wallet = customer.get().getWallet();

		Set<BillPayment> billpaymnets = billDao.findByWalletId(wallet.getWalletId());
		return billpaymnets;
	}

}
