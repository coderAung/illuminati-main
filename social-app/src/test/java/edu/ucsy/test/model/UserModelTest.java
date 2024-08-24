package edu.ucsy.test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import edu.ucsy.social.data.Model;
import edu.ucsy.social.data.ModelFactory;
import edu.ucsy.social.data.db.DatabaseConnector;
import edu.ucsy.social.model.entity.User;
import edu.ucsy.social.model.entity.User.Role;
import edu.ucsy.social.model.entity.User.Status;
import edu.ucsy.test.db.CustomConnectorFactory;
import edu.ucsy.test.db.DatabaseInitializer;

//@Disabled
@TestMethodOrder(OrderAnnotation.class)
public class UserModelTest {
	
	private static DatabaseInitializer di;
	private static DatabaseConnector connector;
	private static Model<User> model;

	@BeforeAll
	static void init() {
		connector = CustomConnectorFactory.getConnectorWithAdmin();
		di = new DatabaseInitializer(connector);
		di.truncate("users");
		
		model = ModelFactory.getModel(User.class);
	}
	
	@Order(1)
	@ParameterizedTest
	@CsvFileSource(
			files = {"test-source/users.txt"},
			delimiter = '\t')
	void test_save(long id, String email, String name, String password, Role role, Status status) {
		var user = new User(email, name, password, role, status);
		
		try(var connection = connector.getConnection()) {
			model.setConnection(connector.getConnection());
			var savedUser = model.save(user);
			
			assertNotNull(savedUser);
			assertEquals(id, savedUser.id());
			assertEquals(email, savedUser.email());
			assertEquals(name, savedUser.name());
			assertEquals(password, savedUser.password());
			assertEquals(role, savedUser.role());
			assertEquals(status, savedUser.status());

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			model.setConnection(null);
		}
		
	}
	
	@Order(3)
	@ParameterizedTest
	@CsvFileSource(
			files = {"test-source/name-update-user.txt"},
			delimiter = '\t')
	void test_update_name(long id, String email, String name, String password) {
		var user = new User(id, email, name, password, Role.MEMBER, Status.ACTIVE, null, null);
		
		try(var connection = connector.getConnection()) {
			model.setConnection(connector.getConnection());
			var updatedUser = model.update(user);
			
			assertNotNull(updatedUser);
			assertEquals(name, updatedUser.name());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			model.setConnection(null);
		}
	}
	
	@Order(2)
	@ParameterizedTest
	@CsvFileSource(
			files = {"test-source/users.txt"},
			delimiter = '\t')
	void test_find_one(long id, String email, String name, String password, Role role) {
		try(var connection = connector.getConnection()) {
			model.setConnection(connector.getConnection());
			var user = model.findOne(id);
			assertNotNull(user);
			assertEquals(id, user.id());
			assertEquals(email, user.email());
			assertEquals(name, user.name());
			assertEquals(password, user.password());
			assertEquals(role, user.role());

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			model.setConnection(null);
		}
	}
}
