package com.qa.ims.persistence.domain;

public class OrderEdit extends Edit {
	private Long OrderID;

	@Override
	public String toString() {
		return "OrderEdit [OrderID=" + OrderID + "," + super.toString();
	}
	
	public OrderEdit(String editor, String change, Long orderID) {
		super(editor, change);
		OrderID = orderID;
	}

	public Long getOrderID() {
		return OrderID;
	}

	public void setOrderID(Long orderID) {
		OrderID = orderID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((OrderID == null) ? 0 : OrderID.hashCode());
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
		OrderEdit other = (OrderEdit) obj;
		if (OrderID == null) {
			if (other.OrderID != null)
				return false;
		} else if (!OrderID.equals(other.OrderID))
			return false;
		return true;
	}
	
	
}
