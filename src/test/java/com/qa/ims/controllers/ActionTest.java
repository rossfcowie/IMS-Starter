package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.Action;
import com.qa.ims.persistence.domain.Domain;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class ActionTest {

	@Mock
	private Utils utils;
	
	private Action action;
	
	@Test
	public void testGetAction() {
		Mockito.when(utils.getString()).thenReturn("Create");
		action = Action.getAction(utils);
		assertEquals(Action.CREATE,action);
		Mockito.verify(utils, Mockito.times(1)).getString();
	}
	@Test
	public void testGetDescription() {
		action = Action.CREATE;
		assertEquals("CREATE: To save a new entity into the database" ,action.getDescription());
	}

	@Test
	public void testPrintActions() {
		for (Domain d : Domain.values()) {
			for(int i=1;i<=4;i++) {
				Action.printActions(i,d);
			}
		}
		
	}
	
	@Test
	public void testGetActionAdmin() {
		Mockito.when(utils.getString()).thenReturn("Create");
		for (Domain d : Domain.values()) {
			if(d.equals(Domain.STOP)) {
				
			}else {
			action = Action.getAction(utils,4,d);
			assertEquals(Action.CREATE,action);
		}
		}
		Mockito.verify(utils, Mockito.times(4)).getString();
	}
	
	@Test
	public void testGetActionManager() {
		Mockito.when(utils.getString()).thenReturn("Read");
		action = Action.getAction(utils,3,Domain.CUSTOMER);
		assertEquals(Action.READ,action);
		Mockito.verify(utils, Mockito.times(1)).getString();
	}
	
	@Test
	public void testGetActionWorker() {
		Mockito.when(utils.getString()).thenReturn("Read");
		action = Action.getAction(utils,2,Domain.ORDER);
		assertEquals(Action.READ,action);
		Mockito.verify(utils, Mockito.times(1)).getString();
	}
	
	@Test
	public void testGetActionCustomer() {
		Mockito.when(utils.getString()).thenReturn("Read");
		action = Action.getAction(utils,1,Domain.ITEM);
		assertEquals(Action.READ,action);
		Mockito.verify(utils, Mockito.times(1)).getString();
	}
}
