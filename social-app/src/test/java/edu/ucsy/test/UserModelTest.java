package edu.ucsy.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import edu.ucsy.social.data.Model;
import edu.ucsy.social.data.ModelFactory;
import edu.ucsy.social.model.entity.User;

public class UserModelTest {

	@Test
	void test() {
		Model<User> userModel = ModelFactory.getModel(User.class, null);
		assertNotNull(userModel);
	}
}
