package edu.ucsy.social.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import edu.ucsy.social.data.AbstractModel;
import edu.ucsy.social.data.db.DatabaseConnector;
import edu.ucsy.social.model.entity.SavedPost;
import edu.ucsy.social.utils.StringTool;

public class SavedPostModel extends AbstractModel<SavedPost> {

	public SavedPostModel(DatabaseConnector connector) {
		super(connector);
	}

	@Override
	public SavedPost save(SavedPost sp) {
		var sql = "insert into saved_posts (user_id, post_id, saved_at) values (?, ?, ?)";
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setLong(1, sp.userId());
			stmt.setLong(2, sp.postId());
			var savedAt = Timestamp.valueOf(LocalDateTime.now());
			stmt.setTimestamp(3, savedAt);
			
			var row = stmt.executeUpdate();
			if(0 == row) {
				return null;
			}
			var keys = stmt.getGeneratedKeys();
			if(keys.next()) {
				sp = sp.perfectClone(keys.getLong(1), savedAt.toLocalDateTime());
				return sp;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SavedPost findOne(long id) {
		var sql = "select * from saved_posts where id = ?";
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, id);
			var rs = stmt.executeQuery();
			if(rs.next()) {
				return savedpostFrom(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultSet findOne(long id, String... cols) {
		var sql = "select %s from saved_posts where id = ?";
		var columns = StringTool.joinWithComma(cols);
		
		sql = sql.formatted(columns);
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, id);
			var rs = stmt.executeQuery();
			if(rs.next()) {
				return rs;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<SavedPost> getAll() {
		var sql = "select * from saved_posts";
		try (var conn = connector.getConnection(); 
				var stmt = conn.prepareStatement(sql)) {

			var rs = stmt.executeQuery();
			var savedposts = new ArrayList<SavedPost>();

			while (rs.next()) {
				var savedpost = savedpostFrom(rs);
				savedposts.add(savedpost);
			}
			return savedposts;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<SavedPost> get(long limit) {
		var sql = "select * from saved_posts limit ?";
		try (var conn = connector.getConnection(); 
				var stmt = conn.prepareStatement(sql)) {

			stmt.setLong(1, limit);
			var rs = stmt.executeQuery();
			var savedposts = new ArrayList<SavedPost>();
			while (rs.next()) {
				var savedpost = savedpostFrom(rs);
				savedposts.add(savedpost);
			}
			return savedposts;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SavedPost update(SavedPost sp) {
		
		return null;	
	}

	@Override
	public SavedPost fullUpdate(SavedPost sp) {
		return null;
	}

	@Override
	public boolean delete(long id) {
		var sql = "delete from saved_posts where id = ?";
		try (var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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

	private SavedPost savedpostFrom(ResultSet rs) throws SQLException {
		var savedpost = new SavedPost(
				rs.getLong("id"), 
				rs.getLong("postid"), 
				rs.getString("content"),
				rs.getLong("user_id"),
				rs.getTimestamp("saved_at").toLocalDateTime());
		return savedpost;
	}

}
