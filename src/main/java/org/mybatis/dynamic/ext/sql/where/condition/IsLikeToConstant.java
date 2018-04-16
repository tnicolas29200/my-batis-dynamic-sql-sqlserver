package org.mybatis.dynamic.ext.sql.where.condition;

import org.mybatis.dynamic.ext.sql.AbstractConstantCondition;

public class IsLikeToConstant extends AbstractConstantCondition<String> {

	protected IsLikeToConstant(String value) {
		super(value);
	}

	public static IsLikeToConstant of(String value) {
        return new IsLikeToConstant(value);
    }

	@Override
	public String getOperator() {
		return "like";
	}
}
