/**
 *    Copyright 2016-2017 the original author or authors.
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

import org.mybatis.dynamic.ext.sql.AbstractSingleParamCondition;

public class IsLessThanOrEqualToParam<T> extends AbstractSingleParamCondition<T> {

	protected IsLessThanOrEqualToParam(String paramName) {
		super(paramName);
	}

	public static <T> IsLessThanOrEqualToParam<T> of(String paramName) {
        return new IsLessThanOrEqualToParam<>(paramName);
    }

	@Override
	public String getOperator() {
		return "<=";
	}
}
