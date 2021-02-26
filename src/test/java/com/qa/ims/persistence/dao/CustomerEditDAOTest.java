package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.IMS;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.CustomerEdit;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.ItemEdit;
import com.qa.ims.persistence.domain.User;
import com.qa.ims.utils.DBUtils;

public class CustomerEditDAOTest {
	private final UserDAO UDAO = new UserDAO();
	private final CustomerEditDAO EDAO = new CustomerEditDAO();

	@Before
	public void setup() {
		IMS.userLogin = new User(1L,"admin","admin",4L);
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
		UDAO.create(new User(1L, "admin", "admin",4L));
		final Customer created = new Customer(2L, "chris", "perrins");
		EDAO.recordCreate(created);
		
		
	
	}
	
	@Test
	public void testReadAll() {	
		
		List<CustomerEdit> expected = new ArrayList<>();
		expected.add(new CustomerEdit("admin", "Create",2L));
		assertEquals(expected, EDAO.readAll());
	}
	
	@Test
	public void testRecordCreate() {
		final Customer created = new Customer(3L, "chris", "perrins");
		assertEquals(created, EDAO.recordCreate(created));
		List<CustomerEdit> expected = new ArrayList<>();
		expected.add(new CustomerEdit("admin", "Create",2L));
		expected.add(new CustomerEdit("admin", "Create",3L));
		assertEquals(expected, EDAO.readAll());
	}
	
	@Test
	public void testrecordUpdate() {
		final Customer updated = new Customer(2L, "Christopher", "perrins");
		assertEquals(updated, EDAO.recordUpdate(updated));
		List<CustomerEdit> expected = new ArrayList<>();
		expected.add(new CustomerEdit("admin", "Create",2L));
		expected.add(new CustomerEdit("admin", "Update",2L));
		assertEquals(expected, EDAO.readAll());
	}
	
	@Test
	public void testrecordDelete() {
		Long ID = 1L;
		assertEquals(1, EDAO.recordDelete(ID));
		List<CustomerEdit> expected = new ArrayList<>();
		expected.add(new CustomerEdit("admin", "Create",2L));
		expected.add(new CustomerEdit("admin", "Delete",1L));
		assertEquals(expected, EDAO.readAll());
	}
	
	@Test
	public void testReadAllBadSQL() {	
		DBUtils.connect("db.url=jdbc:h2:~/im");
		assertEquals(null, EDAO.readAll());
		
	}
	
	@Test
	public void testRecordCreateBadSQL() {
		DBUtils.connect("db.url=jdbc:h2:~/im");
		final Customer created = new Customer(2L, "chris", "perrins");
		assertEquals(null, EDAO.recordCreate(created));
	}
	
	@Test
	public void testrecordUpdateBadSQL() {
		DBUtils.connect("db.url=jdbc:h2:~/im");
		final Customer updated = new Customer(2L, "Christopher", "perrins");
		assertEquals(null, EDAO.recordUpdate(updated));
	}
	
	@Test
	public void testrecordDeleteBadSQL() {
		DBUtils.connect("db.url=jdbc:h2:~/im");
		Long ID = 1L;
		assertEquals(0, EDAO.recordDelete(ID));
	}
	
}

