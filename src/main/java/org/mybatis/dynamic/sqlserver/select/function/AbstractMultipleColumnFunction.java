/**
 *    Copyright 2016-2018 the original author or authors.
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
package org.mybatis.dynamic.sqlserver.select.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.BindableColumn;
import org.mybatis.dynamic.sql.render.TableAliasCalculator;
import org.mybatis.dynamic.sql.select.function.AbstractFunction;

public abstract class AbstractMultipleColumnFunction<T, U extends AbstractMultipleColumnFunction<T, U>> extends AbstractFunction<T, AbstractMultipleColumnFunction<T,U>> {
    
    protected BindableColumn<T> secondColumn;
    protected List<BindableColumn<T>> subsequentColumns = new ArrayList<>();
    
    protected AbstractMultipleColumnFunction(BindableColumn<T> firstColumn, BindableColumn<T> secondColumn, List<BindableColumn<T>> subsequentColumns) {
        super(firstColumn);
        this.secondColumn = Objects.requireNonNull(secondColumn);
        this.subsequentColumns.addAll(subsequentColumns);
    }

    @Override
    public String renderWithTableAlias(TableAliasCalculator tableAliasCalculator) {
        // note - the cast below is added for a type inference bug in the Java9 compiler.
        return Stream.of(Stream.of((BasicColumn) column), Stream.of(secondColumn), subsequentColumns.stream())
                .flatMap(Function.identity())
                .map(column -> column.renderWithTableAlias(tableAliasCalculator))
                .collect(Collectors.joining(padOperator(), getFunctionName() + "(", ")")); //$NON-NLS-1$ //$NON-NLS-2$
    }
    
    private String padOperator() {
        return ", "; //$NON-NLS-1$ //$NON-NLS-2$
    }
    
    protected abstract String getFunctionName();
}
