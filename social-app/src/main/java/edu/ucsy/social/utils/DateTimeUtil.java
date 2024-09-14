package edu.ucsy.social.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

	private static DateTimeFormatter dateTimeFormatter;
	private static DateTimeFormatter dateFormatter;
	private static DateTimeFormatter defaultDirthdateFormatter;
	
	public static DateTimeFormatter getDefaultBirthDateFormatter() {
		if(null == defaultDirthdateFormatter) {
			defaultDirthdateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		}
		return defaultDirthdateFormatter;
	}
	
	public static LocalDate getBirthDateFromString(String stringDate, DateTimeFormatter formatter) {
		return LocalDate.parse(stringDate, formatter);
	}

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
