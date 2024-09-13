package edu.ucsy.test;

import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import edu.ucsy.social.utils.StringTool;

public class StringUtilsTest {

	@Disabled
	@Test
	void test() {
		var sql = combine("id", "name", "password");
		System.out.println(sql);
	}
	
	private String combine(String ...cols) {
		return StringTool.joinWithComma(cols);
	}
	
	@Test
	void test2() {
		var gson = new Gson();
		var result = gson.fromJson("""
				{"photoIds":["28","29","30","31","32","33"]}
				""", JsonObject.class);
		System.out.println(result);
		
		var element = result.get("photoIds");
		System.out.println(element);
		var array = element.getAsJsonArray();
		System.out.println(array);
		var list = new ArrayList<Integer>();
		array.forEach(data -> list.add(data.getAsInt()));
		
		System.out.println(list);
	}
}
