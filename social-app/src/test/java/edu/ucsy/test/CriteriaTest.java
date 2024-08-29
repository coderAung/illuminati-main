package edu.ucsy.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.ucsy.social.data.criteria.Criteria;
import edu.ucsy.social.data.criteria.Criteria.Type;

public class CriteriaTest {

	@Test
	void test() {
		var result = "select * from posts where 1 = 1 and user_id = ? or id < ? and content like ?";
		var sql = "select * from posts";
		
		var statement = new Criteria()
							.where("user_id", Type.EQ, 1)
							.orWhere("id", Type.LT, 3)
							.where("content", Type.LIKE, "%something")
							.generateStatement(sql);
				
		System.out.println(statement);
		assertEquals(result, statement);
	}
	
	@Test
	void test2() {
		var result = "select * from posts where 1 = 1 and user_id = ? order by created_at desc limit 3";
		
		var sql = "select * from posts";
		
		var statement = new Criteria()
							.where("user_id", Type.EQ, 2)
							.limit(3)
							.orderBy("created_at")
							.generateStatement(sql);
		
		System.out.println(statement);
		assertEquals(result, statement);
	}
	
	@Test
	void test3() {
		var result = "select * from users where 1 = 1 and email = ? and password = ?";
		var sql = "select * from users";
		var criteria = new Criteria().where("email", Type.EQ, "aung aung")
										.where("password", Type.EQ, "password");
		
		System.out.println(criteria.generateStatement(sql));
		assertEquals(result, criteria.generateStatement(sql));
	}
	
	@Test
	void test4() {
		var sql = "select * from users";
		
		var criteria = new Criteria().limit(5).orderBy("id").limit(6).orderBy("created_at", Type.ASC);
		var stmt = criteria.generateStatement(sql);
		
		System.out.println(stmt);
	}
}
