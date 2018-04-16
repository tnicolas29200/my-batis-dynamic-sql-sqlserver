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
package org.mybatis.dynamic.ext.sql.insert;

import java.util.Arrays;
import java.util.Optional;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;
import org.mybatis.dynamic.sql.insert.InsertColumnListModel;

public class InsertIntoDSL {

	private SqlTable table;
	private Optional<InsertColumnListModel> columnList;

	private InsertIntoDSL(SqlTable table, InsertColumnListModel columnList) {
		this.table = table;
		this.columnList = Optional.of(columnList);
	}

	public InsertIntoModel build() {
		return InsertIntoModel.withTable(table).withColumnList(columnList).build();
	}

	public static InsertColumnGatherer insertInto(SqlTable table) {
		return new InsertColumnGatherer(table);
	}

	public static class InsertColumnGatherer {
		private SqlTable table;

		private InsertColumnGatherer(SqlTable table) {
			this.table = table;
		}

		public InsertIntoDSL values(SqlColumn<?>... columns) {
			return new InsertIntoDSL(table, InsertColumnListModel.of(Arrays.asList(columns)));
		}
	}
}