package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.Action;
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

}
