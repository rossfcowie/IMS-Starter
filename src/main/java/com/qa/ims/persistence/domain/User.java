package com.qa.ims.persistence.domain;

public class User {
	private Long id;
	private String username;
	private String password;
	private Long permission;
	
	
	public User(Long id, String username, String password, Long permission) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.permission = permission;
	}
	public User(String username, Long permission) {
		this.username = username;
		this.password = "password";
		this.permission = permission;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", permission=" + permission
				+ "]";
	}
	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public boolean comparePassword(String password) {
		return this.password==password;
	}

	public int getPermission() {
		return Integer.parseInt(Long.toString(permission)) ;
	}

	public String getLevel() {
		switch(this.getPermission()){
		
	case 4:
		return "System Admin";
	case 3:
		return "Manager";
	case 2:
		return "Worker";
	case 1:
		return "Customer";
	default:
		return "Error, permission level not acceptable";
		}
	}
	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPermission(Long permission) {
		this.permission = permission;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((permission == null) ? 0 : permission.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (permission == null) {
			if (other.permission != null)
				return false;
		} else if (!permission.equals(other.permission))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	
	
}
