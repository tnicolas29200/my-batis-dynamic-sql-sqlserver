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
package org.mybatis.dynamic.sql.select.function.abs;

import java.sql.JDBCType;
import java.util.Objects;
import java.util.Optional;

import org.mybatis.dynamic.sql.BindableColumn;

public abstract class AbstractValueFunction<T> implements BindableColumn<T> {
    protected T value;
    protected String alias;
    protected JDBCType jdbcType;

    protected AbstractValueFunction(T value) {
        this.value = Objects.requireNonNull(value);
    }
    
    protected AbstractValueFunction(T value, JDBCType jdbcType) {
        this(value);
        this.jdbcType = jdbcType;
    }
    
    @Override
    public Optional<String> alias() {
        return Optional.ofNullable(alias);
    }

    @Override
    public AbstractValueFunction<T> as(String alias) {
    	AbstractValueFunction<T> newThing = copy();
        newThing.alias = alias;
        return newThing;
    }

    @Override
    public Optional<String> typeHandler() {
        return Optional.empty();
    }
    
    @Override
    public Optional<JDBCType> jdbcType() {
    	if (jdbcType != null) {
    		return Optional.of(jdbcType);	
    	}
        return Optional.empty();
    }
    
    protected abstract AbstractValueFunction<T> copy();
}
