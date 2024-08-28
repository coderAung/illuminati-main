package edu.ucsy.test.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import edu.ucsy.social.data.Model;
import edu.ucsy.social.data.ModelFactory;
import edu.ucsy.social.data.OneToMany;
import edu.ucsy.social.data.OneToManyAsMap;
import edu.ucsy.social.model.entity.Post;
import edu.ucsy.social.model.entity.PostImage;
import edu.ucsy.social.model.entity.User;
import edu.ucsy.test.db.CustomConnectorFactory;
import edu.ucsy.test.db.DatabaseInitializer;

public class UserModelPostImageTest {
	
	private static DatabaseInitializer di;
	private static Model<User> model;

	@BeforeAll
	static void init() {
		di = new DatabaseInitializer(CustomConnectorFactory.getConnectorWithPassword("root"));
		di.truncate("users");
		
		model = ModelFactory.getModel(User.class, CustomConnectorFactory.getConnectorWithPassword("root"));
		di.loadUser();
		di.loadPostImage();
	}
	
	@ParameterizedTest
	@CsvSource({"1,2,3"})
	void test_getManyImage(int id1, int id2, int id3) {
		var rm = model.getRelational(OneToManyAsMap.class);
		var imgMap = rm.getMany(PostImage.class, Set.of(id1, id2, id3));
		
		assertNotNull(imgMap);
		
		System.out.println(imgMap.get(id1));
		System.out.println(imgMap.get(id2));
		System.out.println(imgMap.get(id3));
		
		model.getRelational(OneToMany.class).getMany(Post.class, 1);
	}

}
