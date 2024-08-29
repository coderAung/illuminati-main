package edu.ucsy.social.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import edu.ucsy.social.data.AbstractModel;
import edu.ucsy.social.model.entity.User;
import edu.ucsy.social.model.entity.User.Role;
import edu.ucsy.social.model.entity.User.Status;
import edu.ucsy.social.utils.StringTool;

public class UserModel extends AbstractModel<User> {

	@Override
	public User save(User u) {
		var sql = """
				insert into users (email, name, password, role, created_at, updated_at)
				 values (?, ?, ?, ?, ?, ?)
				""";
		
		try(var stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			stmt.setString(1, u.email());
			stmt.setString(2, u.name());
			stmt.setString(3, u.password());
			stmt.setInt(4, u.role().ordinal() + 1);
			var createdAt = Timestamp.valueOf(LocalDateTime.now());
			var updatedAt = Timestamp.valueOf(LocalDateTime.now());
			stmt.setTimestamp(5, createdAt);
			stmt.setTimestamp(6, updatedAt);

			var row = stmt.executeUpdate();
			if(row == 0) {
				return null;
			}
			
			var keys = stmt.getGeneratedKeys();
			if(keys.next()) {
				var user = u.perfectClone(keys.getLong(1), createdAt.toLocalDateTime(), updatedAt.toLocalDateTime());
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
		try(var stmt = connection.prepareStatement(sql)) {
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
		try(var stmt = connection.prepareStatement(sql)) {
			
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
		try(var stmt = connection.prepareStatement(sql)) {
			
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
	public User update(User u) {
		var sql = "select * from users where id = ?";
		try(var stmt = connection.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)) {
			
			stmt.setLong(1, u.id());
			
			var rs = stmt.executeQuery();
			
			if(rs.next()) {

				// email update
				if(!u.email().equals(rs.getString("email"))) {
					rs.updateString("email", u.email());
				}
				// name update
				if(!u.name().equals(rs.getString("name"))) {
					rs.updateString("name", u.name());
				}
				// password update
				if(!u.password().equals(rs.getString("password"))) {
					rs.updateString("password", u.password());
				}
				// role update
				if(!u.role().equals(Role.valueOf(rs.getString("role")))) {
					rs.updateInt("role", u.role().ordinal() + 1);
				}
				
				// status update
				if(!u.status().equals(Status.valueOf(rs.getString("status")))) {
					rs.updateInt("status", u.status().ordinal() + 1);
				}
				
				var updatedAt = Timestamp.valueOf(LocalDateTime.now());
				rs.updateTimestamp("updated_at", updatedAt);

				rs.updateRow();
				return u.updatedClone(updatedAt.toLocalDateTime());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}

	@Override
	public User fullUpdate(User u) {
		var sql = """
				update users 
				set email = ?, name = ?, password = ?,
					role = ?, status = ?, updated_at = ?
				where id = ?
				""";
		try(var stmt = connection.prepareStatement(sql)) {
			
			stmt.setString(1, u.email());
			stmt.setString(2, u.name());
			stmt.setString(3, u.password());
			stmt.setInt(4, u.role().ordinal() + 1);
			stmt.setInt(5, u.status().ordinal() + 1);

			var updatedAt = Timestamp.valueOf(LocalDateTime.now());
			stmt.setTimestamp(6, updatedAt);
			
			stmt.setLong(7, u.id());
			
			var row = stmt.executeUpdate();
			if(0 == row) {
				return null;
			}
			
			return u.updatedClone(updatedAt.toLocalDateTime());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(long id) {
		var sql = "delete from users where id = ?";
		try(var stmt = connection.prepareStatement(sql)) {
			stmt.setLong(1, id);
			var row = stmt.executeUpdate();
			if(0 == row) {
				return false;
			}
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private User userFrom(ResultSet rs) throws SQLException {
		var user = new User(
				rs.getLong("id"),
				rs.getString("email"), 
				rs.getString("name"),
				rs.getString("password"),
				Role.valueOf(rs.getString("role")),
				Status.valueOf(rs.getString("status")),
				rs.getTimestamp("created_at").toLocalDateTime(),
				rs.getTimestamp("updated_at").toLocalDateTime());
		return user;
	}
	
	@Override
	public ResultSet findOne(long id, String... cols) {
		var sql = "select %s from users where id = ?";
		var columns = StringTool.joinWithComma(cols);		
		sql = sql.formatted(columns);
		
		try(var stmt = connection.prepareStatement(sql)) {
			
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
	public ResultSet findOne(long id, String... cols) {
		// TODO Auto-generated method stub
		return null;
	}

}
