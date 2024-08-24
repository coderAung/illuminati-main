package edu.ucsy.test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import edu.ucsy.social.data.Model;
import edu.ucsy.social.data.ModelFactory;
import edu.ucsy.social.data.db.DatabaseConnector;
import edu.ucsy.social.model.entity.Post;
import edu.ucsy.test.db.CustomConnectorFactory;
import edu.ucsy.test.db.DatabaseInitializer;

@TestMethodOrder(OrderAnnotation.class)
public class PostModelTest {

	private static DatabaseConnector connector;
	private static DatabaseInitializer di;
	private static Model<Post> postModel;
	
	@BeforeAll
	static void init() {
		// enter specific password to get connector
		connector = CustomConnectorFactory.getConnectorWithPassword("admin");
		di = new DatabaseInitializer(connector);
		di.truncate("posts");
		
		postModel = ModelFactory.getModel(Post.class);
	}
	
	@Order(1)
	@ParameterizedTest
	@CsvFileSource(
			files = {"test-source/posts.txt"},
			delimiter = '\t')
	void test_save(long id, String content, long userId, String userName) {
		var post = new Post(content, userId, userName);
		
		try(var conn = connector.getConnection()) {
			postModel.setConnection(conn);
			post = postModel.save(post);
			
			assertNotNull(post);
			assertEquals(id, post.id());
			assertEquals(content, post.content());
			assertEquals(userId, post.userId());
			assertEquals(userName, post.userName());	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			postModel.setConnection(null);
		}
		
	}
	
	@Order(2)
	@ParameterizedTest
	@CsvFileSource(
			files = {"test-source/posts.txt"},
			delimiter = '\t')
	void test_find_one(long id, String content, long userId, String userName) {
		
		try(var conn = connector.getConnection()) {
			postModel.setConnection(conn);
			var post = postModel.findOne(id);

			assertNotNull(post);
			assertEquals(id, post.id());
			assertEquals(content, post.content());
			assertEquals(userId, post.userId());
			assertEquals(userName, post.userName());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			postModel.setConnection(null);
		}
		
	}

	@Order(3)
	@Test
	void test_getAll() {
		try(var conn = connector.getConnection()) {
			postModel.setConnection(conn);
			var posts = postModel.getAll();
			assertNotNull(posts);
			assertEquals(10, posts.size());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			postModel.setConnection(null);
		}
	}
	
	@Order(4)
	@ParameterizedTest
	@CsvFileSource(
			files = {"test-source/content-update-posts.txt"},
			delimiter = '\t')
	void test_update(long id, String content, long userId, String userName) {
		var post = new Post(id, content, null, null, userId, userName);
		try(var conn = connector.getConnection()) {
			postModel.setConnection(conn);
			post = postModel.update(post);
			
			assertNotNull(post);
			assertEquals(id, post.id());
			assertEquals(content, post.content());
			assertEquals(userId, post.userId());
			assertEquals(userName, post.userName());

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			postModel.setConnection(null);
		}
	}
		
}
