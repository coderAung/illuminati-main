package edu.ucsy.social.model;

import java.util.List;

import edu.ucsy.social.data.AbstractModel;
import edu.ucsy.social.data.db.DatabaseConnector;
import edu.ucsy.social.model.entity.Post;

public class PostModel extends AbstractModel<Post> {

	public PostModel(DatabaseConnector connector) {
		super(connector);
	}

	@Override
	public Post save(Post t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post findOne(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> get(long limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post update(Post t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post fullUpdate(Post t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Post findOne(long id, String... cols) {
		// TODO Auto-generated method stub
		return null;
	}

}
