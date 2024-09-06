package edu.ucsy.social.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ucsy.social.data.SearchModel;
import edu.ucsy.social.data.criteria.Criteria;
import edu.ucsy.social.model.entity.Friend;

public class FriendSearchModel extends SearchModel<Friend> {

	@Override
	public Friend searchOne(Criteria c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Friend> search(Criteria c) {
		var sql = "select f.id, u.name from friends as f join users as u on f.user_id = u.id";
		var statement = c.generateStatement(sql);
		var values = c.getValues();
		
		try(var stmt = connection.prepareStatement(statement)) {
			
			for(int i = 0; i < values.size(); i ++) {
				stmt.setObject(i + 1, values.get(i));
			}
			
			var rs = stmt.executeQuery();
			var friends = new ArrayList<Friend>();
			while(rs.next()) {
				var friend = friendFrom(rs);
				friends.add(friend);
			}
			
			return friends;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Friend friendFrom(ResultSet rs) throws SQLException {
		var friend = new Friend(rs.getLong("id"), 
				rs.getLong("user_id"), 
				rs.getLong("friend_id"),
				rs.getString("name"));
		return friend;

	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Friend> search(Criteria c, long limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Friend> searchLatest(long limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Friend> searchLatest(Criteria c, long limit) {
		// TODO Auto-generated method stub
		return null;
	}

}
