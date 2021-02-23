package com.qa.ims.exceptions;

public class CustomerNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1319030538360418254L;
	
	public String getMessage() {
		return "No customer could be found with the requested parameters.";
		
	}
	
	
}
