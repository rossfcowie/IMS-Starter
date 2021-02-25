package com.qa.ims.persistence.domain;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class OrderEditTest {
	
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(OrderEdit.class).verify();
	}
}
