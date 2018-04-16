package org.mybatis.dynamic.ext.sql.select;

public enum DateInterval {
	
	day("day"),
	dayofyear("dayofyear"),
	hour("hour"),
	millisecond("millisecond"),
	minute("minute"),
	month("month"),
	quarter("quarter"),
	second("second"),
	week("week"),
	weekday("weekday"),
	year("year");	
	
	protected String representation;
	
	private DateInterval(String representation) {
		this.representation = representation;
	}
	
	public String toString() {
		return representation;
	}
}
