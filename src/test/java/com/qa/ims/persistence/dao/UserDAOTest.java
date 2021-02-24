package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.User;
import com.qa.ims.utils.DBUtils;

public class UserDAOTest {
	private final UserDAO DAO = new UserDAO();

	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");

	}

	@Test
	public void testCreate() {
	/*	try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("Show tables");) {
				while(resultSet.next()) {
					System.out.println(resultSet.getString(1));
				}
				
		}catch (Exception E) {
			System.out.println(E.getMessage());
		}
		*/
		final User created = new User(1L,"ChrisP","password",1L);
		assertEquals(created, DAO.create(created));
	}
	
	@Test
	public void testReadAll() {
		List<User> expected = new ArrayList<>();
		expected.add(DAO.create(new User(1L, "admin", "admin",4L)));
		assertEquals(expected, DAO.readAll());
	}
	
	@Test
	public void testReadLatest() {
		DAO.create(new User(1L, "admin", "admin",4L));
		assertEquals(new User(1L, "admin", "admin",4L), DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ID = 1L;final 
		User created = new User(1L,"ChrisP","password",1L);
		DAO.create(created);
		assertEquals(new User(1L,"ChrisP","password",1L), DAO.read(ID));
	}
	@Ignore
	@Test
	public void testUpdate() {
		final User updated = new User(1L,"ChrisP","password",1L);
		assertEquals(updated, DAO.update(updated));

	}
	@Ignore
	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}
	
}
