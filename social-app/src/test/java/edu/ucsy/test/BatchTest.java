package edu.ucsy.test;

import java.util.Set;

import org.junit.jupiter.api.Test;

import edu.ucsy.social.data.Model;
import edu.ucsy.social.data.ModelFactory;
import edu.ucsy.social.data.OneToMany;
import edu.ucsy.social.data.OneToManyAsMap;
import edu.ucsy.social.model.entity.Post;
import edu.ucsy.social.model.entity.PostImage;
import edu.ucsy.social.model.entity.ProfileImage;
import edu.ucsy.social.model.entity.User;

public class BatchTest {

	@Test
	void test() {
		Model<PostImage> model = ModelFactory.getModel(PostImage.class);
		model.getBatchModel().saveBatch(null);
		
		Model<User> userModel = ModelFactory.getModel(User.class);
		userModel.getRelational(OneToMany.class).getMany(Post.class, 1);
		userModel.getRelational(OneToManyAsMap.class).getMany(ProfileImage.class, Set.of(1, 2, 3, 4));
	}
}
