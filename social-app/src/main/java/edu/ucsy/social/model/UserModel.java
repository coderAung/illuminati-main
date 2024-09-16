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
import edu.ucsy.social.data.OneToOne;
import edu.ucsy.social.model.entity.CoverImage;
import edu.ucsy.social.model.entity.Friend;
import edu.ucsy.social.model.entity.FriendRequest;
import edu.ucsy.social.model.entity.Post;
import edu.ucsy.social.model.entity.ProfileImage;
import edu.ucsy.social.model.entity.SavedPost;
import edu.ucsy.social.model.entity.User;
import edu.ucsy.social.model.entity.User.Role;
import edu.ucsy.social.model.entity.User.Status;
import edu.ucsy.social.model.entity.type.ImageStatus;
import edu.ucsy.social.utils.StringTool;

public class UserModel extends AbstractModel<User> 
				implements OneToOne, OneToMany {

	@Override
	public User save(User u) {
		var sql = """
				insert into users (email, name, password, role, created_at, updated_at)
				 values (?, ?, ?, ?, ?, ?)
				""";
		
		try(var stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			stmt.setString(1, u.email());
			stmt.setString(2, u.name());
			stmt.setString(3, u.password());
			stmt.setInt(4, u.role().ordinal() + 1);
			var createdAt = Timestamp.valueOf(LocalDateTime.now());
			var updatedAt = Timestamp.valueOf(LocalDateTime.now());
			stmt.setTimestamp(5, createdAt);
			stmt.setTimestamp(6, updatedAt);

			var row = stmt.executeUpdate();
			if(row == 0) {
				return null;
			}
			
			var keys = stmt.getGeneratedKeys();
			if(keys.next()) {
				var user = u.perfectClone(keys.getLong(1), createdAt.toLocalDateTime(), updatedAt.toLocalDateTime());
				return user;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User findOne(long id) {
		var sql = "select * from users where id = ?";
		try(var stmt = connection.prepareStatement(sql)) {
			stmt.setLong(1, id);
			var rs = stmt.executeQuery();
			if(rs.next()) {
				return userFrom(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> getAll() {
		var sql = "select * from users";
		try(var stmt = connection.prepareStatement(sql)) {
			
			var rs = stmt.executeQuery();
			var users = new ArrayList<User>();
			
			while (rs.next()) {
				var user = userFrom(rs);
				users.add(user);
			}
			return users;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> get(long limit) {
		var sql = "select * from users limit ?";
		try(var stmt = connection.prepareStatement(sql)) {
			
			stmt.setLong(1, limit);
			var rs = stmt.executeQuery();
			var users = new ArrayList<User>();
			while(rs.next()) {
				var user = userFrom(rs);
				users.add(user);
			}
			return users;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User update(User u) {
		var sql = "select * from users where id = ?";
		try(var stmt = connection.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)) {
			
			stmt.setLong(1, u.id());
			
			var rs = stmt.executeQuery();
			
			if(rs.next()) {

				// email update
				if(!u.email().equals(rs.getString("email"))) {
					rs.updateString("email", u.email());
				}
				// name update
				if(!u.name().equals(rs.getString("name"))) {
					rs.updateString("name", u.name());
				}
				// password update
				if(!u.password().equals(rs.getString("password"))) {
					rs.updateString("password", u.password());
				}
				// role update
				if(!u.role().equals(Role.valueOf(rs.getString("role")))) {
					rs.updateInt("role", u.role().ordinal() + 1);
				}
				
				// status update
				if(!u.status().equals(Status.valueOf(rs.getString("status")))) {
					rs.updateInt("status", u.status().ordinal() + 1);
				}
				
				var updatedAt = Timestamp.valueOf(LocalDateTime.now());
				rs.updateTimestamp("updated_at", updatedAt);

				rs.updateRow();
				return u.updatedClone(updatedAt.toLocalDateTime());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}

	@Override
	public User fullUpdate(User u) {
		var sql = """
				update users 
				set email = ?, name = ?, password = ?,
					role = ?, status = ?, updated_at = ?
				where id = ?
				""";
		try(var stmt = connection.prepareStatement(sql)) {
			
			stmt.setString(1, u.email());
			stmt.setString(2, u.name());
			stmt.setString(3, u.password());
			stmt.setInt(4, u.role().ordinal() + 1);
			stmt.setInt(5, u.status().ordinal() + 1);

			var updatedAt = Timestamp.valueOf(LocalDateTime.now());
			stmt.setTimestamp(6, updatedAt);
			
			stmt.setLong(7, u.id());
			
			var row = stmt.executeUpdate();
			if(0 == row) {
				return null;
			}
			
			return u.updatedClone(updatedAt.toLocalDateTime());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(long id) {
		var sql = "delete from users where id = ?";
		try(var stmt = connection.prepareStatement(sql)) {
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

	private User userFrom(ResultSet rs) throws SQLException {
		var user = new User(
				rs.getLong("id"),
				rs.getString("email"), 
				rs.getString("name"),
				rs.getString("password"),
				Role.valueOf(rs.getString("role")),
				Status.valueOf(rs.getString("status")),
				rs.getTimestamp("created_at").toLocalDateTime(),
				rs.getTimestamp("updated_at").toLocalDateTime());
		return user;
	}
	
	@Override
	public ResultSet findOne(long id, String... cols) {
		var sql = "select %s from users where id = ?";
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
	public <T> T getOne(Class<T> e, long id) {
		if(e.equals(ProfileImage.class)) {
			var profileImage = getOneProfileImage(id);
			if(null != profileImage) {
				return (T) profileImage;
			}
		}
		
		if(e.equals(CoverImage.class)) {
			var coverImage = getOneCoverImage(id);
			if(null != coverImage) {
				return (T) coverImage;
			}
		}
		return null;
	}

	private CoverImage getOneCoverImage(long id) {
		var sql = "select * from cover_images where user_id = ? and status = ?";
		
		try(var stmt = connection.prepareStatement(sql)) {
			stmt.setLong(1, id);
			stmt.setString(2, ImageStatus.ACTIVE.name());
			var rs = stmt.executeQuery();
			
			if(rs.next()) {
				var coverImage = new CoverImage(rs.getLong("id"),
												rs.getString("name"),
												rs.getLong("user_id"),
												ImageStatus.valueOf(rs.getString("status")),
												rs.getTimestamp("uploaded_at").toLocalDateTime());
				return coverImage;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private ProfileImage getOneProfileImage(long id) {
		var sql = "select * from profile_images where user_id = ? and status = ?";
		
		try(var stmt = connection.prepareStatement(sql)) {
			stmt.setLong(1, id);
			stmt.setString(2, ImageStatus.ACTIVE.name());
			var rs = stmt.executeQuery();
			
			if(rs.next()) {
				var profileImage = new ProfileImage(rs.getLong("id"),
												rs.getString("name"),
												rs.getLong("user_id"),
												ImageStatus.valueOf(rs.getString("status")),
												rs.getTimestamp("uploaded_at").toLocalDateTime());
				return profileImage;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	@Override
	public <T> boolean deleteOne(Class<T> e, long id) {
		if(e.equals(ProfileImage.class)) {
			var result = deleteOneProfileImage(id);
			return result;
		} else if(e.equals(CoverImage.class)) {
			var result = deleteOneCoverImage(id);
			return result;
		}
		return false;
	}

	private boolean deleteOneCoverImage(long id) {
		var sql = "delete from cover_images where id = ?";
		try(var stmt = connection.prepareStatement(sql)) {
			stmt.setLong(1, id);
			var row = stmt.executeUpdate();
			if(0 < row) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean deleteOneProfileImage(long id) {
		var sql = "delete from profile_images where id = ?";
		try(var stmt = connection.prepareStatement(sql)) {
			stmt.setLong(1, id);
			var row = stmt.executeUpdate();
			if(0 < row) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public <T> List<T> getMany(Class<T> e, long id) {
		return getMany(e, id, 0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getMany(Class<T> e, long id, long limit) {
		
		if(e.equals(Post.class)) {
			var posts = getManyPosts(id, limit);
			return posts.stream().map(post -> (T) post).toList();
		}

		if(e.equals(SavedPost.class)) {
			var savedPosts = getManySavedPosts(id, limit);
			if(null != savedPosts) {
				return savedPosts.stream().map(sp -> (T) sp).toList();
			}
		}
		
//		if(e.equals(FriendRequest.class)) {
//			var friendRequests = getManyFriendRequests(id, limit);
//			return friendRequests.stream().map(fr -> (T) fr).toList();
//		}
		
		return null;
	}

	private List<SavedPost> getManySavedPosts(long id, long limit) {
		var sql = "select * from saved_posts where user_id = ? order by saved_at desc";
		try(var stmt = connection.prepareStatement(sql)) {
			stmt.setLong(1, id);
			var rs = stmt.executeQuery();
			var savedPosts = new ArrayList<SavedPost>();
			while(rs.next()) {
				var savedPost = savedPostFrom(rs);
				savedPosts.add(savedPost);
			}
			return savedPosts;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private SavedPost savedPostFrom(ResultSet rs) throws SQLException {
		var savedpost = new SavedPost(
				rs.getLong("id"), 
				rs.getLong("post_id"), 
				rs.getLong("user_id"),
				rs.getTimestamp("saved_at").toLocalDateTime());
		return savedpost;
	}
	
	//
//	private List<FriendRequest> getManyFriendRequests(long id, long limit) {
//		
//		var sql = """
//				select 
//				""";
//		
//		return null;
//	}

	private List<Post> getManyPosts(long id, long limit) {
		var sql = """
				select p.id, p.content, p.created_at, p.updated_at, p.user_id, u.name as user_name
				from posts as p
				join users as u on u.id = p.user_id
				where p.user_id = ? order by created_at desc
				""";
		if(0 < limit) {
			sql = """
				select p.id, p.content, p.created_at, p.updated_at, p.user_id, u.name as user_name
					from posts as p
					join users as u on u.id = p.user_id
					where user_id = ? order by updated_at desc limit ?
					""";
		}
		try(var stmt = connection.prepareStatement(sql)) {
			stmt.setLong(1, id);
			if(0 < limit) {
				stmt.setLong(2, limit);
			}
			
			var rs = stmt.executeQuery();
			var posts = new ArrayList<Post>();
			while(rs.next()) {
				var post = postFrom(rs);
				posts.add(post);
			}
			
			return posts;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
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
	public <T> boolean deleteMany(Class<T> e, long id) {
		if(e.equals(Post.class)) {
			var result = deleteManyPost(id);
			return result;
		}
		return false;
	}

	private boolean deleteManyPost(long id) {
		var sql = "delete from posts where user_id = ?";
		try(var stmt = connection.prepareStatement(sql)) {
			stmt.setLong(1, id);
			var row = stmt.executeUpdate();
			if(0 < row) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public <T> long countMany(Class<T> e, long userId) {
		if(e.equals(Friend.class)) {
			return countManyFriends(userId);
		}
		if(e.equals(SavedPost.class)) {
			return countManySavedPosts(userId);
		}
		if(e.equals(FriendRequest.class)) {
			return countManyFriendRequest(userId);
		}
		if(e.equals(Post.class)) {
			return countManyPosts(userId);
		}
		return 0;
	}

	private long countManyPosts(long userId) {
		var sql = "select count(*) as postCount from posts where user_id = ?";
		try(var stmt = connection.prepareStatement(sql)) {
			stmt.setLong(1, userId);
			var rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getLong("postCount");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	private long countManyFriendRequest(long userId) {
		var sql = "select count(*) as friend_request_count from friend_requests where request_to = ?";
		try(var stmt = connection.prepareStatement(sql)) {
			stmt.setLong(1, userId);
			var rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getLong("friend_request_count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	private long countManySavedPosts(long userId) {
		var sql = "select count(*) as saved_post_count from saved_posts where user_id = ?";
		try(var stmt = connection.prepareStatement(sql)) {
			stmt.setLong(1, userId);
			var rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getLong("saved_post_count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	private long countManyFriends(long userId) {
		var sql = "select count(*) as friend_count from friends where user_id = ?";
		try(var stmt = connection.prepareStatement(sql)) {
			stmt.setLong(1, userId);
			var rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getLong("friend_count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
