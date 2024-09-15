package edu.ucsy.social.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import edu.ucsy.social.data.AbstractModel;
import edu.ucsy.social.data.OneToMany;
import edu.ucsy.social.model.entity.Comment;
import edu.ucsy.social.model.entity.Post;
import edu.ucsy.social.model.entity.PostImage;
import edu.ucsy.social.model.entity.SavedPost;
import edu.ucsy.social.utils.StringTool;

public class PostModel extends AbstractModel<Post>
			implements OneToMany {

	@Override
	public Post save(Post p) {
		var sql = """
					insert into posts (content, created_at, updated_at, user_id)
					values (?, ?, ?, ?)
				""";		
		
		try(var stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, p.content());
			var createdAt = Timestamp.valueOf(LocalDateTime.now());
			var updatedAt = Timestamp.valueOf(LocalDateTime.now());
			stmt.setTimestamp(2, createdAt);
			stmt.setTimestamp(3, updatedAt);
			stmt.setLong(4, p.userId());

			var row = stmt.executeUpdate();
			if(row == 0) {
				return null;
			}
			
			var keys = stmt.getGeneratedKeys();
			if(keys.next()) {
				var posts = p.perfectClone(
						keys.getLong(1),
						createdAt.toLocalDateTime(), 
						updatedAt.toLocalDateTime());

				return posts;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Post findOne(long id) {
		var sql = "select p.*, u.name as user_name from posts as p join users as u on u.id = p.user_id where p.id = ?";
		try(var stmt = connection.prepareStatement(sql)) {
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
		var sql = "select p.* , u.name as user_name from posts as p join users as u on u.id = p.user_id";
		try(var stmt = connection.prepareStatement(sql)) {
			
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
		var sql = "select p.* , u.name as user_name from posts as p join users as u on u.id = p.user_id limit ?";
		try(var stmt = connection.prepareStatement(sql)) {
			
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
		try(var stmt = connection.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)) {
			
			stmt.setLong(1, p.id());
			
			var rs = stmt.executeQuery();
			
			if(rs.next()) {

				// content update
				if(!p.content().equals(rs.getString("content"))) {
					rs.updateString("content", p.content());
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
		var updatedPost = update(t);
		return updatedPost;
	}

	@Override
	public boolean delete(long id) {
		var sql = "delete from posts where id = ?";
		try(var stmt = connection.prepareStatement(sql)) {
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
		
		var post = new Post(
				rs.getLong("id"),
				rs.getString("content"), 
				rs.getTimestamp("created_at").toLocalDateTime(),
				rs.getTimestamp("updated_at").toLocalDateTime(),
				rs.getLong("user_id"),
				rs.getString("user_name"));	
		return post;
	}

	@Override
	public ResultSet findOne(long id, String... cols) {
		var sql = "select %s from posts where id = ?";
		var columns = StringTool.joinWithComma(cols);
		
		sql = sql.formatted(columns);
		try(var stmt = connection.prepareStatement(sql)) {
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

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getMany(Class<T> e, long id) {
		if(e.equals(PostImage.class)) {
			var postImages = getManyPostImages(id);
			if(null != postImages) {
				return postImages.stream().map(pi -> (T) pi).toList();
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getMany(Class<T> e, long id, long limit) {
		if(e.equals(Comment.class)) {
			var comments = getManyComments(id, limit);
			if(null != comments) {
				return comments.stream().map(c -> (T) c).toList();
			}
		}
		return null;
	}


	private List<Comment> getManyComments(long id, long limit) {
		
		var sql = """
				select c.* , u.name as user_name 
				from comments as c 
				join users as u on c.user_id = u.id
				where c.post_id = ?
				order by c.created_at desc
				limit ?
				""";

		try(var stmt = connection.prepareStatement(sql)) {
			stmt.setLong(1, id);
			stmt.setLong(2, limit);
			
			var rs = stmt.executeQuery();
			List<Comment> comments = new ArrayList<>();
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
	
	private Comment commentFrom(ResultSet rs) throws SQLException {
		var comment = new Comment(
				rs.getLong("id"),
				rs.getString("content"), 
				rs.getTimestamp("created_at").toLocalDateTime(),
				rs.getTimestamp("updated_at").toLocalDateTime(),
				rs.getLong("user_id"),
				rs.getString("user_name"),
				rs.getLong("post_id")
				);
		return comment;
	}


	private List<PostImage> getManyPostImages(long id) {
		var sql = """
				select * from post_images where post_id = ?
				""";
		
		try(var stmt = connection.prepareStatement(sql)) {
			stmt.setLong(1, id);
			var rs = stmt.executeQuery();
			var postImages = new ArrayList<PostImage>();
			while(rs.next()) {
				var postImage = new PostImage(rs.getLong("id"), rs.getString("name"), rs.getLong("post_id"));
				postImages.add(postImage);
			}
			return postImages;
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return null;
	}

	@Override
	public <T> boolean deleteMany(Class<T> e, long id) {
		
		if(e.equals(PostImage.class)) {
			return deleteManyPostImages(id);
		}
		
		if(e.equals(Comment.class)) {
			return deleteManyComments(id);
		}
		
		if(e.equals(SavedPost.class)) {
			return deleteManySavedPosts(id);
		}

		return false;
	}

	private boolean deleteManySavedPosts(long id) {
		var sql = "delete from saved_posts where post_id = ?";
		try(var stmt = connection.prepareStatement(sql)) {
			stmt.setLong(1, id);
			var rows = stmt.executeUpdate();
			if(rows > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean deleteManyComments(long id) {
		var sql = "delete from comments where post_id = ?";
		try(var stmt = connection.prepareStatement(sql)) {
			stmt.setLong(1, id);
			var rows = stmt.executeUpdate();
			if(rows > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean deleteManyPostImages(long id) {
		var sql = "delete from post_images where post_id = ?";
		try(var stmt = connection.prepareStatement(sql)) {
			stmt.setLong(1, id);
			var rows = stmt.executeUpdate();
			if(rows > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public <T> long countMany(Class<T> e, long postId) {
		if(e.equals(Comment.class)) {
			return countManyComments(postId);
		}
		return 0;
	}

	private long countManyComments(long postId) {
		var sql = "select count(*) as commentCount from comments where post_id = ?";
		
		try(var stmt = connection.prepareStatement(sql)) {
			stmt.setLong(1, postId);
			var rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getLong("commentCount");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

}
