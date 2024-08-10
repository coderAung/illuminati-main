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
import edu.ucsy.social.model.entity.Comment;

public class CommentModel extends  AbstractModel <Comment>{

	public CommentModel(DatabaseConnector connector) {
		super(connector);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Comment save(Comment c) {
		var sql = """
				insert into comments (content, created_at , updated_at , user_id , post_id)
				 values (?, ?, ?, ?, ?)
				""";
		
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			stmt.setString(1, c.content());
			var createdAt = Timestamp.valueOf(LocalDateTime.now());
			var updatedAt = Timestamp.valueOf(LocalDateTime.now());
			stmt.setTimestamp(2, createdAt);
			stmt.setTimestamp(3, updatedAt);
			stmt.setLong(4, c.userId());
			stmt.setLong(5, c.postId());

			var row = stmt.executeUpdate();
			if(row == 0) {
				return null;
			}
			
			var keys = stmt.getGeneratedKeys();
			if(keys.next()) {
				var comment = c.perfectClone(keys.getLong(1), createdAt.toLocalDateTime(), updatedAt.toLocalDateTime(),keys.getString(1));
				return comment ;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Comment findOne(long id) {
		var sql = "select * from comments where id = ?";
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, id);
			var rs = stmt.executeQuery();
			if(rs.next()) {
				return commentFrom(rs);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Comment> getAll() {
		var sql = "select * from comments";
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql)) {
			
			var rs = stmt.executeQuery();
			var comments = new ArrayList<Comment>();
			
			while (rs.next()) {
				var comment = commentFrom(rs);
				comments.add(comment);
			}
			return comments;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Comment> get(long limit) {
		var sql = "select * from comments limit ?";
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql)) {
			
			stmt.setLong(1, limit);
			var rs = stmt.executeQuery();
			var comments = new ArrayList<Comment>();
			while(rs.next()) {
				var comment = commentFrom(rs);
				comments.add(comment);
			}
			return comments;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Comment update(Comment c) {
		var sql = "select * from comments where id = ?";
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)) {
			
			stmt.setLong(1, c.id());
			
			var rs = stmt.executeQuery();
			
			if(rs.next()) {

				// content update
				if(!c.content().equals(rs.getString("content"))) {
					rs.updateString("content", c.content());
				}

				var updatedAt = Timestamp.valueOf(LocalDateTime.now());
				rs.updateTimestamp("updated_at", updatedAt);

				rs.updateRow();
				return c.updatedClone(updatedAt.toLocalDateTime());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Comment fullUpdate(Comment c) {
		var sql = """
				update comments set (content, updated_at , user_id , post_id)
				values (?, ?, ?, ?)
				where id = ?
				""";
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql)) {
			
			stmt.setString(1, c.content());
			var updatedAt = Timestamp.valueOf(LocalDateTime.now());
			stmt.setTimestamp(2, updatedAt);
			stmt.setLong(3,c.userId());
			stmt.setLong(4, c.postId());
			stmt.setLong(5,c.id());
			

			
			var row = stmt.executeUpdate();
			if(0 == row) {
				return null;
			}
			
			return c.updatedClone(updatedAt.toLocalDateTime());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(long id) {
		var sql = "delete from comments where id = ?";
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, id);
			var row = stmt.executeUpdate();
			if(row == 0) {
				return false;
			}
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private Comment commentFrom(ResultSet rs) throws SQLException {
		var comment = new Comment(
				rs.getLong(1),
				rs.getString(2), 
				rs.getTimestamp(3).toLocalDateTime(),
				rs.getTimestamp(4).toLocalDateTime(),
				rs.getLong(5),
				rs.getString(6),
				rs.getLong(7));
		return comment;
	}

}
