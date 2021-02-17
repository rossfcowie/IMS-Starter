package com.qa.ims.persistence.domain;

public class Item {

	private Long id;
	private String name;
	private float value;
	

	public Item(Long id, String name, float value) {
		this.setId(id);
		this.setName(name);
		this.setValue(value);
	}
	public Item(String name, float value) {
		this.setName(name);
		this.setValue(value);
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public float getValue() {
		return value;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setValue(float value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", value=" + value;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Float.floatToIntBits(value);
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
		Item other = (Item) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Float.floatToIntBits(value) != Float.floatToIntBits(other.value))
			return false;
		return true;
	}
}
