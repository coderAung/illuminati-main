package edu.ucsy.social.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.ucsy.social.data.SearchModel;
import edu.ucsy.social.data.criteria.Criteria;
import edu.ucsy.social.model.entity.FriendRequest;

public class FriendRequestSearchModel extends SearchModel<FriendRequest> {

	@Override
	public FriendRequest searchOne(Criteria c) {
		var sql = """
				select * from friend_requests
				""";
		var statement = c.generateStatement(sql);
		var values = c.getValues();
		
		try(var stmt = connection.prepareStatement(statement)) {
			for(int i = 0; i < values.size(); i ++) {
				stmt.setObject(i + 1, values.get(i));
			}
			
			var rs = stmt.executeQuery();
			if(rs.next()) {
				var friendRequest = new FriendRequest(rs.getLong("id"), rs.getLong("request_to"), rs.getLong("request_by"));
				return friendRequest;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<FriendRequest> search(Criteria c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<FriendRequest> search(Criteria c, long limit) {
		
		return null;
	}

	@Override
	public List<FriendRequest> searchLatest(long limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FriendRequest> searchLatest(Criteria c, long limit) {
		// TODO Auto-generated method stub
		return null;
	}

}
