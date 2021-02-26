package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.IMS;
import com.qa.ims.controller.UserController;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.User;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private UserDAO dao;


	@InjectMocks
	private UserController controller;
	
	@Before
	public void setup() {
		IMS.userLogin = new User(1L,"admin","admin",4L);
	}
	
	@Test
	public void testCreate() {
		final String UNAME = "barry";
		final Long PER = 1L;
		final User created = new User(UNAME,PER);

		Mockito.when(utils.getString()).thenReturn(UNAME);
		Mockito.when(utils.getPermissions()).thenReturn(PER);
		Mockito.when(dao.create(created)).thenReturn(created);
		
		assertEquals(created, controller.create());
		
		Mockito.verify(utils, Mockito.times(1)).getPermissions();
		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}

	@Test
	public void testReadAll() {
		final String UNAME = "barry";
		final Long PER = 1L;
		List<User> users = new ArrayList<>();
		users.add(new User(UNAME,PER));

		Mockito.when(dao.readAll()).thenReturn(users);

		assertEquals(users, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}
	
	@Test
	public void testUpdate() {
		final String UNAME = "barry", PASS = "password";
		final Long PER = 1L, ID = 1L;
		final User updated = new User(ID,UNAME,PASS,PER);

		Mockito.when(this.utils.getLong()).thenReturn(ID);
		Mockito.when(this.utils.getString()).thenReturn(UNAME, PASS);
		Mockito.when(this.dao.update(updated)).thenReturn(updated);
		Mockito.when(utils.getPermissions()).thenReturn(PER);

		assertEquals(updated, this.controller.update());

		Mockito.verify(this.utils, Mockito.times(1)).getLong();
		Mockito.verify(this.utils, Mockito.times(2)).getString();
		Mockito.verify(utils, Mockito.times(1)).getPermissions();
		Mockito.verify(this.dao, Mockito.times(1)).update(updated);
	}
	
	@Test
	public void testDelete() {
		final long ID = 1L;

		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(dao.delete(ID)).thenReturn(1);

		assertEquals(1L, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(ID);
	}
	
	

}
