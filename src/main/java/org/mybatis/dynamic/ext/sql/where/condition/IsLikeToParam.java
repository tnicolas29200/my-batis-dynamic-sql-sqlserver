package org.mybatis.dynamic.ext.sql.where.condition;

import org.mybatis.dynamic.ext.sql.AbstractSingleParamCondition;

public class IsLikeToParam<T> extends AbstractSingleParamCondition<T> {

	protected IsLikeToParam(String paramName) {
		super(paramName);
	}

	public static <T> IsLikeToParam<T> of(String paramName) {
        return new IsLikeToParam<>(paramName);
    }

	@Override
	public String getOperator() {
		return "like";
	}
}
