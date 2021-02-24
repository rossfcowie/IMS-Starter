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
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderEdit;
import com.qa.ims.persistence.domain.User;
import com.qa.ims.utils.DBUtils;

public class OrderEditDAOTest {
	private final UserDAO UDAO = new UserDAO();
	private final OrderDAO DAO = new OrderDAO();
	private final OrderEditsDAO EDAO = new OrderEditsDAO();

	@Before
	public void setup() {
		IMS.userLogin = new User(1L,"admin","admin",4L);
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
		final Order created = new Order(2L, 2L);
		DAO.create(created);
		UDAO.create(new User(1L, "admin", "admin",4L));
		
	
	}
	
	@Test
	public void testReadAll() {	
		
		List<OrderEdit> expected = new ArrayList<>();
		expected.add(new OrderEdit("admin", "Create",2L));
		assertEquals(expected, EDAO.readAll());
	}
	
}
