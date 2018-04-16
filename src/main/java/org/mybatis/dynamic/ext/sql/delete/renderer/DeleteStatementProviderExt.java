package org.mybatis.dynamic.ext.sql.delete.renderer;

import static org.mybatis.dynamic.sql.util.StringUtilities.spaceBefore;

import java.util.Objects;
import java.util.Optional;

import org.mybatis.dynamic.sql.where.render.WhereClauseProvider;

public class DeleteStatementProviderExt {
	private String tableName;
	private Optional<WhereClauseProvider> whereClauseProvider;

	private DeleteStatementProviderExt(Builder builder) {
		tableName = Objects.requireNonNull(builder.tableName);
		whereClauseProvider = Objects.requireNonNull(builder.whereClauseProvider);
	}

	public String getDeleteStatement() {
		return "delete from" //$NON-NLS-1$
				+ spaceBefore(tableName) + spaceBefore(whereClauseProvider.map(WhereClauseProvider::getWhereClause));
	}

	public static Builder withTableName(String tableName) {
		return new Builder().withTableName(tableName);
	}

	public static class Builder {
		private String tableName;
		private Optional<WhereClauseProvider> whereClauseProvider = Optional.empty();

		public Builder withTableName(String tableName) {
			this.tableName = tableName;
			return this;
		}

		public Builder withWhereClause(Optional<WhereClauseProvider> whereClauseProvider) {
			this.whereClauseProvider = whereClauseProvider;
			return this;
		}

		public DeleteStatementProviderExt build() {
			return new DeleteStatementProviderExt(this);
		}
	}
}
