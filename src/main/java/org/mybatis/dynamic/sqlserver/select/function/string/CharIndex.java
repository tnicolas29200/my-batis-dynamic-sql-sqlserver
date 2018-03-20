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
package org.mybatis.dynamic.sqlserver.select.function.string;

import java.sql.JDBCType;
import java.util.Optional;

import org.mybatis.dynamic.sql.BindableColumn;
import org.mybatis.dynamic.sql.render.TableAliasCalculator;
import org.mybatis.dynamic.sql.select.function.AbstractFunction;

public class CharIndex extends AbstractFunction<String, CharIndex> {
    
	private String substring;
	private Integer startPosition;
	
    private CharIndex(BindableColumn<String> column, String substring) {
        super(column);
        this.substring = substring;
    }
    
    private CharIndex(BindableColumn<String> column, String substring, Integer startPosition) {
        this(column, substring);
        this.startPosition = startPosition;
    }
    
    @Override
    public String renderWithTableAlias(TableAliasCalculator tableAliasCalculator) {
        return "CHARINDEX(" //$NON-NLS-1$
                + column.renderWithTableAlias(tableAliasCalculator)
                + ", '" + substring + "'"
                + (startPosition != null ? ", " + startPosition : "")
                + ")"; //$NON-NLS-1$
    }
    
    @Override
    protected CharIndex copy() {
        return new CharIndex(column, substring, startPosition);
    }
    
    public static CharIndex of(BindableColumn<String> column, String substring) {
        return new CharIndex(column, substring);
    }
    
    public static CharIndex of(BindableColumn<String> column, String substring, Integer startPosition) {
        return new CharIndex(column, substring, startPosition);
    }
    
    @Override
    public Optional<JDBCType> jdbcType() {
        return Optional.of(JDBCType.INTEGER);
    }
}
