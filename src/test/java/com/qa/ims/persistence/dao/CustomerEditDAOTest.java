package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.IMS;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.CustomerEdit;
import com.qa.ims.persistence.domain.User;
import com.qa.ims.utils.DBUtils;

public class CustomerEditDAOTest {
	private final UserDAO UDAO = new UserDAO();
	private final CustomerDAO DAO = new CustomerDAO();
	private final CustomerEditDAO EDAO = new CustomerEditDAO();

	@Before
	public void setup() {
		IMS.userLogin = new User(1L,"admin","admin",4L);
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
		UDAO.create(new User(1L, "admin", "admin",4L));
		final Customer created = new Customer(2L, "chris", "perrins");
		DAO.create(created);
		
		
	
	}
	
	@Test
	public void testReadAll() {	
		
		List<CustomerEdit> expected = new ArrayList<>();
		expected.add(new CustomerEdit("admin", "Create",2L));
		assertEquals(expected, EDAO.readAll());
	}
	
}

