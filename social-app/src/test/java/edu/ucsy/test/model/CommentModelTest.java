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
import edu.ucsy.social.data.db.DatabaseConnector;
import edu.ucsy.social.model.entity.Comment;
import edu.ucsy.test.db.DatabaseInitializer;

@TestMethodOrder(OrderAnnotation.class)
public class CommentModelTest {

	private static DatabaseConnector connector;
	private static DatabaseInitializer di;
	private static Model<Comment> commentModel;
	
	@BeforeAll
	static void init() {
		// initiate connector from CustomConnectorFactory here
		
		di = new DatabaseInitializer(connector);
		commentModel = ModelFactory.getModel(Comment.class, connector);
	}
	
	@Order(1)
	@ParameterizedTest
	@CsvFileSource(
			files = {"test-source/comments.txt"},
			delimiter = '\t')
	void test_save(long id, String content, long userId, long postId, String userName) {
//		System.out.printf("%s %s %s %s %s%n", id, content, userId, postId, userName);
		var comment = new Comment(content, userId, userName, postId);
		comment = commentModel.save(comment);
		
		assertNotNull(comment);
		assertEquals(id, comment.id());
		assertEquals(content, comment.content());
		assertEquals(userId, comment.userId());
		assertEquals(postId, comment.postId());
		assertEquals(userName, comment.userName());
		
		assertNotNull(comment.createdAt());
		assertNotNull(comment.updatedAt());
	}
}
