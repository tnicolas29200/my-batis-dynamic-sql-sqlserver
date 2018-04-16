package org.mybatis.dynamic.ext.sql.where.condition;

import org.mybatis.dynamic.ext.sql.AbstractSingleParamCondition;

public class IsNotLikeToParam<T> extends AbstractSingleParamCondition<T> {

	protected IsNotLikeToParam(String paramName) {
		super(paramName);
	}

	public static <T> IsNotLikeToParam<T> of(String paramName) {
        return new IsNotLikeToParam<>(paramName);
    }

	@Override
	public String getOperator() {
		return "NOT LIKE";
	}
}
