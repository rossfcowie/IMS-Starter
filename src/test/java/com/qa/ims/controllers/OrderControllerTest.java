package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
	
	@Mock
	private Utils utils;

	@Mock
	private OrderDAO dao;

	@InjectMocks
	private OrderController controller;
	
	@Test
	public void testReadAll() {
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1L, 1L, 1L));

		Mockito.when(dao.readAll()).thenReturn(orders);

		assertEquals(orders, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}
	@Test
	public void testCreate() {
		final Long CID = 1L;
		final Order created = new Order(CID);

		Mockito.when(utils.getLong()).thenReturn(CID);
		Mockito.when(dao.create(created)).thenReturn(created);

		assertEquals(created, controller.create());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}

	@Test
	public void testUpdateAdd() {
		final Long OID = 1L, IID=1L;
		List<Long> IIDS = new ArrayList<>();
		IIDS.add(IID);
		final Order added = new Order(OID,IIDS);

		Mockito.when(this.utils.getLong()).thenReturn(1L,1L);
		Mockito.when(this.utils.getString()).thenReturn("add");
		Mockito.when(this.dao.update(added)).thenReturn(added);

		assertEquals(added, this.controller.update());

		Mockito.verify(this.utils, Mockito.times(2)).getLong();
		Mockito.verify(this.utils, Mockito.times(1)).getString();
		Mockito.verify(this.dao, Mockito.times(1)).update(added);
	}
	@Test
	public void testUpdateAddThenRemove() {	
		final Long OID = 1L, IID=1L;
		List<Long> IIDS = new ArrayList<>();
		IIDS.add(IID);
		final Order added = new Order(OID,IIDS);

		Mockito.when(this.utils.getLong()).thenReturn(OID,IID);
		Mockito.when(this.utils.getString()).thenReturn("add");
		Mockito.when(this.dao.update(added)).thenReturn(added);

		assertEquals(added, this.controller.update());
		
		
		List<Long> IIDS2 = new ArrayList<>();
		final Order removed = new Order(OID,IIDS2);

		Mockito.when(this.utils.getLong()).thenReturn(OID,IID);
		Mockito.when(this.utils.getString()).thenReturn("remove");
		Mockito.when(this.dao.update(OID,IIDS)).thenReturn(removed);

		assertEquals(removed, this.controller.update());

		Mockito.verify(this.utils, Mockito.times(4)).getLong();
		Mockito.verify(this.utils, Mockito.times(2)).getString();
		Mockito.verify(this.dao, Mockito.times(1)).update(OID,IIDS);
	}
	
}
