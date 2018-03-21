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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.BindableColumn;
import org.mybatis.dynamic.sql.render.TableAliasCalculator;

public class MultipleColumnFunction<T> extends AbstractFunction<T> {
    
	private String functionName;
    protected BasicColumn secondColumn;
    protected List<BasicColumn> subsequentColumns = new ArrayList<>();
    private JDBCType jdbcType;
    
    protected MultipleColumnFunction(BindableColumn<T> firstColumn, BasicColumn secondColumn, List<BasicColumn> subsequentColumns, String functionName) {
        super(firstColumn);
        this.secondColumn = Objects.requireNonNull(secondColumn);
        this.subsequentColumns.addAll(subsequentColumns);
        this.functionName = functionName;
    }
    
    protected MultipleColumnFunction(BindableColumn<T> firstColumn, BasicColumn secondColumn, List<BasicColumn> subsequentColumns, String functionName, JDBCType jdbcType) {
        this(firstColumn, secondColumn, subsequentColumns, functionName);
        this.jdbcType = jdbcType;
    }

    @Override
    public String renderWithTableAlias(TableAliasCalculator tableAliasCalculator) {
        return Stream.of(Stream.of((BasicColumn) column), Stream.of(secondColumn), subsequentColumns.stream())
                .flatMap(Function.identity())
                .map(column -> column.renderWithTableAlias(tableAliasCalculator))
                .collect(Collectors.joining(padOperator(), functionName + "(", ")"));
    }
    
    public static <T> MultipleColumnFunction<T> of(BindableColumn<T> column, BasicColumn secondColumn, List<BasicColumn> subsequentColumns, String functionName) {
        return new MultipleColumnFunction<>(column, secondColumn, subsequentColumns, functionName);
    }

	public static <T> MultipleColumnFunction<T> of(BindableColumn<T> column, BasicColumn secondColumn, List<BasicColumn> subsequentColumns, String functionName, JDBCType jdbcType) {
        return new MultipleColumnFunction<>(column, secondColumn, subsequentColumns, functionName, jdbcType);
    }
    
    private String padOperator() {
        return ", ";
    }
    
    @Override
    public Optional<JDBCType> jdbcType() {
		if (this.jdbcType != null) {
			return Optional.of(this.jdbcType);
		}
		return column.jdbcType();
    }

	@Override
	protected MultipleColumnFunction<T> copy() {
		return new MultipleColumnFunction<>(column, secondColumn, subsequentColumns, functionName, jdbcType);
	}
}
