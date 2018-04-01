package org.mybatis.dynamic.sql.where.condition;

import org.mybatis.dynamic.sql.AbstractSingleParamCondition;

public class IsLikeCaseInsensitiveToParam<T> extends AbstractSingleParamCondition<T> {

	protected IsLikeCaseInsensitiveToParam(String paramName) {
		super(paramName);
	}

	public static <T> IsLikeCaseInsensitiveToParam<T> of(String paramName) {
        return new IsLikeCaseInsensitiveToParam<>(paramName);
    }

	 @Override
	    public String renderCondition(String columnName, String placeholder) {
	        return "upper(" + columnName + ") like upper(#{" + getParamName() + "})";
	    }
	
	@Override
	public String getOperator() {
		return "like";
	}
}
