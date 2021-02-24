package com.qa.ims.persistence.domain;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class UserTest {
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(User.class).verify();
	}

}
