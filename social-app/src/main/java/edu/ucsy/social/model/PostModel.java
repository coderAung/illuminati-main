package edu.ucsy.social.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import edu.ucsy.social.data.AbstractModel;
import edu.ucsy.social.data.db.DatabaseConnector;
import edu.ucsy.social.model.entity.Post;
import edu.ucsy.social.model.entity.PostImage;

public class PostModel extends AbstractModel<Post> {

	public PostModel(DatabaseConnector connector) {
		super(connector);
	}

	@Override
	public Post save(Post p) {
		
		var postImages = p.postImages();
		var sql1 = """
					insert into posts (content, created_at, updated_at, user_id)
					values (?, ?, ?, ?)
				""";		
		
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, p.content());
			var createdAt = Timestamp.valueOf(LocalDateTime.now());
			var updatedAt = Timestamp.valueOf(LocalDateTime.now());
			stmt.setTimestamp(2, createdAt);
			stmt.setTimestamp(3, updatedAt);
			stmt.setLong(4, p.userId());

			var row = stmt.executeUpdate();
			if(null != postImages) {
				postImages = savePostImages(conn, postImages);
			}
			

			if(row == 0) {
				return null;
			}
			
			var keys = stmt.getGeneratedKeys();
			if(keys.next()) {
				var posts = p.perfectClone(
						keys.getLong(1),
						postImages,
						createdAt.toLocalDateTime(), 
						updatedAt.toLocalDateTime());

				return posts;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	private List<PostImage> savePostImages(Connection conn, List<PostImage> postImages) {
		var sql = "insert into post_images (name, post_id) values (?, ?)";
		try(var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			for(var pi : postImages) {
				stmt.setString(1, pi.name());
				stmt.setLong(2, pi.postId());
				
				stmt.addBatch();
			}
			
			var rows = stmt.executeBatch();
			if(rows.length == 0) {
				return null;
			}
			
			var savedPostImages = new ArrayList<PostImage>();
			
			var keys = stmt.getGeneratedKeys();
			var index = 0;
			
			while(keys.next()) {
				var pi = postImages.get(index).perfectClone(keys.getLong(1));
				savedPostImages.add(pi);
				++ index;
			}
			return savedPostImages;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Post findOne(long id) {
		var sql = "select * from posts where id = ?";
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, id);
			var rs = stmt.executeQuery();
			if(rs.next()) {
				return postFrom(rs);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Post> getAll() {
		var sql = "select * from posts";
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql)) {
			
			var rs = stmt.executeQuery();
			var posts = new ArrayList<Post>();
			
			while (rs.next()) {
				var post = postFrom(rs);
				posts.add(post);
			}
			return posts;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Post> get(long limit) {
		var sql = "select * from posts limit ?";
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql)) {
			
			stmt.setLong(1, limit);
			var rs = stmt.executeQuery();
			var posts = new ArrayList<Post>();
			while(rs.next()) {
				var user = postFrom(rs);
				posts.add(user);
			}
			return posts;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Post update(Post p) {
		var sql = "select * from posts where id = ?";
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)) {
			
			stmt.setLong(1, p.id());
			
			var rs = stmt.executeQuery();
			
			if(rs.next()) {

				// content update
				if(!p.content().equals(rs.getString("post"))) {
					rs.updateString("post", p.content());
				}
				

				var updatedAt = Timestamp.valueOf(LocalDateTime.now());
				rs.updateTimestamp("updated_at", updatedAt);

				rs.updateRow();
				return p.updatedClone(updatedAt.toLocalDateTime());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}

	@Override
	public Post fullUpdate(Post t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(long id) {
		var sql = "delete from posts where id = ?";
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

	private Post postFrom(ResultSet rs) throws SQLException {
		
		var postImages = new ArrayList<PostImage>();
		var post = new Post(
				rs.getLong(1),
				rs.getString(2), 
				postImages,
				rs.getTimestamp(4).toLocalDateTime(),
				rs.getTimestamp(5).toLocalDateTime(),
				rs.getLong(6),
				rs.getString(7));	
		return post;
	}

	@Override
	public ResultSet findOne(long id, String... cols) {
		// TODO Auto-generated method stub
		return null;

	}

}
