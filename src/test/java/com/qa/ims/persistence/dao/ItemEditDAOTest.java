package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.IMS;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.ItemEdit;
import com.qa.ims.persistence.domain.User;
import com.qa.ims.utils.DBUtils;

public class ItemEditDAOTest {
	private final UserDAO UDAO = new UserDAO();
	private final ItemEditDAO EDAO = new ItemEditDAO();

	@Before
	public void setup() {
		IMS.userLogin = new User(1L,"admin","admin",4L);
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
		final Item created = new Item(2L, "Asparagus", 1.55);
		EDAO.recordCreate(created);
		UDAO.create(new User(1L, "admin", "admin",4L));
		
	
	}
	
	@Test
	public void testReadAll() {	
		
		List<ItemEdit> expected = new ArrayList<>();
		expected.add(new ItemEdit("admin", "Create",2L));
		assertEquals(expected, EDAO.readAll());
		
	}
	
	@Test
	public void testRecordCreate() {
		final Item created = new Item(3L, "Bike", 155.99);
		assertEquals(created, EDAO.recordCreate(created));
		List<ItemEdit> expected = new ArrayList<>();
		expected.add(new ItemEdit("admin", "Create",2L));
		expected.add(new ItemEdit("admin", "Create",3L));
		assertEquals(expected, EDAO.readAll());
	}
	
	@Test
	public void testrecordUpdate() {
		final Item updated = new Item(2L, "Bike", 155.99);
		assertEquals(updated, EDAO.recordUpdate(updated));
		List<ItemEdit> expected = new ArrayList<>();
		expected.add(new ItemEdit("admin", "Create",2L));
		expected.add(new ItemEdit("admin", "Update",2L));
		assertEquals(expected, EDAO.readAll());
	}
	
	@Test
	public void testrecordDelete() {
		Long ID = 1L;
		assertEquals(1, EDAO.recordDelete(ID));
		List<ItemEdit> expected = new ArrayList<>();
		expected.add(new ItemEdit("admin", "Create",2L));
		expected.add(new ItemEdit("admin", "Delete",1L));
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
		final Item created = new Item(3L, "Bike", 155.99);
		assertEquals(null, EDAO.recordCreate(created));
	}
	
	@Test
	public void testrecordUpdateBadSQL() {
		DBUtils.connect("db.url=jdbc:h2:~/im");
		final Item updated = new Item(2L, "Bike", 155.99);
		assertEquals(null, EDAO.recordUpdate(updated));
	}
	
	@Test
	public void testrecordDeleteBadSQL() {
		DBUtils.connect("db.url=jdbc:h2:~/im");
		Long ID = 1L;
		assertEquals(0, EDAO.recordDelete(ID));
	}
	
}