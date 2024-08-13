package edu.ucsy.test;

import java.util.Set;

import edu.ucsy.social.data.Model;
import edu.ucsy.social.data.ModelFactory;
import edu.ucsy.social.data.OneToMany;
import edu.ucsy.social.data.OneToManyAsMap;
import edu.ucsy.social.model.entity.Post;
import edu.ucsy.social.model.entity.PostImage;
import edu.ucsy.social.model.entity.ProfileImage;
import edu.ucsy.social.model.entity.User;
import edu.ucsy.test.db.CustomConnectorFactory;

public class BatchTest {

	void test() {
		Model<PostImage> model = ModelFactory.getModel(PostImage.class, CustomConnectorFactory.getConnectorWithAdmin());
		model.getBatchModel().saveBatch(null);
		
		Model<User> userModel = ModelFactory.getModel(User.class, CustomConnectorFactory.getConnectorWithAdmin());
		userModel.getRelational(OneToMany.class).getMany(Post.class, 1);
		userModel.getRelational(OneToManyAsMap.class).getMany(ProfileImage.class, Set.of(1, 2, 3, 4));
	}
}
