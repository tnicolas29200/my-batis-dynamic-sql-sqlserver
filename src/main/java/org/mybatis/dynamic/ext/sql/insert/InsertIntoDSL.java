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