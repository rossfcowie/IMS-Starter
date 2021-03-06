package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.IMS;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.User;
import com.qa.ims.utils.DBUtils;

public class ItemDAOTest {

	private final ItemDAO itemDAO = new ItemDAO();

	@Before
	public void setup() {
		IMS.userLogin = new User(1L,"admin","admin",4L);
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		final Item created = new Item(2L, "Asparagus", 1.55);
		assertEquals(created, itemDAO.create(created));
	}
	@Test
	public void testReadAll() {
		List<Item> expected = new ArrayList<>();
		expected.add(new Item(1L,"Carrot",1.25));
		assertEquals(expected, itemDAO.readAll());
	}
	@Test
	public void testReadLatest() {
		assertEquals(new Item(1L, "Carrot", 1.25), itemDAO.readLatest());
	}
	@Test
	public void testRead() {
		assertEquals(new Item(1L, "Carrot", 1.25), itemDAO.read(1L));
	}
	@Test
	public void testUpdate() {
		final Item updated = new Item(1L, "Asparagus", 1.55);
		assertEquals(updated, itemDAO.update(updated));
	}
	@Test
	public void testDelete() {
		assertEquals(1, itemDAO.delete(1));
	}
	
	@Test
	public void testCreateBadSQL() {
		DBUtils.connect("db.url=jdbc:h2:~/im");
		final Item created = new Item(2L, "Asparagus", 1.55);
		assertEquals(null, itemDAO.create(created));
	}
	@Test
	public void testReadAllBadSQL() {
		DBUtils.connect("db.url=jdbc:h2:~/im");
		List<Item> expected = new ArrayList<>();
		assertEquals(expected, itemDAO.readAll());
	}
	@Test
	public void testReadLatestBadSQL() {
		DBUtils.connect("db.url=jdbc:h2:~/im");
		assertEquals(null, itemDAO.readLatest());
	}
	@Test
	public void testReadBadSQL() {
		DBUtils.connect("db.url=jdbc:h2:~/im");
		assertEquals(null, itemDAO.read(1L));
	}
	@Test
	public void testUpdateBadSQL() {
		DBUtils.connect("db.url=jdbc:h2:~/im");
		final Item updated = new Item(1L, "Asparagus", 1.55);
		assertEquals(null, itemDAO.update(updated));
	}
	@Test
	public void testDeleteBadSQL() {
		DBUtils.connect("db.url=jdbc:h2:~/im");
		assertEquals(0, itemDAO.delete(1));
	}
}
