package org.mybatis.dynamic.ext.sql.where.condition;

import org.mybatis.dynamic.ext.sql.AbstractSingleParamCondition;

public class IsInParam<T> extends AbstractSingleParamCondition<T> {

	protected IsInParam(String paramName) {
		super(paramName);
	}

	public static <T> IsInParam<T> of(String paramName) {
        return new IsInParam<>(paramName);
    }

	@Override
	public String renderCondition(String columnName, String placeholder) {
		return columnName + " " + getOperator() + " (#{" + getParamName() + "})";
	}
	
	@Override
	public String getOperator() {
		return "IN";
	}
}
