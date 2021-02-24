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
}
