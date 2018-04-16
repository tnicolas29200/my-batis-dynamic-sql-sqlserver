package org.mybatis.dynamic.ext.sql.where.condition;

import org.mybatis.dynamic.ext.sql.AbstractConstantCondition;

public class IsInConstant<T> extends AbstractConstantCondition<T> {

	protected String valueFormatted;
	
	protected IsInConstant(String value) {
		super(null);
		this.valueFormatted = value;
	}

	public static <T> IsInConstant<T> of(String value) {
        return new IsInConstant<>(value);
    }

	@Override
	public String renderCondition(String columnName, String placeholder) {
		return columnName + " " + getOperator() + " (" + valueFormatted + ")";
	}
	
	@Override
	public String getOperator() {
		return "IN";
	}
}
