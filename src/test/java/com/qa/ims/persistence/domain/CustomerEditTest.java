package com.qa.ims.persistence.domain;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class CustomerEditTest {
	
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(CustomerEdit.class).verify();
	}
}
