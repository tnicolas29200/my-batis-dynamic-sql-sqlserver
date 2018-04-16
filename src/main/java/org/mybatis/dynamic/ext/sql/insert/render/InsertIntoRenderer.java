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
