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

import java.util.List;

import org.mybatis.dynamic.sql.BindableColumn;
import org.mybatis.dynamic.sqlserver.select.function.AbstractMultipleColumnFunction;

public class Concat extends AbstractMultipleColumnFunction<String, Concat> {
    
    private Concat(BindableColumn<String> firstColumn, BindableColumn<String> secondColumn, List<BindableColumn<String>> subsequentColumns) {
        super(firstColumn, secondColumn, subsequentColumns);
    }

    @Override
    protected Concat copy() {
        return new Concat(column, secondColumn, subsequentColumns);
    }

    public static Concat of(BindableColumn<String> firstColumn, BindableColumn<String> secondColumn, List<BindableColumn<String>> subsequentColumns) {
        return new Concat(firstColumn, secondColumn, subsequentColumns);
    }

	@Override
	protected String getFunctionName() {
		return "CONCAT";
	}
}
