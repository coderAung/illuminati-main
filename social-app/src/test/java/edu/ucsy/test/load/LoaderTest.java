package edu.ucsy.test.load;

import org.junit.jupiter.api.Test;

import edu.ucsy.test.db.CustomConnectorFactory;
import edu.ucsy.test.db.DatabaseInitializer;

public class LoaderTest {

	@Test
	void test() {
		var di = new DatabaseInitializer(CustomConnectorFactory.getConnectorWithPassword("admin"));
		di.loadUser();
		di.loadPost();
		di.loadPostImage();
	}
}
