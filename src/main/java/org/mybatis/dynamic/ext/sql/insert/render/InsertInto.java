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
