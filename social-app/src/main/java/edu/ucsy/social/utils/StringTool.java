package edu.ucsy.social.utils;

import java.time.LocalDateTime;

import edu.ucsy.social.model.entity.type.ImageStatus;

public class StringTool {

	public static String joinWithComma(String [] array) {
		var sb = new StringBuilder();
		for(int i = 0; i < array.length; i ++) {
			if(i == array.length - 1) {
				sb.append("%s".formatted(array[i]));
			} else {
				sb.append("%s, ".formatted(array[i]));
			}
		}
		return sb.toString();
	}
	
	public static boolean isEmpty(String string) {
		return null == string || 
				"".equals(string) || 
				string.isEmpty() || 
				string.isBlank();
	}
	
	public static String generateImageName(String imageName, int userId, String userName) {
		
		var format = "%s_%s_%s_%s";
		var dateString = LocalDateTime.now().toString();
		return format.formatted(userId, userName, dateString, imageName);
	}
	
	public static void main(String[] args) {
		var name1 = generateImageName("img1.jsp", 1, "Aung Aung");
		System.out.println(name1);

		var name2 = generateImageName("img1.jsp", 1, "Aung Aung");
		System.out.println(name2);
		
		System.out.println(name1.equals(name2));
	}
}