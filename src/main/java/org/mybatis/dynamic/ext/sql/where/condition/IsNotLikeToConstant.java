package org.mybatis.dynamic.ext.sql.where.condition;

import org.mybatis.dynamic.ext.sql.AbstractConstantCondition;

public class IsNotLikeToConstant extends AbstractConstantCondition<String> {

	protected IsNotLikeToConstant(String value) {
		super(value);
	}

	public static IsNotLikeToConstant of(String value) {
        return new IsNotLikeToConstant(value);
    }

	@Override
	public String getOperator() {
		return "NOT LIKE";
	}
}
