package com.qa.ims.persistence.domain;

import java.util.List;

public class Order {

	private Long id;
	private Long customerID;
	private List<Long> itemID;
	
	
	public Order(Long id, Long customerID, List<Long> itemID) {
		super();
		this.id = id;
		this.customerID = customerID;
		this.itemID = itemID;
	}
	
	public Long getId() {
		return id;
	}
	public Long getCustomerID() {
		return customerID;
	}
	public List<Long> getItemID() {
		return itemID;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}
	public void setItemID(List<Long> itemID) {
		this.itemID = itemID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerID == null) ? 0 : customerID.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((itemID == null) ? 0 : itemID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (customerID == null) {
			if (other.customerID != null)
				return false;
		} else if (!customerID.equals(other.customerID))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (itemID == null) {
			if (other.itemID != null)
				return false;
		} else if (!itemID.equals(other.itemID))
			return false;
		return true;
	}
	
	
}
