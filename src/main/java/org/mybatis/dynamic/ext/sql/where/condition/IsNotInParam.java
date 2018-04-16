package org.mybatis.dynamic.ext.sql.where.condition;

import org.mybatis.dynamic.ext.sql.AbstractSingleParamCondition;

public class IsNotInParam<T> extends AbstractSingleParamCondition<T> {

	protected IsNotInParam(String paramName) {
		super(paramName);
	}

	public static <T> IsNotInParam<T> of(String paramName) {
        return new IsNotInParam<>(paramName);
    }

	@Override
	public String renderCondition(String columnName, String placeholder) {
		return columnName + " " + getOperator() + " (#{" + getParamName() + "})";
	}
	
	@Override
	public String getOperator() {
		return "NOT IN";
	}
}
