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
package org.mybatis.dynamic.ext.sql.insert.render;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.mybatis.dynamic.ext.sql.insert.InsertIntoModel;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.insert.InsertColumnListModel;
import org.mybatis.dynamic.sql.render.RenderingStrategy;

public class InsertIntoRenderer {

	private InsertIntoModel model;
	private RenderingStrategy renderingStrategy;

	private InsertIntoRenderer(Builder builder) {
		model = Objects.requireNonNull(builder.model);
		renderingStrategy = Objects.requireNonNull(builder.renderingStrategy);
	}

	private Optional<String> calculateColumnsPhrase() {
		return model.columnList().map(this::calculateColumnsPhrase);
	}

	private Optional<String> calculateColumnsValues() {
		return model.columnList().map(this::calculateValuesPhrase);
	}

	public InsertInto render() {
		return InsertInto.with().withTableName(model.table().name()).withColumnsPhrase(calculateColumnsPhrase().get()).withValuesPhrase(" VALUES " + calculateColumnsValues().get()).build();
	}

	private String calculateColumnsPhrase(InsertColumnListModel columnList) {
		return columnList.mapColumns(SqlColumn::name).collect(Collectors.joining(", ", "(", ")"));
	}

	private String calculateValuesPhrase(InsertColumnListModel columnList) {
		List<String> list = columnList.mapColumns(SqlColumn::name).collect(Collectors.toList());
		StringBuilder builder = new StringBuilder();
		for (String column : list) {
			if (builder.length() > 0) {
				builder.append(", ");
			} else {
				builder.append("(");
			}
			builder.append("#{" + column + "}");
		}
		if (builder.length() > 0) {
			builder.append(")");
		}
		return builder.toString();
	}

	public static Builder withInsertIntoModel(InsertIntoModel model) {
		return new Builder().withInsertIntoModel(model);
	}

	public static class Builder {
		private InsertIntoModel model;
		private RenderingStrategy renderingStrategy;

		public Builder withInsertIntoModel(InsertIntoModel model) {
			this.model = model;
			return this;
		}

		public Builder withRenderingStrategy(RenderingStrategy renderingStrategy) {
			this.renderingStrategy = renderingStrategy;
			return this;
		}

		public InsertIntoRenderer build() {
			return new InsertIntoRenderer(this);
		}
	}
}
