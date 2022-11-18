package com.code.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code.model.Customer;

@Repository
public interface CustomerDAO extends JpaRepository<Customer, Integer>{
	
	public Optional<Customer> findByUserName(String userName);
	
	public Optional<Customer>findByMobileNo(String mobileNo);
}
