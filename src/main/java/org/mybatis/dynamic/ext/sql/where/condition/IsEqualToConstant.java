package org.mybatis.dynamic.ext.sql.where.condition;

import org.mybatis.dynamic.ext.sql.AbstractConstantCondition;

public class IsEqualToConstant<T> extends AbstractConstantCondition<T> {
	
	protected IsEqualToConstant(T value) {
		super(value);
	}

	public static <T> IsEqualToConstant<T> of(T value) {
        return new IsEqualToConstant<>(value);
    }

	@Override
	public String getOperator() {
		return "=";
	}
}
