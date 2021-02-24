package com.qa.ims.exceptions;

public class ItemNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4626742359157269245L;

	public String getMessage() {
		return "No item could be found with the requested parameters.";
		
	}

}
