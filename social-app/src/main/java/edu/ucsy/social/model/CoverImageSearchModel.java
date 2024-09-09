package edu.ucsy.social.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import edu.ucsy.social.data.SearchModel;
import edu.ucsy.social.data.criteria.Criteria;
import edu.ucsy.social.model.entity.CoverImage;
import edu.ucsy.social.model.entity.type.ImageStatus;

public class CoverImageSearchModel extends SearchModel<CoverImage> {

	@Override
	public CoverImage searchOne(Criteria c) {
		var sql = "select id, name, user_id, status, uploaded_at from cover_images";
		var statement = c.generateStatement(sql);
		
		var values = c.getValues();
		
		try(var stmt = connection.prepareStatement(statement)) {
			for(int i = 0; i < values.size(); i ++) {
				stmt.setObject(i + 1, values.get(i));
			}
			
			var rs = stmt.executeQuery();
			if(rs.next()) {
				var coverImage = coverImageFrom(rs);
				return coverImage;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private CoverImage coverImageFrom(ResultSet rs) throws SQLException {
		return new CoverImage(rs.getLong("id"),
				rs.getString("name"), 
				rs.getLong("user_id"), 
				ImageStatus.valueOf(rs.getString("status").toUpperCase()),
				rs.getTimestamp("uploaded_at").toLocalDateTime());
	}

	@Override
	public List<CoverImage> search(Criteria c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}


	@Override
	public List<CoverImage> searchLatest(Criteria c) {
		// TODO Auto-generated method stub
		return null;
	}

}
