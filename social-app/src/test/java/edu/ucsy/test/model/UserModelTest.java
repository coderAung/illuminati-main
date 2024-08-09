package edu.ucsy.test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import edu.ucsy.social.data.Model;
import edu.ucsy.social.data.ModelFactory;
import edu.ucsy.social.model.UserModel;
import edu.ucsy.social.model.entity.User;
import edu.ucsy.test.db.CustomConnectorFactory;
import edu.ucsy.test.db.DatabaseInitializer;

//@Disabled
@TestMethodOrder(OrderAnnotation.class)
public class UserModelTest {
	
	private static DatabaseInitializer di;
	private static Model<User> model;

	@BeforeAll
	static void init() {
		di = new DatabaseInitializer(CustomConnectorFactory.getConnectorWithAdmin());
		di.truncate("users");
		
		model = ModelFactory.getModel(User.class, CustomConnectorFactory.getConnectorWithAdmin());
	}
	
	@Order(1)
	@ParameterizedTest
	@CsvFileSource(
			files = {"test-source/users.txt"},
			delimiter = '\t')
	void test_save(long id, String email, String name, String password) {
		var user = new User(email, name, password);
		var savedUser = model.save(user);
		
		assertNotNull(savedUser);
		assertEquals(id, savedUser.id());
		assertEquals(email, savedUser.email());
		assertEquals(name, savedUser.name());
		assertEquals(password, savedUser.password());
	}
	
	@Order(3)
	@ParameterizedTest
	@CsvFileSource(
			files = {"test-source/name-update-user.txt"},
			delimiter = '\t')
	void test_update_name(long id, String email, String name, String password) {
		var user = new User(id, email, name, password, null, null);
		var updatedUser = model.update(user);
		
		assertNotNull(updatedUser);
		assertEquals(name, updatedUser.name());
	}
	
	@Order(2)
	@ParameterizedTest
	@CsvFileSource(
			files = {"test-source/users.txt"},
			delimiter = '\t')
	void test_find_one(long id, String email, String name, String password) {
		var user = model.findOne(id);
		assertNotNull(user);
		assertEquals(id, user.id());
		assertEquals(email, user.email());
		assertEquals(name, user.name());
		assertEquals(password, user.password());
	}
}
