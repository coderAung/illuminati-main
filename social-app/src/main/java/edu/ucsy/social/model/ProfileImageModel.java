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
import edu.ucsy.social.model.entity.ProfileImage;
import edu.ucsy.social.model.entity.type.ImageStatus;
import edu.ucsy.social.utils.StringTool;

public class ProfileImageModel extends AbstractModel<ProfileImage> {

	public ProfileImageModel(DatabaseConnector connector) {
		super(connector);
	}

	@Override
	public ProfileImage save(ProfileImage pi) {
		var sql = """
				insert into profile_images (name, user_id, status, uploaded_at)
				values(?, ?, ?, ?, ?)
				""";
		try (var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setString(1, pi.name());
			stmt.setLong(2, pi.userId());
			stmt.setInt(3, pi.status().ordinal() + 1);
			var uploadedAt = Timestamp.valueOf(LocalDateTime.now());
			stmt.setTimestamp(4, uploadedAt);

			var row = stmt.executeUpdate();
			if (row == 0) {
				return null;
			}

			var keys = stmt.getGeneratedKeys();
			if (keys.next()) {
				var profleImage = pi.perfectClone(keys.getLong(1), uploadedAt.toLocalDateTime());
				return profleImage;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ProfileImage findOne(long id) {

		var sql = "select * from profile_images where id = ?";
		try (var conn = connector.getConnection(); var stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, id);
			var rs = stmt.executeQuery();
			if (rs.next()) {
				return profileFrom(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultSet findOne(long id, String... cols) {
		
		var sql = "select %s from proifle_images where id = ?";
		var columns = StringTool.joinWithComma(cols);
		sql =sql.formatted(columns);
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql)){
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
	public List<ProfileImage> getAll() {
		var sql = "select * from profile_images";
		try (var conn = connector.getConnection(); var stmt = conn.prepareStatement(sql)) {
			var rs = stmt.executeQuery();
			var profileImages = new ArrayList<ProfileImage>();
			while (rs.next()) {
				var profileImage = profileFrom(rs);
				profileImages.add(profileImage);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ProfileImage> get(long limit) {
		var sql = "select * from profile_images limit ?";
		try (var conn = connector.getConnection(); 
				var stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, limit);
			var rs = stmt.executeQuery();
			var profileImages = new ArrayList<ProfileImage>();
			while (rs.next()) {
				var profileImage = profileFrom(rs);
				profileImages.add(profileImage);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ProfileImage update(ProfileImage pi) {
		
		var sql = "select * from profile_images where id = ?";
	try(var conn = connector.getConnection();
			var stmt = conn.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)) {
		
		stmt.setLong(1, pi.id());
		
		var rs = stmt.executeQuery();
		
		if(rs.next()) {
			// content update
			if(!pi.name().equals(rs.getString("name"))) {
				rs.updateString("content", pi.name());
			}
			// updateClone?
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
	}

	@Override
	public ProfileImage fullUpdate(ProfileImage pi) {
		var pi1 = update(pi);
		return pi1;
	}

	private ProfileImage profileFrom(ResultSet rs) throws SQLException {

		var profile = new ProfileImage(
				rs.getLong("id"),
				rs.getString("name"),
				rs.getLong("user_id"),
				ImageStatus.valueOf(rs.getString("status")),
				rs.getTimestamp("uploaded_at").toLocalDateTime());
		return profile;
	}

	@Override
	public boolean delete(long id) {
		var sql = "delete from profile_images where id = ?";
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

}
