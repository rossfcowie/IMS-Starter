package com.qa.ims.exceptions;

public class OrderNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5091831206775243253L;
	
	public String getMessage() {
		return "No order could be found with the requested parameters.";
		
	}
}
