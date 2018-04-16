package org.mybatis.dynamic.ext.sql.where.condition;

import org.mybatis.dynamic.ext.sql.AbstractSingleParamCondition;

public class IsNotEqualToParam<T> extends AbstractSingleParamCondition<T> {

	protected IsNotEqualToParam(String paramName) {
		super(paramName);
	}

	public static <T> IsNotEqualToParam<T> of(String paramName) {
        return new IsNotEqualToParam<>(paramName);
    }

	@Override
	public String getOperator() {
		return "<>";
	}
}
