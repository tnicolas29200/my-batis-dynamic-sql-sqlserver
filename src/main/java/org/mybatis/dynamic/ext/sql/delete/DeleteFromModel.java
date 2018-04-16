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
package org.mybatis.dynamic.ext.sql.delete;

import java.util.Objects;
import java.util.Optional;

import org.mybatis.dynamic.sql.SqlTable;
import org.mybatis.dynamic.ext.sql.delete.renderer.DeleteFromRenderer;
import org.mybatis.dynamic.ext.sql.delete.renderer.DeleteStatementProviderExt;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.where.WhereModel;

public class DeleteFromModel {
	private SqlTable table;
	private Optional<WhereModel> whereModel;

	private DeleteFromModel(Builder builder) {
		table = Objects.requireNonNull(builder.table);
		whereModel = Optional.ofNullable(builder.whereModel);
	}

	public SqlTable table() {
		return table;
	}

	public Optional<WhereModel> whereModel() {
		return whereModel;
	}

	public DeleteStatementProviderExt render(RenderingStrategy renderingStrategy) {
		return DeleteFromRenderer.withDeleteModel(this).withRenderingStrategy(renderingStrategy).build().render();
	}

	public static Builder withTable(SqlTable table) {
		return new Builder().withTable(table);
	}

	public static class Builder {
		private SqlTable table;
		private WhereModel whereModel;

		public Builder withTable(SqlTable table) {
			this.table = table;
			return this;
		}

		public Builder withColumns(WhereModel whereModel) {
			this.whereModel = whereModel;
			return this;
		}

		public DeleteFromModel build() {
			return new DeleteFromModel(this);
		}
	}
}
