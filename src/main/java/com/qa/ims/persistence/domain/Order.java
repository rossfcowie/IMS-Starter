package com.qa.ims.persistence.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {

	private Long id;
	private Long customerID;
	private List<Long> itemIDs;
	
	
	@Override
	public String toString() {
		return "id=" + id + ", customerID=" + customerID + ", itemIDs=" + itemIDs;
	}

	public Order(Long id,Long customerID) {
		super();
		this.id = id;
		this.customerID = customerID;
		this.itemIDs = new ArrayList<>();
	}
	
	public Order(Long id, Long customerID, List<Long> itemIDs) {
		super();
		this.id = id;
		this.customerID = customerID;
		this.itemIDs = itemIDs;
	}
	
	public Long getId() {
		return id;
	}
	public Long getCustomerID() {
		return customerID;
	}
	public List<Long> getItemIDs() {
		return itemIDs;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}
	public void setItemIDs(List<Long> itemIDs) {
		this.itemIDs = itemIDs;
	}
	public void addItemID(Long itemID) {
		this.itemIDs.add(itemID);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerID == null) ? 0 : customerID.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((itemIDs == null) ? 0 : itemIDs.hashCode());
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
		if (itemIDs == null) {
			if (other.itemIDs != null)
				return false;
		} else if (!itemIDs.equals(other.itemIDs))
			return false;
		return true;
	}
	
	
}
