package org.mybatis.dynamic.ext.sql.delete.renderer;

import java.util.Objects;

import org.mybatis.dynamic.ext.sql.delete.DeleteFromModel;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.where.WhereModel;
import org.mybatis.dynamic.sql.where.render.WhereClauseProvider;

public class DeleteFromRenderer {
	private DeleteFromModel deleteModel;
	private RenderingStrategy renderingStrategy;

	private DeleteFromRenderer(Builder builder) {
		deleteModel = Objects.requireNonNull(builder.deleteModel);
		renderingStrategy = Objects.requireNonNull(builder.renderingStrategy);
	}

	public DeleteStatementProviderExt render() {
		return DeleteStatementProviderExt.withTableName(deleteModel.table().name()).withWhereClause(deleteModel.whereModel().map(this::renderWhereClause)).build();
	}

	private WhereClauseProvider renderWhereClause(WhereModel whereModel) {
		return whereModel.render(renderingStrategy);
	}

	public static Builder withDeleteModel(DeleteFromModel deleteModel) {
		return new Builder().withDeleteModel(deleteModel);
	}

	public static class Builder {
		private DeleteFromModel deleteModel;
		private RenderingStrategy renderingStrategy;

		public Builder withDeleteModel(DeleteFromModel deleteModel) {
			this.deleteModel = deleteModel;
			return this;
		}

		public Builder withRenderingStrategy(RenderingStrategy renderingStrategy) {
			this.renderingStrategy = renderingStrategy;
			return this;
		}

		public DeleteFromRenderer build() {
			return new DeleteFromRenderer(this);
		}
	}
}
