package edu.ucsy.test.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.ucsy.test.db.CustomConnectorFactory;
import edu.ucsy.test.db.DatabaseInitializer;

public class UserModelTest {

	@BeforeAll
	static void init() {
		new DatabaseInitializer(CustomConnectorFactory.getConnectorWithAdmin())
					.loadUser();
	}
	
	@Test
	void test() {
		
	}
}
