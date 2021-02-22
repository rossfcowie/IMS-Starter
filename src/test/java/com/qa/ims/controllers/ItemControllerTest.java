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

import com.qa.ims.controller.ItemController;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {

	@Mock
	private Utils utils;
	@Mock
	private ItemDAO itemDAO;
	
	@InjectMocks
	private ItemController itemController;
	
	@Test
	public void testCreate() {
		final String NAME = "Apple";
		final Double VALUE = 1.0;
		final Item created = new Item(NAME, VALUE);

		Mockito.when(utils.getString()).thenReturn(NAME);
		Mockito.when(utils.getDouble()).thenReturn(VALUE);
		Mockito.when(itemDAO.create(created)).thenReturn(created);

		assertEquals(created, itemController.create());

		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(utils, Mockito.times(1)).getDouble();
		Mockito.verify(itemDAO, Mockito.times(1)).create(created);
	}
	
	@Test
	public void testReadAll() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(1L, "Apple", 1.0));

		Mockito.when(itemDAO.readAll()).thenReturn(items);

		assertEquals(items, itemController.readAll());

		Mockito.verify(itemDAO, Mockito.times(1)).readAll();
	}
	
	@Test
	public void testUpdate() {
		final String NAME = "Apple";
		final Double VALUE = 1.0;
		final Long ID = 1L;
		final Item updated = new Item(ID,NAME, VALUE);

		Mockito.when(utils.getString()).thenReturn(NAME);
		Mockito.when(utils.getDouble()).thenReturn(VALUE);
		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(itemDAO.update(updated)).thenReturn(updated);

		assertEquals(updated, itemController.update());

		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(utils, Mockito.times(1)).getDouble();
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(itemDAO, Mockito.times(1)).update(updated);
	}
	@Test
	public void testDelete() {
		final Long ID = 1L;
		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(itemDAO.delete(ID)).thenReturn(1);

		assertEquals(1L, itemController.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(itemDAO, Mockito.times(1)).delete(ID);
	}
}