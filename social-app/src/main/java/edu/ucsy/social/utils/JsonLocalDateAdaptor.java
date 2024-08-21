package edu.ucsy.social.utils;

import java.lang.reflect.Type;
import java.time.LocalDate;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class JsonLocalDateAdaptor implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

	@Override
	public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		return LocalDate.parse(json.getAsString(), DateTimeUtil.getDateFormatter());
	}

	@Override
	public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(src.format(DateTimeUtil.getDateFormatter()));
	}

}
