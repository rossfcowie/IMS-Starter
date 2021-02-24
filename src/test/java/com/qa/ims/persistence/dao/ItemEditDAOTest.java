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
	private final ItemDAO DAO = new ItemDAO();
	private final ItemEditDAO EDAO = new ItemEditDAO();

	@Before
	public void setup() {
		IMS.userLogin = new User(1L,"admin","admin",4L);
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
		final Item created = new Item(2L, "Asparagus", 1.55);
		DAO.create(created);
		UDAO.create(new User(1L, "admin", "admin",4L));
		
	
	}
	
	@Test
	public void testReadAll() {	
		
		List<ItemEdit> expected = new ArrayList<>();
		expected.add(new ItemEdit("admin", "Create",2L));
		assertEquals(expected, EDAO.readAll());
	}
	
}