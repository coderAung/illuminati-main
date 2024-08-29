package edu.ucsy.social.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import edu.ucsy.social.data.SearchModel;
import edu.ucsy.social.data.criteria.Criteria;
import edu.ucsy.social.model.entity.User;
import edu.ucsy.social.model.entity.User.Role;
import edu.ucsy.social.model.entity.User.Status;

public class UserSearchModel extends SearchModel<User> {

	public UserSearchModel(Connection connection) {
		super(connection);
	}

	@Override
	public User searchOne(Criteria c) {
		var sql = "select * from users";
		try(var stmt = connection.prepareStatement(c.generateStatement(sql))) {
			var values = c.getValues();
			
			for(int i = 0; i < values.size(); i ++) {
				stmt.setObject(i + 1, values.get(i));
			}
			
			var rs = stmt.executeQuery();
			if(rs.next()) {
				var user = userFrom(rs);
				return user;
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
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
	public List<User> search(Criteria c, long limit) {
		var sql = "select * from users";
		return null;
	}

	@Override
	public List<User> searchLatest(long limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> searchLatest(Criteria c, long limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}


}
