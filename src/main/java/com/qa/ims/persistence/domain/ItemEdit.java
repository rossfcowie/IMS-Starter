package com.qa.ims.persistence.domain;

public class ItemEdit  extends Edit {
	private Long ItemID;

	@Override
	public String toString() {
		return "ItemEdit [ItemID=" + ItemID + "," + super.toString();
	}
	
	public ItemEdit(String editor, String change, Long itemID) {
		super(editor, change);
		ItemID = itemID;
	}

	public Long getItemID() {
		return ItemID;
	}

	public void setItemID(Long itemID) {
		ItemID = itemID;
	}
}
