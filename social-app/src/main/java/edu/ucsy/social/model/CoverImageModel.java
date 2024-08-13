package edu.ucsy.social.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import edu.ucsy.social.data.AbstractModel;
import edu.ucsy.social.data.db.DatabaseConnector;
import edu.ucsy.social.model.entity.CoverImage;

public class CoverImageModel extends AbstractModel <CoverImage>{

	public CoverImageModel(DatabaseConnector connector) {
		super(connector);
	}

	@Override
	public CoverImage save(CoverImage ci) {
		var sql = """
				insert into cover_images (name, user_id, status, uploaded_at )
				 values ( ?, ?, ?, ?)
				""";
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			stmt.setString(1, ci.name());
			stmt.setLong(2, ci.userId());
			stmt.setInt(3,ci.status().ordinal() + 1);
			var uploadedAt =  Timestamp.valueOf(LocalDateTime.now());
			stmt.setTimestamp(4,uploadedAt);

			var row = stmt.executeUpdate();
			if(row == 0) {
				return null;
			}
			
			var keys = stmt.getGeneratedKeys();
			if(keys.next()) {
				var coverImage = ci.perfectClone(keys.getLong(1),uploadedAt.toLocalDateTime());
				return coverImage;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CoverImage findOne(long id) {
		return null;
	}

	@Override
	public ResultSet findOne(long id, String... cols) {
		return null;
	}

	@Override
	public List<CoverImage> getAll() {
		return null;
	}

	@Override
	public List<CoverImage> get(long limit) {
		return null;
	}

	@Override
	public CoverImage update(CoverImage t) {
		return null;
	}

	@Override
	public CoverImage fullUpdate(CoverImage t) {
		return null;
	}

	@Override
	public boolean delete(long id) {
		return false;
	}

	

}
