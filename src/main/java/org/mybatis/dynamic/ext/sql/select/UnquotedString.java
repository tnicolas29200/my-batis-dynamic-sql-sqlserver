package org.mybatis.dynamic.ext.sql.select;

public class UnquotedString {
	protected String value;
	
	public UnquotedString(String value) {
		this.value = value;
	}
	
	public String toString() {
		return value;
	}
}
