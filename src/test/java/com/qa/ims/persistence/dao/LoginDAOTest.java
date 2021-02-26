package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.IMS;
import com.qa.ims.persistence.domain.User;
import com.qa.ims.utils.DBUtils;

public class LoginDAOTest {
	private final UserDAO UDAO = new UserDAO();
	private final LoginDAO DAO = new LoginDAO();
	
	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
		UDAO.create(new User(1L, "admin", "admin",4L));
	}
	@Test
	public void testLogin() {
		final String USERNAME = "admin";
		final String PASSWORD = "admin";
		final User created = new User(1L,USERNAME,PASSWORD,4L);
		assertEquals(created, DAO.attemptLogin(USERNAME,PASSWORD));
	}
}
