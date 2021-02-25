package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.IMS;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.User;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTest {
	private final OrderDAO DAO = new OrderDAO();
	
	@Before
	public void setup() {
		IMS.userLogin = new User(1L,"admin","admin",4L);
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreate() {
		/*try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("Show tables");) {
				while(resultSet.next()) {
					System.out.println(resultSet.getString(1));
				}
				
		}catch (Exception E) {
			System.out.println(E.getMessage());
		}
		*/
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
	@Test
	public void testOrderCost() {
		Double cost = 1.25;
		assertEquals(cost,DAO.getOrderCost(1L));
		
}
	
	@Test
	public void testCreateBadSQL() {
		DBUtils.connect("db.url=jdbc:h2:~/im");
		final Order created = new Order(2L, 2L);
		assertEquals(null, DAO.create(created));
	}
	
	@Test
	public void testReadAllBadSQL() {
		DBUtils.connect("db.url=jdbc:h2:~/im");
		List<Order> expected = new ArrayList<>();
		assertEquals(expected, DAO.readAll());
	}
	
	@Test
	public void testReadLatestBadSQL() {
		assertEquals(new Order(1L, 1L, 1L), DAO.readLatest());
	}
	
	@Test
	public void testReadBadSQL() {
		DBUtils.connect("db.url=jdbc:h2:~/im");
		final long ID = 1L;
		assertEquals(null, DAO.read(ID));
	}
	@Test
	public void testUpdateAddBadSQL() {

		DBUtils.connect("db.url=jdbc:h2:~/im");
		final long ID = 1L,IID =2L;
		List<Long> IIDS = new ArrayList<>();
		IIDS.add(IID);
		final Order added = new Order(ID,IIDS);
		
		assertEquals(null, DAO.update(added));
	}
	@Test
	public void testUpdateDeleteBadSQL() {
		DBUtils.connect("db.url=jdbc:h2:~/im");
		final long ID = 1L,IID =1L;
		List<Long> IIDS = new ArrayList<>();
		IIDS.add(IID);
		assertEquals(null, DAO.update(ID,IIDS));
	}
	@Test
	public void testDeleteBadSQL() {
		DBUtils.connect("db.url=jdbc:h2:~/im");
		assertEquals(0, DAO.delete(1));
	}
	@Test
	public void testOrderCostBadSQL() {
		DBUtils.connect("db.url=jdbc:h2:~/im");
		
		Double cost = 0D;
		assertEquals(cost,DAO.getOrderCost(1L));
	}
}
