package edu.ucsy.social.data.criteria;

import java.util.ArrayList;
import java.util.List;

public class Criteria {

	private StringBuilder sb;
	private List<Object> values;
	
	private static final String WHERE = " where 1 = 1";
	private static final String WHERE_FORMAT = " and %s %s ?";
	private static final String OR_WHERE_FORMAT = " or %s %s ?";
	private static final String ORDER_BY = " order by %s %s";
	private static final String LIMIT = " limit %s";
	
	private String orderByColumn;
	private long limit;
	private Type orderByType;
	
	public Criteria() {
		values = new ArrayList<>();
		sb = new StringBuilder();
	}
	
	public enum Type {
		EQ("="), NE("<>"),
		GT(">"), LT("<"),
		GE(">="), LE("<="),
		LIKE("like"),
		ASC("asc"), DESC("desc");
		
		private String value;
		
		private Type(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
		
	}
	
	public Criteria where(String column, Type type, Object value) {
		
		sb.append(WHERE_FORMAT.formatted(column, type.getValue()));
		values.add(value);
		
		return this;
	}
	
	public Criteria orWhere(String column, Type type, Object value) {

		sb.append(OR_WHERE_FORMAT.formatted(column, type.getValue()));
		values.add(value);
		
		return this;
	}

	public Criteria limit(long value) {
		limit = value;
		return this;
	}
	
	public Criteria orderBy(String column) {
		return orderBy(column, Type.DESC);
	}
	
	public Criteria orderBy(String column, Type type) {
		orderByColumn = column;
		orderByType = type;
		return this;
	}

	
	public String generateStatement(String sql) {
		
		if(null != orderByColumn) {
			sb.append(ORDER_BY.formatted(orderByColumn, orderByType.getValue()));
		}
		
		if(0 != limit) {
			sb.append(LIMIT.formatted(limit));
		}
		
		return String.format("%s%s%s", sql, WHERE, sb.toString());
	}
	
	public List<Object> getValues() {
		return new ArrayList<Object>(values);
	}
	
}
