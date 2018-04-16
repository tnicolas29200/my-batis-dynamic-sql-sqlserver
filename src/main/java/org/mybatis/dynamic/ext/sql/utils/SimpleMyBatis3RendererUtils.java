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
package org.mybatis.dynamic.ext.sql.utils;

import org.mybatis.dynamic.ext.render.SimpleMyBatis3RenderingStrategy;
import org.mybatis.dynamic.ext.sql.delete.DeleteFromDSL;
import org.mybatis.dynamic.ext.sql.delete.DeleteFromModel;
import org.mybatis.dynamic.ext.sql.insert.InsertIntoDSL;
import org.mybatis.dynamic.sql.select.SelectDSL;
import org.mybatis.dynamic.sql.select.SelectModel;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.util.Buildable;

public interface SimpleMyBatis3RendererUtils {
	public static String select(Buildable<SelectModel> query) {
		return query.build().render(new SimpleMyBatis3RenderingStrategy()).getSelectStatement();
	}

	public static String select(SelectDSL<SelectModel> query) {
		return query.build().render(new SimpleMyBatis3RenderingStrategy()).getSelectStatement();
	}

	public static String update(UpdateDSL<UpdateModel> query) {
		return query.build().render(new SimpleMyBatis3RenderingStrategy()).getUpdateStatement();
	}

	public static String update(UpdateDSL<UpdateModel>.UpdateWhereBuilder query) {
		return query.build().render(new SimpleMyBatis3RenderingStrategy()).getUpdateStatement();
	}

	public static String insert(InsertIntoDSL query) {
		return query.build().render(new SimpleMyBatis3RenderingStrategy()).getInsertStatementSQL();
	}

	public static String delete(DeleteFromDSL<DeleteFromModel>.DeleteWhereBuilder query) {
		return query.build().render(new SimpleMyBatis3RenderingStrategy()).getDeleteStatement();
	}

	public static String delete(DeleteFromDSL<DeleteFromModel> query) {
		return query.build().render(new SimpleMyBatis3RenderingStrategy()).getDeleteStatement();
	}
}
