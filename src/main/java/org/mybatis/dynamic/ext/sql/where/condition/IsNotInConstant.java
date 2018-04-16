package org.mybatis.dynamic.ext.sql.where.condition;

import org.mybatis.dynamic.ext.sql.AbstractConstantCondition;

public class IsNotInConstant<T> extends AbstractConstantCondition<T> {

	protected String valueFormatted;
	
	protected IsNotInConstant(String value) {
		super(null);
		this.valueFormatted = value;
	}

	public static <T> IsNotInConstant<T> of(String value) {
        return new IsNotInConstant<>(value);
    }

	@Override
	public String renderCondition(String columnName, String placeholder) {
		return columnName + " " + getOperator() + " (" + valueFormatted + ")";
	}
	
	@Override
	public String getOperator() {
		return "NOT IN";
	}
}
