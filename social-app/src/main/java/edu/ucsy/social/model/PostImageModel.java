package edu.ucsy.social.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.ucsy.social.data.AbstractModel;
import edu.ucsy.social.data.BatchModel;
import edu.ucsy.social.data.db.DatabaseConnector;
import edu.ucsy.social.model.entity.PostImage;
import edu.ucsy.social.utils.StringTool;

public class PostImageModel extends AbstractModel<PostImage> implements BatchModel<PostImage> {

	public PostImageModel(DatabaseConnector connector) {
		super(connector);
	}

	@Override
	public PostImage save(PostImage pi) {
		var sql = "insert into post_images (name, post_id) values (?, ?)";
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, pi.name());
			stmt.setLong(2, pi.postId());
			
			var row = stmt.executeUpdate();
			if(0 == row) {
				return null;
			}
			var keys = stmt.getGeneratedKeys();
			if(keys.next()) {
				pi = pi.perfectClone(keys.getLong(1));
				return pi;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PostImage findOne(long id) {
		var sql = "select * from post_images where id = ?";
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, id);
			var rs = stmt.executeQuery();
			if(rs.next()) {
				var pi = new PostImage(
						rs.getLong("id"), 
						rs.getString("name"),
						rs.getLong("post_id"));
				return pi;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultSet findOne(long id, String... cols) {
		var sql = "select * from post_images where id = ?";
		var columns = StringTool.joinWithComma(cols);
		sql = sql.formatted(columns);
		
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql)) {
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
	public List<PostImage> getAll() {
		var sql = "select * from post_images";
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql)) {

			var rs = stmt.executeQuery();
			var postImages = new ArrayList<PostImage>();
			while(rs.next()) {
				var pi = new PostImage(
						rs.getLong("id"), 
						rs.getString("name"),
						rs.getLong("post_id"));
				postImages.add(pi);
			}
			return postImages;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<PostImage> get(long limit) {
		var sql = "select * from post_images limit ?";
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, limit);
			var rs = stmt.executeQuery();
			var postImages = new ArrayList<PostImage>();
			while(rs.next()) {
				var pi = new PostImage(
						rs.getLong("id"), 
						rs.getString("name"),
						rs.getLong("post_id"));
				postImages.add(pi);
			}
			return postImages;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PostImage update(PostImage pi) {
		var updatedPostImage = fullUpdate(pi);
		return updatedPostImage;
	}

	@Override
	public PostImage fullUpdate(PostImage pi) {
		var sql = """
				update post_images set 
				name = ?
				where id = ?
				""";
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql)) {
			
			stmt.setString(1, pi.name());
			stmt.setLong(3, pi.id());
			
			var row = stmt.executeUpdate();
			if(0 == row) {
				return null;
			}
			return pi;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(long id) {
		var sql = "delete from post_images where id = ?";
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql)) {
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

	@Override
	public List<PostImage> saveBatch(List<PostImage> batch) {
		var sql = "insert into post_images (name, post_id) values (?, ?)";
		try(var conn = connector.getConnection()) {
			
			try(var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				
				conn.setAutoCommit(false);
				
				for(var pi : batch) {
					stmt.setString(1, pi.name());
					stmt.setLong(2, pi.postId());
					stmt.addBatch();
				}
				stmt.executeBatch();

				var keys = stmt.getGeneratedKeys();
				var index = 0;
				var savedPostImages = new ArrayList<PostImage>();
				while(keys.next()) {
					savedPostImages.add(
							batch.get(index)
								.perfectClone(keys.getLong(1)));
				}
				
				conn.commit();
				
				return savedPostImages;

			} catch (SQLException e) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteBatch(List<Long> idList) {
		var sql = "delete from post_images where id = ?";
		try(var conn = connector.getConnection()) {
			
			try(var stmt = conn.prepareStatement(sql)) {
				conn.setAutoCommit(false);
				
				for(var id : idList) {
					stmt.setLong(1, id);
					stmt.addBatch();
				}
				
				stmt.executeBatch();
				
				conn.commit();
				
				return true;
			} catch (SQLException e) {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
