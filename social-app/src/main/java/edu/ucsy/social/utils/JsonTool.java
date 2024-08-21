package edu.ucsy.social.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class JsonTool {

	private static Gson gsonWithLocalDateTime;
	private static Gson gsonWithLocalDate;
	
	static {
		if(null == gsonWithLocalDateTime) {
			gsonWithLocalDateTime = new GsonBuilder()
					.registerTypeAdapter(LocalDateTime.class, new JsonLocalDateTimeAdaptor())
					.create();
		}
		
		if(null == gsonWithLocalDate) {
			gsonWithLocalDate = new GsonBuilder()
					.registerTypeAdapter(LocalDate.class, new JsonLocalDateAdaptor())
					.create();
		}
	}
	
	public static String jsonFromList(List<Object> list) {
		return gsonWithLocalDateTime.toJson(list);
	}
	
	public static String jsonFromSingleObject(Object object) {
		return gsonWithLocalDateTime.toJson(object);
	}
	
	public static String jsonFromMap(Object object) {
		return gsonWithLocalDateTime.toJson(object);
	}
	
}
