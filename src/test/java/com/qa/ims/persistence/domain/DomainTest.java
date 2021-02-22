package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class DomainTest {

	@Mock
	private Utils utils;
	
	private Domain domain;
	
	@Test
	public void testGetDomain() {
		Mockito.when(utils.getString()).thenReturn("Customer");
		domain = Domain.getDomain(utils);
		assertEquals(Domain.CUSTOMER,domain);
		Mockito.verify(utils, Mockito.times(1)).getString();
	}
	@Test
	public void testGetDescription() {
		domain = Domain.CUSTOMER;
		assertEquals("CUSTOMER: Information about customers" ,domain.getDescription());
	}
	
}
