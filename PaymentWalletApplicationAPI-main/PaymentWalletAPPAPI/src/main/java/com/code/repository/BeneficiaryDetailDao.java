package com.code.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code.exception.BeneficiaryDetailException;
import com.code.model.BeneficiaryDetail;

@Repository
public interface BeneficiaryDetailDao extends JpaRepository<BeneficiaryDetail, Integer>{

	public List<BeneficiaryDetail> findBybeneficiaryMobileNo(String beneficiaryMobileNo) throws BeneficiaryDetailException;
	public List<BeneficiaryDetail> findByWalletId(Integer walletId) throws BeneficiaryDetailException;

}
