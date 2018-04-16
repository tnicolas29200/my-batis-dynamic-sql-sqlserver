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
