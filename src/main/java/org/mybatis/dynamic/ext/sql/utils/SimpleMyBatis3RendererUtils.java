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
