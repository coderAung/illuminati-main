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
}
