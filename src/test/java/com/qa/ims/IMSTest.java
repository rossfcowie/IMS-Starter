package com.qa.ims;

import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.domain.Domain;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class IMSTest {

	
	
	@Mock
	private Utils utils;
	private Domain domain;
	
	
	@InjectMocks
	IMS ims = new IMS();
	@Test
	public void constructorTest() {
		ims = null;
		ims = new IMS();
		assertTrue(ims instanceof IMS);
	}
	/* test runs the program, but I cannot exit out of it with mockito
	@Test
	public void imsSystemTest() {
		ims.imsSystem();
		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(domain, Mockito.times(1)).getDomain(utils);
	}
	*/

}
