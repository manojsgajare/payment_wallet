package com.code.exception;

public class NotAnyBankAddedYet extends RuntimeException {

	public NotAnyBankAddedYet() {
	}

	public NotAnyBankAddedYet(String message) {
		super(message);
	}


}
