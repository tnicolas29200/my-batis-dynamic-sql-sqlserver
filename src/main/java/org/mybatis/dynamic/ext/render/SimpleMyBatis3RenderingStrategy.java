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
package org.mybatis.dynamic.ext.render;

import org.mybatis.dynamic.sql.BindableColumn;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.render.RenderingStrategy;

public class SimpleMyBatis3RenderingStrategy extends RenderingStrategy {
	@Override
	public String getFormattedJdbcPlaceholder(BindableColumn<?> column, String prefix, String parameterName) {
		if (column.alias().isPresent()) {
			return "#{" + ((SqlColumn<?>) column).alias().get() + "}";
		}
		return "#{" + ((SqlColumn<?>) column).name() + "}";
	}
}
