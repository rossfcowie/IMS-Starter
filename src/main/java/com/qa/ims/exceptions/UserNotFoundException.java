package com.qa.ims.exceptions;

public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4833653263699343723L;

	public String getMessage() {
		return "No user could be found with the requested parameters.";
		
	}
}
