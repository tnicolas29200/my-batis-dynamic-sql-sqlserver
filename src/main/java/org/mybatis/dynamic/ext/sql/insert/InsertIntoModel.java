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
package org.mybatis.dynamic.ext.sql.insert;

import java.util.Objects;
import java.util.Optional;

import org.mybatis.dynamic.ext.sql.insert.render.InsertInto;
import org.mybatis.dynamic.ext.sql.insert.render.InsertIntoRenderer;
import org.mybatis.dynamic.sql.SqlTable;
import org.mybatis.dynamic.sql.insert.InsertColumnListModel;
import org.mybatis.dynamic.sql.render.RenderingStrategy;

public class InsertIntoModel {
	private SqlTable table;
	private Optional<InsertColumnListModel> columnList;

	private InsertIntoModel(Builder builder) {
		table = Objects.requireNonNull(builder.table);
		columnList = Objects.requireNonNull(builder.columnList);
	}

	public SqlTable table() {
		return table;
	}

	public Optional<InsertColumnListModel> columnList() {
		return columnList;
	}

	public InsertInto render(RenderingStrategy renderingStrategy) {
		return InsertIntoRenderer.withInsertIntoModel(this).withRenderingStrategy(renderingStrategy).build().render();
	}

	public static Builder withTable(SqlTable table) {
		return new Builder().withTable(table);
	}

	public static class Builder {
		private SqlTable table;
		private Optional<InsertColumnListModel> columnList = Optional.empty();

		public Builder withTable(SqlTable table) {
			this.table = table;
			return this;
		}

		public Builder withColumnList(Optional<InsertColumnListModel> columnList) {
			this.columnList = columnList;
			return this;
		}

		public InsertIntoModel build() {
			return new InsertIntoModel(this);
		}
	}
}
