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

import org.mybatis.dynamic.sql.BindableColumn;

public class DivideWithParameter<T extends Number> extends AbstractArithmeticFunctionWithParameter<T, DivideWithParameter<T>> {
    
    DivideWithParameter(BindableColumn<T> firstColumn, String paramName) {
        super(firstColumn, paramName);
    }

    @Override
    protected DivideWithParameter<T> copy() {
        return new DivideWithParameter<>(column, paramName);
    }

    @Override
    protected String operator() {
        return "/";
    }

    public static <T extends Number> DivideWithParameter<T> of(BindableColumn<T> firstColumn, String paramName) {
        return new DivideWithParameter<>(firstColumn, paramName);
    }
}
