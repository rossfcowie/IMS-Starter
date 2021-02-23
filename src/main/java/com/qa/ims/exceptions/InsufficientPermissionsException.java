package com.qa.ims.exceptions;

public class InsufficientPermissionsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1319030538360418255L;
	
	public String getMessage() {
		return "This user account does not have permission to do that.";
		
	}

}
