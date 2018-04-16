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
package org.mybatis.dynamic.ext.sql.select;

import java.sql.JDBCType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.BindableColumn;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.render.TableAliasCalculator;

public class Function<U> implements BindableColumn<U> {
    
	protected DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
	
	protected Collection<Object> parameters;
    protected String alias;
    protected JDBCType jdbcType;
	protected String functionName;
	protected boolean nextParameterUnparametrized = false;
	
	protected Function(String functionName) {
		this.functionName = functionName;
	}
	
	protected Function(String functionName, Collection<Object> parameters) {
		this(functionName);
		this.parameters = parameters;
	}
	
    protected Function(String functionName, JDBCType jdbcType, Collection<Object> parameters) {
    	this(functionName, parameters);
		this.jdbcType = jdbcType;
	}
    
	@Override
    public String renderWithTableAlias(TableAliasCalculator tableAliasCalculator) {
		 StringBuilder builder = new StringBuilder(functionName + "(");
		 int index = 0;
		 for (Object value: parameters) {
			 if (index > 0 && !nextParameterUnparametrized) {
				 builder.append(", ");
			 }
			 nextParameterUnparametrized = false;
			 index++;
			 builder.append(format(value, tableAliasCalculator)); 
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
		 } else if (param instanceof UnparametrizedColumn) {
			 this.nextParameterUnparametrized = true;
			 return ((UnparametrizedColumn) param).getColumn().renderWithTableAlias(tableAliasCalculator);
		 } else if (param instanceof Date) {
			 return "'" + dateFormat.format((Date) param) + "'";
		 } else {
			 return param.toString();
		 }
	}

	public static <U> Function<U> of(String functionName, JDBCType jdbcType, Collection<Object> parameters) {
        return new Function<>(functionName, jdbcType, parameters);
    }
	
	protected Function<U> copy() {
		return new Function<>(functionName, jdbcType, parameters);
	}

	@Override
	public Optional<String> alias() {
		return Optional.ofNullable(alias);
	}

	@Override
	public BindableColumn<U> as(String alias) {
		Function<U> newThing = copy();
        newThing.alias = alias;
        return newThing;
	}

	@Override
	public Optional<JDBCType> jdbcType() {
    	return Optional.of(jdbcType);	
	}

	@Override
	public Optional<String> typeHandler() {
		return Optional.empty();
	}
}