/**
 *    Copyright 2018 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
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
