package edu.ucsy.social.utils;

import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

	private static DateTimeFormatter dateTimeFormatter;
	private static DateTimeFormatter dateFormatter;

	public static DateTimeFormatter getDateTimeFormatter() {
		if(null == dateTimeFormatter) {
			dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM");
		}
		return dateTimeFormatter;
	}

	public static DateTimeFormatter getDateFormatter() {
		if(null == dateFormatter) {
			dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		}
		return dateFormatter;
	}
}
