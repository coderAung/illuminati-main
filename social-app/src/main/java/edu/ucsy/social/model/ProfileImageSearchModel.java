package edu.ucsy.social.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import edu.ucsy.social.data.SearchModel;
import edu.ucsy.social.data.criteria.Criteria;
import edu.ucsy.social.model.entity.ProfileImage;
import edu.ucsy.social.model.entity.type.ImageStatus;

public class ProfileImageSearchModel extends SearchModel<ProfileImage> {

	@Override
	public ProfileImage searchOne(Criteria c) {

		var sql = "select id, name, user_id, status, uploaded_at from profile_images";
		
		var statement = c.generateStatement(sql);
		
		var values = c.getValues();
		
		try(var stmt = connection.prepareStatement(statement)) {
			for(int i = 0; i < values.size(); i ++) {
				stmt.setObject(i + 1, values.get(i));
			}
			
			var rs = stmt.executeQuery();
			if(rs.next()) {
				var profileImage = profileImageFrom(rs);
				return profileImage;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private ProfileImage profileImageFrom(ResultSet rs) throws SQLException {
		return new ProfileImage(rs.getLong("id"),
								rs.getString("name"), 
								rs.getLong("user_id"), 
								ImageStatus.valueOf(rs.getString("status").toUpperCase()),
								rs.getTimestamp("uploaded_at").toLocalDateTime());
	}

	@Override
	public List<ProfileImage> search(Criteria c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}


	@Override
	public List<ProfileImage> searchLatest(Criteria c) {
		// TODO Auto-generated method stub
		return null;
	}

}
