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
