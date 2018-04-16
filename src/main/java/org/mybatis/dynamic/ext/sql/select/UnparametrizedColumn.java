package org.mybatis.dynamic.ext.sql.select;

import org.mybatis.dynamic.sql.BindableColumn;

public class UnparametrizedColumn<T> {
	protected BindableColumn<T> column;
	
	public UnparametrizedColumn(BindableColumn<T> column) {
		this.column = column;
	}
	
	public BindableColumn<T> getColumn() {
		return column;
	}
}	
