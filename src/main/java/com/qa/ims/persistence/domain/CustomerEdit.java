package com.qa.ims.persistence.domain;

public class CustomerEdit extends Edit {
	private Long CustomerID;

	
	
	public CustomerEdit(String editor, String change, Long customerID) {
		super(editor, change);
		CustomerID = customerID;
	}

	public Long getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(Long customerID) {
		CustomerID = customerID;
	}
}
