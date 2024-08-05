package edu.ucsy.test.db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;

import edu.ucsy.social.data.db.DatabaseConnector;

public class DatabaseInitializer {

	DatabaseConnector connector;
	private static final String TRUNCATE = "truncate %s";
	private static StringBuilder sb = new StringBuilder();
	
	public DatabaseInitializer(DatabaseConnector connector) {
		this.connector = connector;
	}
	
	public void truncate(String table) {
		var sql = TRUNCATE.formatted(table);
		try(var conn = connector.getConnection();
				var stmt = conn.prepareStatement(sql)) {
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void loadUser() {
		try(var lines = Files.lines(Path.of("ex-source/load-user.txt"));) {
			lines.forEach(System.out::println);
			for(var l : lines.toList()) {
				sb.append(l);
			}
			
			var sql = sb.toString();
			sb.setLength(0);
			System.out.println(sql);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
