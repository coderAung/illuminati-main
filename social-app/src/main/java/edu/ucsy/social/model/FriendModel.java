package edu.ucsy.social.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import edu.ucsy.social.data.AbstractModel;
import edu.ucsy.social.data.db.DatabaseConnector;
import edu.ucsy.social.model.entity.Friend;
import edu.ucsy.social.model.entity.FriendRequest;

public class FriendModel extends AbstractModel<Friend> {

	public FriendModel(DatabaseConnector connector) {
		super(connector);
	}

	@Override
	public Friend save(Friend t) {
		return null;
	}

	@Override
	public Friend findOne(long id) {
		return null;
	}

	@Override
	public ResultSet findOne(long id, String... cols) {
		return null;
	}

	@Override
	public List<Friend> getAll() {
		return null;
	}

	@Override
	public List<Friend> get(long limit) {
		return null;
	}

	@Override
	public Friend update(Friend t) {
		return null;
	}

	@Override
	public Friend fullUpdate(Friend t) {
		return null;
	}

	@Override
	public boolean delete(long id) {
		var sql = "delete from friends where id = ?";
		try (var conn = connector.getConnection(); var stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, id);
			var row = stmt.executeUpdate();
			if (row == 0) {
				return false;
			}
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Friend friendFrom(ResultSet rs) throws SQLException {
		var friend = new Friend(
				rs.getLong("id"), 
				rs.getLong("user_id"),
				rs.getLong("friend_id"),
				rs.getString(""));
		return friend;

	}

}
