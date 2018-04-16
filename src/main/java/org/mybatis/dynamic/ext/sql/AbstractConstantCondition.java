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

import org.mybatis.dynamic.sql.AbstractSingleValueCondition;
import org.mybatis.dynamic.sql.ConditionVisitor;

public abstract class AbstractConstantCondition<T> extends AbstractSingleValueCondition<T> {
    
    protected T value;
    
    public AbstractConstantCondition(T value) {
    	super(() -> null);
        this.value = value;
    }
    
    @Override
	public String renderCondition(String columnName, String placeholder) {
    	String valueTmp = value.toString();
    	if (this.value instanceof Boolean) {
    		Boolean valueBoolean = (Boolean) value;
    		if (valueBoolean) {
    			valueTmp = "1";
    		} else {
    			valueTmp = "0";
    		}
    	} else if (this.value instanceof String) {
    		valueTmp = "'" + value + "'";
    	}
		return columnName + " " + getOperator() + " " + valueTmp;
	}
    
    @Override
    public <R> R accept(ConditionVisitor<T,R> visitor) {
        return visitor.visit(this);
    }
    
    public abstract String getOperator();
}
