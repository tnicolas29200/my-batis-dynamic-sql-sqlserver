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
