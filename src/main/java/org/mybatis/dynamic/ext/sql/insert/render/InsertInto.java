package org.mybatis.dynamic.ext.sql.insert.render;

import static org.mybatis.dynamic.sql.util.StringUtilities.spaceBefore;

import java.util.Objects;

public class InsertInto {
	private String tableName;
	private String columnsPhrase;
	private String valuesPhrase;

	private InsertInto(Builder builder) {
		tableName = Objects.requireNonNull(builder.tableName);
		columnsPhrase = Objects.requireNonNull(builder.columnsPhrase);
		valuesPhrase = Objects.requireNonNull(builder.valuesPhrase);
	}

	public static Builder with() {
		return new Builder();
	}

	/**
	 * Returns the generated SQL for this batch. This is useful for Spring JDBC batch support.
	 * 
	 * @return the generated INSERT statement
	 */
	public String getInsertStatementSQL() {
		return "insert into" + spaceBefore(tableName) + spaceBefore(columnsPhrase) + spaceBefore(valuesPhrase);
	}

	public static class Builder {
		private String tableName;
		private String columnsPhrase;
		private String valuesPhrase;

		public Builder withTableName(String tableName) {
			this.tableName = tableName;
			return this;
		}

		public Builder withColumnsPhrase(String columnsPhrase) {
			this.columnsPhrase = columnsPhrase;
			return this;
		}

		public Builder withValuesPhrase(String valuesPhrase) {
			this.valuesPhrase = valuesPhrase;
			return this;
		}

		public InsertInto build() {
			return new InsertInto(this);
		}
	}
}
