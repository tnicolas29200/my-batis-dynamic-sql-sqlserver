/**
 *    Copyright 2016-2017 the original author or authors.
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
package org.mybatis.dynamic.ext.sql;

import java.util.Objects;

import org.mybatis.dynamic.sql.AbstractSingleValueCondition;
import org.mybatis.dynamic.sql.ConditionVisitor;

public abstract class AbstractSingleParamCondition<T> extends AbstractSingleValueCondition<T> {
    
    private String paramName;
    
    public AbstractSingleParamCondition(String paramName) {
    	super(() -> null);
        this.paramName = Objects.requireNonNull(paramName);
    }
    
    public String getParamName() {
        return paramName;
    }
    
    @Override
	public String renderCondition(String columnName, String placeholder) {
		return columnName + " " + getOperator() + " #{" + getParamName() + "}";
	}
    
    @Override
    public <R> R accept(ConditionVisitor<T,R> visitor) {
        return visitor.visit(this);
    }
    
    public abstract String getOperator();
}
