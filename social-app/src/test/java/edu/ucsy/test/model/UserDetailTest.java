package edu.ucsy.test.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.ucsy.social.data.Model;
import edu.ucsy.social.data.ModelFactory;
import edu.ucsy.social.data.db.DatabaseConnector;
import edu.ucsy.social.model.entity.UserDetail;
import edu.ucsy.test.db.CustomConnectorFactory;
import edu.ucsy.test.db.DatabaseInitializer;

public class UserDetailTest {

	private static DatabaseConnector connector;
	private static Model<UserDetail> userDetailModel;
	private static DatabaseInitializer di;
	
	@BeforeAll
	static void init() {
		connector = CustomConnectorFactory.getConnectorWithPassword("admin");
		di = new DatabaseInitializer(connector);
		userDetailModel = ModelFactory.getModel(UserDetail.class);
		di.truncate("user_details");
	}
	
	@Test
	void test_save() {
		userDetailModel.save(null);
	}
}
