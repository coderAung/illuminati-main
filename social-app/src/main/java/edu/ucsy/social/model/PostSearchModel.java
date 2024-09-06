package edu.ucsy.social.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ucsy.social.data.SearchModel;
import edu.ucsy.social.data.criteria.Criteria;
import edu.ucsy.social.model.entity.Post;

public class PostSearchModel extends SearchModel<Post> {

	@Override
	public Post searchOne(Criteria c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> search(Criteria c) {
		var sql = "select p.*, u.name as user_name from posts as p join users as u on u.id = p.user_id";
		
		var statement = c.generateStatement(sql);
		var values = c.getValues();
		try(var stmt = connection.prepareStatement(statement)) {

			for(int i = 0; i < values.size(); i ++) {
				stmt.setObject(i + 1, values.get(i));
			}
			
			var rs = stmt.executeQuery();
			var posts = new ArrayList<Post>();
			while(rs.next()) {
				var post = postFrom(rs);
				posts.add(post);
			}
			return posts;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private Post postFrom(ResultSet rs) throws SQLException {
		
		var post = new Post(
				rs.getLong("id"),
				rs.getString("content"), 
				rs.getTimestamp("created_at").toLocalDateTime(),
				rs.getTimestamp("updated_at").toLocalDateTime(),
				rs.getLong("user_id"),
				rs.getString("user_name"));	
		return post;
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Post> search(Criteria c, long limit) {
		return null;
	}

	@Override
	public List<Post> searchLatest(long limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> searchLatest(Criteria c, long limit) {
		// TODO Auto-generated method stub
		return null;
	}

}
