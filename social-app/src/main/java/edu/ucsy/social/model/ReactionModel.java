package edu.ucsy.social.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import edu.ucsy.social.data.AbstractModel;
import edu.ucsy.social.data.Countable;
import edu.ucsy.social.data.Deletable;
import edu.ucsy.social.data.criteria.Criteria;
import edu.ucsy.social.model.entity.Reaction;

public class ReactionModel extends AbstractModel<Reaction> implements Countable, Deletable {

	@Override
	public Reaction save(Reaction t) {
		var sql = "insert into reactions (user_id, post_id) values (?, ?)";
		try(var stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			stmt.setLong(1, t.getUserId());
			stmt.setLong(2, t.getPostId());
			var rows = stmt.executeUpdate();
			if(rows > 0) {
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

	@Override
	public boolean delete(Criteria criteria, String... tableNames) {
		var sql = "delete from %s";
		var values = criteria.getValues();
		for(var name : tableNames) {
			sql = criteria.generateStatement(sql.formatted(name));
			try(var stmt = connection.prepareStatement(sql)) {
				for(int i = 0; i < values.size(); i ++) {
					stmt.setObject(i + 1, values.get(i));
				}
				stmt.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}

}
