package edu.ucsy.social.utils;

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
}
