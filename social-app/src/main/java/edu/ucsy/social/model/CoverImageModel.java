package edu.ucsy.social.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Execute;

import edu.ucsy.social.data.AbstractModel;
import edu.ucsy.social.data.db.DatabaseConnector;
import edu.ucsy.social.model.entity.CoverImage;
import edu.ucsy.social.model.entity.type.ImageStatus;

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
		var sql= "select * from cover_images where id = ?";
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			stmt.setLong(1,id);
			var rs = stmt.executeQuery();
			if(rs.next()) {
				return CoverImageFrom(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private CoverImage CoverImageFrom(ResultSet rs) throws SQLException{
		
		var coverImage = new CoverImage(
				rs.getLong("id"),
				rs.getString("name"),
				rs.getLong("user_id"),
				ImageStatus.valueOf(rs.getString("status")),
				rs.getTimestamp("uploaded_at").toLocalDateTime()
				);
		return coverImage;
	}

	@Override
	public ResultSet findOne(long id, String... cols) {
		return null;
	}

	@Override
	public List<CoverImage> getAll() {
		var sql = "select * from cover_images";
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql)){
			var rs = stmt.executeQuery();
			var coverImages = new ArrayList<CoverImage>();
			while(rs.next()) {
				var coverImage = CoverImageFrom(rs);
				coverImages.add(coverImage);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CoverImage> get(long limit) {
		var sql = "select * from cover_images limit ?";
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql)){
			stmt.setLong(1, limit);
			var rs = stmt.executeQuery();
			var coverImages = new ArrayList<CoverImage>();
			while(rs.next()) {
				var coverImage = CoverImageFrom(rs);
				coverImages.add(coverImage);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CoverImage update(CoverImage ci) {
		var sql ="select * from cover_images where id = ?";
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			stmt.setLong(1, ci.id());
			var rs = stmt.executeQuery();
			if(rs.next()) {
				
				if(ci.name().equals(rs.getString("name"))) {
					rs.updateString("name", ci.name());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CoverImage fullUpdate(CoverImage ci) {
		var updatedCoverImage = update(ci);
		return updatedCoverImage;
	}

	@Override
	public boolean delete(long id) {
		var sql = "delete from cover_images where id = ?";
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			stmt.setLong(1, id);
			var row = stmt.executeUpdate();
			if(row == 0 ) {
				return false;
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	

}
