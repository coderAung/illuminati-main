package edu.ucsy.test.model;

import org.junit.jupiter.api.BeforeAll;

import edu.ucsy.social.data.Model;
import edu.ucsy.social.data.ModelFactory;
import edu.ucsy.social.data.db.DatabaseConnector;
import edu.ucsy.social.model.entity.Post;
import edu.ucsy.test.db.CustomConnectorFactory;
import edu.ucsy.test.db.DatabaseInitializer;

public class PostModelTest {

	private static DatabaseConnector connector;
	private static DatabaseInitializer di;
	private static Model<Post> postModel;
	
	@BeforeAll
	static void init() {
		// enter specific password to get connector
		connector = CustomConnectorFactory.getConnectorWithPassword("");
		di = new DatabaseInitializer(connector);
		postModel = ModelFactory.getModel(Post.class, connector);
	}
}
