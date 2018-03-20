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
package org.mybatis.dynamic.sqlserver;

import java.sql.JDBCType;
import java.util.Arrays;

import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BindableColumn;
import org.mybatis.dynamic.sql.select.function.AbstractFunction;
import org.mybatis.dynamic.sql.select.function.MultipleColumnFunction;
import org.mybatis.dynamic.sql.select.function.SimpleColumnFunction;
import org.mybatis.dynamic.sql.select.function.SimpleColumnWithParametersFunction;

public interface SQLServerBuilder {
	
	static <U extends AbstractFunction<String, U>> SimpleColumnFunction<String, U> ascii(BindableColumn<String> column) {
        return SimpleColumnFunction.of(column, "ASCII");
    }
	
	static <U extends AbstractFunction<Integer, U>> SimpleColumnFunction<Integer, U> character(BindableColumn<Integer> column) {
		return SimpleColumnFunction.of(column, "CHAR", JDBCType.VARCHAR);
	}
	
	static <U extends AbstractFunction<String, U>> SimpleColumnWithParametersFunction<String, U> charindex(BindableColumn<String> column, String substring) {
		return SimpleColumnWithParametersFunction.of(column, "CHARINDEX", Arrays.asList("'" + substring + "'"), JDBCType.INTEGER);
	}
	
	static <U extends AbstractFunction<String, U>> SimpleColumnWithParametersFunction<String, U> charindex(BindableColumn<String> column, String substring, Integer startPosition) {
		return SimpleColumnWithParametersFunction.of(column, "CHARINDEX", Arrays.asList("'" + substring + "'", startPosition), JDBCType.INTEGER);
	}
	
	@SafeVarargs
	static <U extends MultipleColumnFunction<String, U>> MultipleColumnFunction<String, U> concat(BindableColumn<String> firstColumn, BindableColumn<String> secondColumn, BindableColumn<String>... subsequentColumns) {
		return MultipleColumnFunction.of(firstColumn, secondColumn, Arrays.asList(subsequentColumns), "CONCAT");
	}
	
	static <U extends AbstractFunction<String, U>> SimpleColumnFunction<String, U> datalength(BindableColumn<String> column) {
		return SimpleColumnFunction.of(column, "DATALENGTH", JDBCType.INTEGER);
	}
	
	static <U extends AbstractFunction<String, U>> SimpleColumnWithParametersFunction<String, U> left(BindableColumn<String> column, Integer numberOfChars) {
		return SimpleColumnWithParametersFunction.of(column, "LEFT", Arrays.asList(numberOfChars));
	}
	
	static <U extends AbstractFunction<String, U>> SimpleColumnFunction<String, U> len(BindableColumn<String> column) {
		return SimpleColumnFunction.of(column, "LEN", JDBCType.INTEGER);
	}
	
	static <U extends AbstractFunction<String, U>> SimpleColumnFunction<String, U> lower(BindableColumn<String> column) {
		return SimpleColumnFunction.of(column, "LOWER");
	}
	
	static <U extends AbstractFunction<String, U>> SimpleColumnFunction<String, U> ltrim(BindableColumn<String> column) {
		return SimpleColumnFunction.of(column, "LTRIM");
	}
}
