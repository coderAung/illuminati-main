package edu.ucsy.social.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import edu.ucsy.social.data.SearchModel;
import edu.ucsy.social.data.criteria.Criteria;
import edu.ucsy.social.model.entity.Reaction;

public class ReactionSearchModel extends SearchModel<Reaction> {

	@Override
	public Reaction searchOne(Criteria c) {
		var sql = "select * from reactions";
		sql = c.generateStatement(sql);
		try(var stmt = connection.prepareStatement(sql)) {
			var values = c.getValues();
			
			for(int i = 0; i < values.size(); i ++) {
				stmt.setObject(i + 1, values.get(i));
			}
			
			var rs = stmt.executeQuery();
			if(rs.next()) {
				var reaction = reactionFrom(rs);
				return reaction;
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Reaction reactionFrom(ResultSet rs) throws SQLException {
		var reaction = new Reaction(
					rs.getLong("user_id"),
					rs.getLong("post_id")
				);
		
		return reaction;
	}

	@Override
	public List<Reaction> search(Criteria c) {

		return null;
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}
