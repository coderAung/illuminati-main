package edu.ucsy.test;

import org.junit.jupiter.api.Test;

import edu.ucsy.social.utils.StringTool;

public class StringUtilsTest {

	@Test
	void test() {
		var sql = combine("id", "name", "password");
		System.out.println(sql);
	}
	
	private String combine(String ...cols) {
		return StringTool.joinWithComma(cols);
	}
}
