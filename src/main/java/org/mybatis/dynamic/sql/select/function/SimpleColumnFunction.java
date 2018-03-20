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
package org.mybatis.dynamic.sql.select.function;

import java.sql.JDBCType;
import java.util.Optional;

import org.mybatis.dynamic.sql.BindableColumn;
import org.mybatis.dynamic.sql.render.TableAliasCalculator;
import org.mybatis.dynamic.sql.select.function.AbstractFunction;

public class SimpleColumnFunction<T, U extends AbstractFunction<T, U>> extends AbstractFunction<T, SimpleColumnFunction<T,U>> {
    
	private String functionName;
	private JDBCType jdbcType;
	
    protected SimpleColumnFunction(BindableColumn<T> column, String functionName) {
		super(column);
		this.functionName = functionName;
	}
    
    protected SimpleColumnFunction(BindableColumn<T> column, String functionName, JDBCType jdbcType) {
		this(column, functionName);
		this.jdbcType = jdbcType;
	}

	@Override
    public String renderWithTableAlias(TableAliasCalculator tableAliasCalculator) {
		 return functionName + "(" //$NON-NLS-1$
	                + column.renderWithTableAlias(tableAliasCalculator)
	                + ")"; //$NON-NLS-1$
    }
	
	public static <T, U extends AbstractFunction<T, U>> SimpleColumnFunction<T, U> of(BindableColumn<T> column, String functionName) {
        return new SimpleColumnFunction<T, U>(column, functionName);
    }

	public static <T, U extends AbstractFunction<T, U>> SimpleColumnFunction<T, U> of(BindableColumn<T> column, String functionName, JDBCType jdbcType) {
        return new SimpleColumnFunction<T, U>(column, functionName, jdbcType);
    }
	
	@Override
	protected SimpleColumnFunction<T, U> copy() {
		return new SimpleColumnFunction<>(column, functionName, jdbcType);
	}
	
	@Override
    public Optional<JDBCType> jdbcType() {
		if (this.jdbcType != null) {
			return Optional.of(this.jdbcType);
		}
		return column.jdbcType();
    }
}
