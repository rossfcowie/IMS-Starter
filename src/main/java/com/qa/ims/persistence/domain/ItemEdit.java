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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((ItemID == null) ? 0 : ItemID.hashCode());
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
		ItemEdit other = (ItemEdit) obj;
		if (ItemID == null) {
			if (other.ItemID != null)
				return false;
		} else if (!ItemID.equals(other.ItemID))
			return false;
		return true;
	}
	
}
