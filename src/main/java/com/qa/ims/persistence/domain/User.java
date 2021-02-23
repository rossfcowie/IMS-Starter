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


	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public boolean comparePassword(String password) {
		return this.password==password;
	}

	public int getPermission() {
		return Integer.parseInt(Long.toString(permission)) ;
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
	
	
	
}
