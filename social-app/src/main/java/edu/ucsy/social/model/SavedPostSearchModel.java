package edu.ucsy.social.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import edu.ucsy.social.data.SearchModel;
import edu.ucsy.social.data.criteria.Criteria;
import edu.ucsy.social.model.entity.SavedPost;

public class SavedPostSearchModel extends SearchModel<SavedPost> {
	
	@Override
	public SavedPost searchOne(Criteria c) {
		
		var sql = "select * from saved_posts";
		var values = c.getValues();
		var statement = c.generateStatement(sql);
		
		try(var stmt = connection.prepareStatement(statement)) {
			for(int i = 0; i < values.size(); i ++) {
				stmt.setObject(i + 1, values.get(i));
			}
			
			var rs = stmt.executeQuery();
			if(rs.next()) {
				var savedPost = savedPostFrom(rs);
				return savedPost;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private SavedPost savedPostFrom(ResultSet rs) throws SQLException {
		return new SavedPost(
				rs.getLong("id"), 
				rs.getLong("post_id"), 
				rs.getLong("user_id"), 
				rs.getTimestamp("saved_at").toLocalDateTime());
	}

	@Override
	public List<SavedPost> search(Criteria c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<SavedPost> searchLatest(Criteria c) {
		// TODO Auto-generated method stub
		return null;
	}

}
