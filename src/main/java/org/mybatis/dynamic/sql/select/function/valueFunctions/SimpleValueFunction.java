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
package org.mybatis.dynamic.sql.select.function.valueFunctions;

import java.sql.JDBCType;

import org.mybatis.dynamic.sql.render.TableAliasCalculator;
import org.mybatis.dynamic.sql.select.function.abs.AbstractValueFunction;

public class SimpleValueFunction<T> extends AbstractValueFunction<T> {
    
	private String functionName;
	
    protected SimpleValueFunction(T value, String functionName) {
		super(value);
		this.functionName = functionName;
	}
    
    protected SimpleValueFunction(T value, String functionName, JDBCType jdbcType) {
		super(value, jdbcType);
		this.functionName = functionName;
	}

	@Override
    public String renderWithTableAlias(TableAliasCalculator tableAliasCalculator) {
		 return functionName + "("
	                + (value instanceof String ? "'" + value + "'" : value)
	                + ")";
    }
	
	public static <T> SimpleValueFunction<T> of(T value, String functionName) {
        return new SimpleValueFunction<>(value, functionName);
    }

	public static <T> SimpleValueFunction<T> of(T value, String functionName, JDBCType jdbcType) {
        return new SimpleValueFunction<>(value, functionName, jdbcType);
    }
	
	@Override
	protected SimpleValueFunction<T> copy() {
		return new SimpleValueFunction<>(value, functionName, jdbcType);
	}
}