package edu.ucsy.test.db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import edu.ucsy.social.data.db.DatabaseConnector;
import edu.ucsy.social.model.entity.Post;
import edu.ucsy.social.model.entity.PostImage;
import edu.ucsy.social.model.entity.User;
import edu.ucsy.social.model.entity.User.Role;
import edu.ucsy.social.model.entity.User.Status;

public class DatabaseInitializer {

	DatabaseConnector connector;
	private static final String TRUNCATE = "truncate %s";
	
	public DatabaseInitializer(DatabaseConnector connector) {
		this.connector = connector;
	}
	
	public void truncate(String table) {
		var sql1 = "set foreign_key_checks = 0";
		var sql2 = TRUNCATE.formatted(table);
		try(var conn = connector.getConnection();
				var stmt1 = conn.prepareStatement(sql1);
				var stmt2 = conn.prepareStatement(sql2)) {
			
			stmt1.execute();
			stmt2.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void loadUser() {
		truncate("users");
		var users = getUsers();

		users.forEach(u -> {
			System.out.println("----------");
			System.out.printf("%s %s %s%n".formatted(u.email(), u.name(), u.password()));
		});
		var sql = """
				insert into users (email, name, password, role, created_at, updated_at)
				 values (?, ?, ?, ?, ?, ?)
				""";

		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql)) {
			
			for(var user : users) {
				stmt.setString(1, user.email());
				stmt.setString(2, user.name());
				stmt.setString(3, user.password());
				stmt.setInt(4, user.role().ordinal() + 1);
				var createdAt = Timestamp.valueOf(LocalDateTime.now());
				var updatedAt = Timestamp.valueOf(LocalDateTime.now());
				stmt.setTimestamp(5, createdAt);
				stmt.setTimestamp(6, updatedAt);
				
				stmt.addBatch();
			}
			
			stmt.executeBatch();

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private List<User> getUsers() {

		try(var lines = Files.lines(Path.of("sample/users.txt"))) {
		
			var users = lines.map(line -> line.split("\t"))
				.map(this::userFrom).toList();
			
			return users;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public User userFrom(String [] array) {
		return new User(array[0], array[1], array[2], Role.valueOf(array[3]), Status.valueOf(array[4]));
	}
	
	public void loadPostImage() {
		truncate("post_images");
		var sql1 = "set foreign_key_checks = 0";
		var sql2 = "insert into post_images (name, post_id) values (?, ?)";
		
		var postImages = getPostImages();
		
		try(var conn = connector.getConnection();
				var stmt1 = conn.createStatement();
				var stmt2 = conn.prepareStatement(sql2)) {
			
			stmt1.execute(sql1);
			
			for(var pi : postImages) {
				stmt2.setString(1, pi.name());
				stmt2.setLong(2, pi.postId());
				stmt2.addBatch();
			}
			
			stmt2.executeBatch();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private List<PostImage> getPostImages() {
		try(var lines = Files.lines(Path.of("sample/post-images.txt"))) {
			var list = lines.map(line -> line.split("\t"))
							.map(array -> postImageFrom(array))
							.toList();
			return list;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private PostImage postImageFrom(String[] array) {
		var postImage = new PostImage(array[0], Long.parseLong(array[1]));
		return postImage;
	}
	
	
	public void loadPost() {
		truncate("posts");
		var posts = getPosts();
		var sql = """
				insert into posts 
				(content, user_id, created_at) 
				values (?, ?, ?)
				""";
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql)) {
			var createdAt = Timestamp.valueOf(LocalDateTime.now());
			for(var p : posts) {
				stmt.setString(1, p.content());
				stmt.setLong(2, p.userId());
				stmt.setTimestamp(3, createdAt);
				stmt.addBatch();
			}
			
			stmt.executeBatch();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private List<Post> getPosts() {
		try(var lines = Files.lines(Path.of("sample/posts.txt"))) {
			var list = lines.map(line -> line.split("\t"))
							.map(array -> postFrom(array))
							.toList();
			return list;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Post postFrom(String[] array) {
		var post = new Post(array[0], Long.parseLong(array[1]), array[2]);
		
		return post;
	}
	
}
