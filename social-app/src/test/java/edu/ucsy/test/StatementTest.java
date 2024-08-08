package edu.ucsy.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.ucsy.social.model.entity.PostImage;
import edu.ucsy.test.db.CustomConnectorFactory;
import edu.ucsy.test.db.DatabaseInitializer;

public class StatementTest {
	
	private static DatabaseInitializer di;
	
	@BeforeAll
	static void init() {
		di = new DatabaseInitializer(CustomConnectorFactory.getConnectorWithAdmin());
		di.loadUser();
		di.loadPostImage();
	}

	@Test
	void test() {
		Set<Integer> idSet = Set.of(1, 2, 3);
		var imgMap = new HashMap<Integer, List<PostImage>>();

		var sql = """
				select pi.id as id, pi.name as image from post_images as pi
				where pi.post_id = ?
					""";
		try(var conn = CustomConnectorFactory.getConnectorWithAdmin().getConnection();
				var stmt = conn.prepareStatement(sql)) {
			
			for(var id : idSet) {
				stmt.setLong(1, id);
				var rs = stmt.executeQuery();
				var imageList = new ArrayList<PostImage>();
				while(rs.next()) {
					var postImage = new PostImage(rs.getLong("id"), rs.getString("image"), id);
					imageList.add(postImage);
				}
				imgMap.put(id, imageList);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(imgMap.size() > 0) {
			System.out.println(imgMap.get(1));
		}
	}
}
