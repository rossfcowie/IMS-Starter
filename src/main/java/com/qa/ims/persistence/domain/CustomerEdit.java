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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((CustomerID == null) ? 0 : CustomerID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerEdit other = (CustomerEdit) obj;
		if (CustomerID == null) {
			if (other.CustomerID != null)
				return false;
		} else if (!CustomerID.equals(other.CustomerID))
			return false;
		return true;
	}


	
	
}
