package com.code.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.exception.BankAccountNotExsists;
import com.code.exception.BankAlreadyAdded;
import com.code.exception.NotAnyBankAddedYet;
import com.code.exception.UserNotLogedinException;
import com.code.model.BankAccount;
import com.code.model.CurrentSessionUser;
import com.code.model.Customer;
import com.code.model.Wallet;
import com.code.repository.BankAccountDao;
import com.code.repository.CustomerDAO;
import com.code.repository.LogInDAO;
import com.code.repository.SessionDAO;

import java.util.*;

@Service
public class BanKAccountServiceImpl implements BankAccountService {

	@Autowired
	private BankAccountDao bankDao;

	@Autowired
	private SessionDAO sessionDao;

	@Autowired
	private CustomerDAO cDao;

	@Autowired
	private LogInDAO logInDAO;

	@Override
	public BankAccount addBank(BankAccount bankAccount, String uniqueId)
			throws UserNotLogedinException, BankAlreadyAdded {

		Optional<CurrentSessionUser> currentUser = sessionDao.findByUuid(uniqueId);

		if (!currentUser.isPresent()) {
			throw new UserNotLogedinException("Please Login first");
		}

		Optional<Customer> customer = cDao.findById(currentUser.get().getUserId());
		Wallet wallet = customer.get().getWallet();

		Optional<BankAccount> bankAc = bankDao.findById(bankAccount.getAccountNumber());

		if (bankAc.isPresent()) {
			throw new BankAlreadyAdded(
					"Bank with " + bankAccount.getAccountNumber() + " this Account Nuber Already Exist");
		}

		System.out.println(wallet.getWalletId());
		bankAccount.setWalletId(wallet.getWalletId());
		return bankDao.save(bankAccount);

	}

	@Override
	public BankAccount removeBank(Integer accountNumber, String uniqueId)
			throws BankAccountNotExsists, UserNotLogedinException {
		Optional<CurrentSessionUser> currentUser = sessionDao.findByUuid(uniqueId);

		if (!currentUser.isPresent()) {
			throw new UserNotLogedinException("Please Login first");
		}

		Optional<BankAccount> bankAccount = bankDao.findById(accountNumber);

		bankDao.delete(bankAccount.get());

		return bankAccount.get();

	}

	@Override
	public BankAccount viewBankAccountI(Integer accountNumber, String uniqueId)
			throws BankAccountNotExsists, UserNotLogedinException {

		Optional<CurrentSessionUser> currentUser = sessionDao.findByUuid(uniqueId);

		if (!currentUser.isPresent()) {
			throw new UserNotLogedinException("Please Login first");
		}

		Optional<BankAccount> bankAccount = bankDao.findById(accountNumber);

		if (bankAccount.isPresent()) {
			return bankAccount.get();
		} else {
			throw new BankAccountNotExsists(
					"Bank account is not existed with current account Number :" + accountNumber);
		}

	}

	@Override
	public BankAccount viewAllAccount(String uniqueId)
			throws UserNotLogedinException, NotAnyBankAddedYet, BankAccountNotExsists {
		Optional<CurrentSessionUser> currentUser = sessionDao.findByUuid(uniqueId);

		if (!currentUser.isPresent()) {
			throw new UserNotLogedinException("Please Login first");
		}

		Optional<Customer> customer = cDao.findById(currentUser.get().getUserId());
		Wallet wallet = customer.get().getWallet();

		BankAccount bankAccounts = bankDao.findByWalletId(wallet.getWalletId());

		if (bankAccounts != null) {
			return bankAccounts;
		} else {
			throw new BankAccountNotExsists("Bank account is not existed in current user ");
		}

	}

}
