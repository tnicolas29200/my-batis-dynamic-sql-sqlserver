package org.mybatis.dynamic.sql.where.condition;

import org.mybatis.dynamic.sql.AbstractSingleParamCondition;

public class IsEqualToParam<T> extends AbstractSingleParamCondition<T> {

	protected IsEqualToParam(String paramName) {
		super(paramName);
	}

	public static <T> IsEqualToParam<T> of(String paramName) {
        return new IsEqualToParam<>(paramName);
    }

	@Override
	public String getOperator() {
		return "=";
	}
}
