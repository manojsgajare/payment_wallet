package com.code.exception;

public class WalletNotFound extends RuntimeException{

	public WalletNotFound() {
		
	}
	
	public WalletNotFound(String message) {
		super(message);
	}
	
}
