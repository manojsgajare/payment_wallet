package com.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.code.model.Customer;
import com.code.model.LogIn;

@Repository
public interface LogInDAO extends JpaRepository<LogIn, Integer>{
	
	
}
