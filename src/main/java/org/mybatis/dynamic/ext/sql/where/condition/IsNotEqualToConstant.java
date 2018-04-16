package org.mybatis.dynamic.ext.sql.where.condition;

import org.mybatis.dynamic.ext.sql.AbstractConstantCondition;

public class IsNotEqualToConstant<T> extends AbstractConstantCondition<T> {

	protected IsNotEqualToConstant(T value) {
		super(value);
	}

	public static <T> IsNotEqualToConstant<T> of(T value) {
        return new IsNotEqualToConstant<>(value);
    }

	@Override
	public String getOperator() {
		return "<>";
	}
}
