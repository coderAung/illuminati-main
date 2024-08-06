package edu.ucsy.social.data.criteria;

import java.util.ArrayList;
import java.util.List;

public class Criteria {

	private StringBuilder sb;
	private List<Object> values;
	
	private static final String WHERE = " where 1 = 1";
	private static final String WHERE_FORMAT = " and %s %s ?";
	private static final String OR_WHERE_FORMAT = " or %s %s ?";
	
	public Criteria() {
		values = new ArrayList<>();
		sb = new StringBuilder();
	}	
	
	public enum Type {
		EQ("="), NE("<>"),
		GT(">"), LT("<"),
		GE(">="), LE("<="),
		LIKE("like");
		
		private String name;
		
		private Type(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		
	}
	
	public Criteria where(String column, Type type, Object value) {
		
		sb.append(WHERE_FORMAT.formatted(column, type.getName()));
		values.add(value);
		
		return this;
	}
	
	public Criteria orWhere(String column, Type type, Object value) {

		sb.append(OR_WHERE_FORMAT.formatted(column, type.getName()));
		values.add(value);
		
		return this;
	}

	public Criteria limit(long value) {
		return this;
	}
	
	public String generateStatement(String sql) {
		return String.format("%s%s%s", sql, WHERE, sb.toString());
	}
	
	public List<Object> getValues() {
		return List.of(values);
	}
	
}
