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
package org.mybatis.dynamic.ext.sql.delete;

import java.util.Objects;
import java.util.function.Function;

import org.mybatis.dynamic.sql.BindableColumn;
import org.mybatis.dynamic.sql.SqlCriterion;
import org.mybatis.dynamic.sql.SqlTable;
import org.mybatis.dynamic.sql.VisitableCondition;
import org.mybatis.dynamic.sql.where.AbstractWhereDSL;

public class DeleteFromDSL<R> {

	private Function<DeleteFromModel, R> adapterFunction;
	private SqlTable table;

	private DeleteFromDSL(SqlTable table, Function<DeleteFromModel, R> adapterFunction) {
		this.table = Objects.requireNonNull(table);
		this.adapterFunction = Objects.requireNonNull(adapterFunction);
	}

	public R build() {
		DeleteFromModel deleteModel = DeleteFromModel.withTable(table).build();
		return adapterFunction.apply(deleteModel);
	}

	public static <R> DeleteFromDSL<R> deleteFrom(Function<DeleteFromModel, R> adapterFunction, SqlTable table) {
		return new DeleteFromDSL<>(table, adapterFunction);
	}

	public static DeleteFromDSL<DeleteFromModel> deleteFrom(SqlTable table) {
		return deleteFrom(Function.identity(), table);
	}

	public <T> DeleteWhereBuilder where(BindableColumn<T> column, VisitableCondition<T> condition) {
		return new DeleteWhereBuilder(column, condition);
	}

	public <T> DeleteWhereBuilder where(BindableColumn<T> column, VisitableCondition<T> condition, SqlCriterion<?>... subCriteria) {
		return new DeleteWhereBuilder(column, condition, subCriteria);
	}

	public class DeleteWhereBuilder extends AbstractWhereDSL<DeleteWhereBuilder> {
		public <T> DeleteWhereBuilder(BindableColumn<T> column, VisitableCondition<T> condition) {
			super(column, condition);
		}

		public <T> DeleteWhereBuilder(BindableColumn<T> column, VisitableCondition<T> condition, SqlCriterion<?>... subCriteria) {
			super(column, condition, subCriteria);
		}

		public R build() {
			DeleteFromModel deleteModel = DeleteFromModel.withTable(table).withColumns(buildWhereModel()).build();
			return adapterFunction.apply(deleteModel);
		}

		@Override
		protected DeleteWhereBuilder getThis() {
			return this;
		}
	}
}
