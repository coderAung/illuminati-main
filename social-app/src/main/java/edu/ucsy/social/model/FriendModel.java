package edu.ucsy.social.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.ucsy.social.data.AbstractModel;
import edu.ucsy.social.data.db.DatabaseConnector;
import edu.ucsy.social.model.entity.Friend;
import edu.ucsy.social.utils.StringTool;

public class FriendModel extends AbstractModel<Friend> {

	public FriendModel(DatabaseConnector connector) {
		super(connector);
	}

	@Override
	public Friend save(Friend f) {
		var sql = """
				insert into friends(user_id , friend_id) values(?, ?)
				""";

		try (var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setLong(1, f.userId());
			stmt.setLong(2, f.friendId());

			var row = stmt.executeUpdate();
			if (row == 0) {
				return null;
			}

			var keys = stmt.getGeneratedKeys();
			if (keys.next()) {
				var friend = f.perfectClone(keys.getLong(1));
				return friend;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Friend findOne(long id) {
		var sql = "select * from friends where id = ?";
		try (var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setLong(1, id);
			var rs = stmt.executeQuery();
			if (rs.next()) {
				return friendFrom(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultSet findOne(long id, String... cols) {
		var sql = "select %s from friends where id = ?";
		var columns = StringTool.joinWithComma(cols);

		sql = sql.formatted(columns);
		try (var conn = connector.getConnection(); var stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, id);
			var rs = stmt.executeQuery();
			if (rs.next()) {
				return rs;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Friend> getAll() {
		var sql = "select f.id, u.name from friends as f join users as u on f.user_id = u.id";
		try (var conn = connector.getConnection(); var stmt = conn.prepareStatement(sql)) {

			var rs = stmt.executeQuery();
			var friends = new ArrayList<Friend>();

			while (rs.next()) {
				var friend = friendFrom(rs);
				friends.add(friend);
			}
			return friends;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Friend> get(long limit) {
		var sql = "select f.id, u.name from friends as f join users as u on f.user_id = u.id limit ?";
		try (var conn = connector.getConnection(); var stmt = conn.prepareStatement(sql)) {

			var rs = stmt.executeQuery();
			var friends = new ArrayList<Friend>();

			while (rs.next()) {
				var friend = friendFrom(rs);
				friends.add(friend);
			}
			return friends;

		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		var friend = new Friend(rs.getLong("id"), 
				rs.getLong("user_id"), 
				rs.getLong("friend_id"),
				rs.getString("name"));
		return friend;

	}

}
