package com.qa.ims.persistence.domain;

public class CustomerEdit extends Edit {
	private Long CustomerID;

	
	
	@Override
	public String toString() {
		return "CustomerEdit [CustomerID=" + CustomerID + "," + super.toString();
	}

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
