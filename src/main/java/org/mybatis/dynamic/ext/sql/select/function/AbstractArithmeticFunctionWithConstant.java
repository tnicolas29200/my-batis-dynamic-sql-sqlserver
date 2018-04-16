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
package org.mybatis.dynamic.ext.sql.select.function;

import java.util.Objects;

import org.mybatis.dynamic.sql.BindableColumn;
import org.mybatis.dynamic.sql.render.TableAliasCalculator;
import org.mybatis.dynamic.sql.select.function.AbstractFunction;

public abstract class AbstractArithmeticFunctionWithConstant<T extends Number,
        U extends AbstractArithmeticFunctionWithConstant<T, U>>
        extends AbstractFunction<T, AbstractArithmeticFunctionWithConstant<T,U>> {
    
    protected T value;
    
    protected AbstractArithmeticFunctionWithConstant(BindableColumn<T> firstColumn, T value) {
        super(firstColumn);
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public String renderWithTableAlias(TableAliasCalculator tableAliasCalculator) {
    	return column.renderWithTableAlias(tableAliasCalculator) + padOperator() + value; 
    }
    
    private String padOperator() {
        return " " + operator() + " ";
    }

    protected abstract String operator();
}
