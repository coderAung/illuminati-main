package edu.ucsy.social.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import edu.ucsy.social.data.AbstractModel;
import edu.ucsy.social.data.Countable;
import edu.ucsy.social.data.criteria.Criteria;
import edu.ucsy.social.model.entity.Reaction;

public class ReactionModel extends AbstractModel<Reaction> implements Countable {

	@Override
	public Reaction save(Reaction t) {
		var sql = "insert into reactions (user_id, post_id) values (?, ?)";
		try(var stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			stmt.setLong(1, t.getUserId());
			stmt.setLong(2, t.getPostId());
			stmt.executeUpdate();
			var keys = stmt.getGeneratedKeys();
			if(keys.next()) {
				var id = keys.getLong(1);
				t.setId(id);
				return t;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Reaction findOne(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet findOne(long id, String... cols) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reaction> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reaction> get(long limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reaction update(Reaction t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reaction fullUpdate(Reaction t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count(Criteria criteria) {
		var sql = "select count(*) as reacts from reactions";
		var values = criteria.getValues();
		try(var stmt = connection.prepareStatement(criteria.generateStatement(sql))) {
			
			for(int i = 0; i < values.size(); i ++) {
				stmt.setObject(i + 1, values.get(i));
			}
			
			var rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getLong("reacts");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
