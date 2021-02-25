package com.qa.ims.persistence.domain;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class ItemEditTest {
	
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(ItemEdit.class).verify();
	}
}
