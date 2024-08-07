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
import edu.ucsy.social.model.entity.User;

public class UserModel extends AbstractModel<User> {

	public UserModel(DatabaseConnector connector) {
		super(connector);
	}

	@Override
	public User save(User t) {
		var sql = """
				insert into users (email, name, passwod, created_at, updated_at)
				 values (?, ?, ?, ?, ?)
				""";
		
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			stmt.setString(1, t.email());
			stmt.setString(2, t.name());
			stmt.setString(3, t.password());
			var createdAt = Timestamp.valueOf(LocalDateTime.now());
			var updatedAt = Timestamp.valueOf(LocalDateTime.now());
			stmt.setTimestamp(4, createdAt);
			stmt.setTimestamp(5, updatedAt);

			var row = stmt.executeUpdate();
			if(row == 0) {
				return null;
			}
			
			var keys = stmt.getGeneratedKeys();
			if(keys.next()) {
				var user = t.perfectClone(keys.getLong(1), createdAt.toLocalDateTime(), updatedAt.toLocalDateTime());
				return user;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User findOne(long id) {
		var sql = "select * from users where id = ?";
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, id);
			var rs = stmt.executeQuery();
			if(rs.next()) {
				return userFrom(rs);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> getAll() {
		var sql = "select * from users";
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql)) {
			
			var rs = stmt.executeQuery();
			var users = new ArrayList<User>();
			
			while (rs.next()) {
				var user = userFrom(rs);
				users.add(user);
			}
			return users;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> get(long limit) {
		var sql = "select * from users limit ?";
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql)) {
			
			stmt.setLong(1, limit);
			var rs = stmt.executeQuery();
			var users = new ArrayList<User>();
			while(rs.next()) {
				var user = userFrom(rs);
				users.add(user);
			}
			return users;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User update(User t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User fullUpdate(User t) {
		var sql = """
				update users set (email, name, password, updated_at)
				values (?, ?, ?, ?)
				where id = ?
				""";
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql)) {
			
			stmt.setString(1, t.email());
			stmt.setString(2, t.name());
			stmt.setString(3, t.password());
			
			var updatedAt = Timestamp.valueOf(LocalDateTime.now());
			stmt.setTimestamp(4, updatedAt);
			
			stmt.setLong(5, t.id());
			
			var row = stmt.executeUpdate();
			if(0 == row) {
				return null;
			}
			
			return t.updatedClone(updatedAt.toLocalDateTime());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(long id) {
		var sql = "delete from users where id = ?";
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, id);
			var row = stmt.executeUpdate();
			if(row == 0) {
				return false;
			}
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	private User userFrom(ResultSet rs) throws SQLException {
		var user = new User(
				rs.getLong(1),
				rs.getString(2), 
				rs.getString(3),
				rs.getString(4),
				rs.getTimestamp(5).toLocalDateTime(),
				rs.getTimestamp(6).toLocalDateTime());
		return user;
	}

}
