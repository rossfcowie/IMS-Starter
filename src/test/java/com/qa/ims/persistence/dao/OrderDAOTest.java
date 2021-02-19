package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTest {
	private final OrderDAO DAO = new OrderDAO();
	
	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreate() {
		final Order created = new Order(2L, 2L);
		assertEquals(created, DAO.create(created));
	}
	
	@Test
	public void testReadAll() {
		List<Order> expected = new ArrayList<>();
		expected.add(new Order(1L, 1L, 1L));
		assertEquals(expected, DAO.readAll());
	}
	
	@Test
	public void testReadLatest() {
		assertEquals(new Order(1L, 1L, 1L), DAO.readLatest());
	}
	
	@Test
	public void testRead() {
		final long ID = 1L;
		assertEquals(new Order(ID, 1L, 1L), DAO.read(ID));
	}
	@Test
	public void testUpdateAdd() {
		final long ID = 1L,IID =2L;
		List<Long> IIDS = new ArrayList<>();
		IIDS.add(IID);
		final Order added = new Order(ID,IIDS);
		IIDS.add(1L);
		
		assertEquals(new Order(ID, 1L, IIDS), DAO.update(added));
	}
	@Test
	public void testUpdateDelete() {
		final long ID = 1L,IID =1L;
		List<Long> IIDS = new ArrayList<>();
		IIDS.add(IID);
		List<Long> IIDS2 = new ArrayList<>();
		assertEquals(new Order(ID, 1L, IIDS2), DAO.update(ID,IIDS));
	}
	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}
}
