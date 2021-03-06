package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.IMS;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.CustomerEdit;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderEdit;
import com.qa.ims.persistence.domain.User;
import com.qa.ims.utils.DBUtils;

public class OrderEditDAOTest {
	private final UserDAO UDAO = new UserDAO();
	private final OrderEditsDAO EDAO = new OrderEditsDAO();

	@Before
	public void setup() {
		IMS.userLogin = new User(1L,"admin","admin",4L);
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
		final Order created = new Order(2L, 2L);
		EDAO.recordCreate(created);
		UDAO.create(new User(1L, "admin", "admin",4L));
		
	
	}
	
	@Test
	public void testReadAll() {	
		
		List<OrderEdit> expected = new ArrayList<>();
		expected.add(new OrderEdit("admin", "Create",2L));
		assertEquals(expected, EDAO.readAll());
	}
	
	@Test
	public void testRecordCreate() {
		final Order created = new Order(3L, 3L);
		assertEquals(created, EDAO.recordCreate(created));
		List<OrderEdit> expected = new ArrayList<>();
		expected.add(new OrderEdit("admin", "Create",2L));
		expected.add(new OrderEdit("admin", "Create",3L));
		assertEquals(expected, EDAO.readAll());
	}
	
	@Test
	public void testRecordAdd() {
		Order updated = new Order(2L, 3L);
		updated.addItemID(1L);
		assertEquals(updated, EDAO.recordAdd(updated));
		List<OrderEdit> expected = new ArrayList<>();
		expected.add(new OrderEdit("admin", "Create",2L));
		expected.add(new OrderEdit("admin", "Add",2L));
		assertEquals(expected, EDAO.readAll());
	}
	
	
	@Test
	public void testRecordRemove() {
		Order updated = new Order(2L, 3L);
		updated.addItemID(1L);
		assertEquals(updated, EDAO.recordRemove(updated));
		List<OrderEdit> expected = new ArrayList<>();
		expected.add(new OrderEdit("admin", "Create",2L));
		expected.add(new OrderEdit("admin", "Remove",2L));
		assertEquals(expected, EDAO.readAll());
	}
	
	@Test
	public void testrecordDelete() {
		Long ID = 1L;
		assertEquals(1, EDAO.recordDelete(ID));
		List<OrderEdit> expected = new ArrayList<>();
		expected.add(new OrderEdit("admin", "Create",2L));
		expected.add(new OrderEdit("admin", "Delete",1L));
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
		final Order created = new Order(2L, 3L);
		assertEquals(null, EDAO.recordCreate(created));
	}
	
	@Test
	public void testrecordAddBadSQL() {
		DBUtils.connect("db.url=jdbc:h2:~/im");
		Order updated = new Order(2L, 3L);
		assertEquals(null, EDAO.recordAdd(updated));
	}
	
	@Test
	public void testrecordRemoveBadSQL() {
		DBUtils.connect("db.url=jdbc:h2:~/im");
		Order updated = new Order(2L, 3L);
		assertEquals(null, EDAO.recordRemove(updated));
	}
	
	@Test
	public void testrecordDeleteBadSQL() {
		DBUtils.connect("db.url=jdbc:h2:~/im");
		Long ID = 1L;
		assertEquals(0, EDAO.recordDelete(ID));
	}
	
}
