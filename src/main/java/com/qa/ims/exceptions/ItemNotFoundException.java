package com.qa.ims.exceptions;

public class ItemNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1319030538360418255L;
	
	public String getMessage() {
		return "No item could be found with the requested parameters.";
		
	}

}
