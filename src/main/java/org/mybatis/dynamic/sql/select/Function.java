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
package org.mybatis.dynamic.sql.select;

import java.sql.JDBCType;
import java.util.Collection;
import java.util.Optional;

import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.BindableColumn;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.render.TableAliasCalculator;

public class Function<T, U> implements BindableColumn<U> {
    
	protected T value;
	protected Collection<Object> parameters;
    protected String alias;
    protected JDBCType jdbcType;
	private String functionName;
	
    protected Function(T value, String functionName) {
		this.value = value;
		this.functionName = functionName;
	}
    
    protected Function(T value, String functionName, JDBCType jdbcType) {
		this(value, functionName);
		this.jdbcType = jdbcType;
	}
    
    protected Function(T value, String functionName, Collection<Object> parameters) {
		this.value = value;
		this.functionName = functionName;
		this.parameters = parameters;
	}
    
    protected Function(T value, String functionName, JDBCType jdbcType, Collection<Object> parameters) {
		this(value, functionName);
		this.jdbcType = jdbcType;
		this.parameters = parameters;
	}

	@Override
    public String renderWithTableAlias(TableAliasCalculator tableAliasCalculator) {
		 StringBuilder builder = new StringBuilder(functionName + "(" + format(value, tableAliasCalculator));
		 if (parameters != null) {
			 for (Object value: parameters) {
				 builder.append(", ");
				 builder.append(format(value, tableAliasCalculator)); 
			 }
		 }
	     builder.append(")");           
	     return builder.toString();
    }
	
	@SuppressWarnings("rawtypes")
	private String format(Object param, TableAliasCalculator tableAliasCalculator) {
		if (param instanceof String) {
			return "'" + param + "'"; 
		 } else if (param instanceof BasicColumn) {
			 return ((BasicColumn) param).renderWithTableAlias(tableAliasCalculator);
		 } else if  (param instanceof SqlColumn) {
			 return ((SqlColumn) param).renderWithTableAlias(tableAliasCalculator);
		 } else if  (param instanceof BindableColumn) {
			 return ((BindableColumn) param).renderWithTableAlias(tableAliasCalculator);
		 } else {
			 return param.toString();
		 }
	}
	
	public static <T, U> Function<T, U> of(T value, String functionName) {
        return new Function<>(value, functionName);
    }

	public static <T, U> Function<T, U> of(T value, String functionName, JDBCType jdbcType) {
        return new Function<>(value, functionName, jdbcType);
    }
	
	public static <T, U> Function<T, U> of(T value, String functionName, Collection<Object> parameters) {
        return new Function<>(value, functionName, parameters);
    }

	public static <T, U> Function<T, U> of(T value, String functionName, JDBCType jdbcType, Collection<Object> parameters) {
        return new Function<>(value, functionName, jdbcType, parameters);
    }
	
	protected Function<T, U> copy() {
		return new Function<>(value, functionName, jdbcType, parameters);
	}

	@Override
	public Optional<String> alias() {
		return Optional.ofNullable(alias);
	}

	@Override
	public BindableColumn<U> as(String alias) {
		Function<T, U> newThing = copy();
        newThing.alias = alias;
        return newThing;
	}

	@Override
	public Optional<JDBCType> jdbcType() {
		if (jdbcType != null) {
    		return Optional.of(jdbcType);	
    	}
        return Optional.empty();
	}

	@Override
	public Optional<String> typeHandler() {
		return Optional.empty();
	}
}