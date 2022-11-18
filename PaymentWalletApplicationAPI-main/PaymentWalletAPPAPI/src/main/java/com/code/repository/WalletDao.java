package com.code.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code.model.CurrentSessionUser;
import com.code.model.Wallet;
@Repository
public interface WalletDao extends JpaRepository<Wallet, Integer>{

	public Optional<Wallet> findByWalletId(Integer  walletId);
}
