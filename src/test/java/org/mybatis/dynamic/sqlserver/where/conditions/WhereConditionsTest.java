package org.mybatis.dynamic.sqlserver.where.conditions;

import static org.mybatis.dynamic.sql.SqlBuilder.select;
import static org.mybatis.dynamic.sql.SqlBuilderExt.equal;
import static org.mybatis.dynamic.sql.SqlBuilderExt.greater;
import static org.mybatis.dynamic.sql.SqlBuilderExt.greaterOrEqual;
import static org.mybatis.dynamic.sql.SqlBuilderExt.less;
import static org.mybatis.dynamic.sql.SqlBuilderExt.lessOrEqual;
import static org.mybatis.dynamic.sql.SqlBuilderExt.like;
import static org.mybatis.dynamic.sql.SqlBuilderExt.notEqual;
import static org.mybatis.dynamic.sql.SqlBuilderExt.notLike;

import java.sql.JDBCType;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.SelectModel;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL.QueryExpressionWhereBuilder;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;

@RunWith(JUnitPlatform.class)
public class WhereConditionsTest {

	public static final SqlTable table = SqlTable.of("foo");
    public static final SqlColumn<Integer> columnInteger1 = table.column("columnInteger1", JDBCType.INTEGER);
    public static final SqlColumn<Integer> columnInteger2 = table.column("columnInteger2", JDBCType.INTEGER);
    public static final SqlColumn<Integer> columnInteger3 = table.column("columnInteger3", JDBCType.INTEGER);
    public static final SqlColumn<String> columnString1 = table.column("columnString1", JDBCType.VARCHAR);
    
    @Test
    public void testEqual() {
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnInteger1, equal("paramName1")),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnInteger1 = #{paramName1}");
    	
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnInteger1, equal()),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnInteger1 = #{parameters.p1,jdbcType=INTEGER}");
    	
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnInteger1, equal(select(columnInteger2).from(table, "b"))),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnInteger1 = (select b.columnInteger2 from foo b)");
    	
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnInteger1, equal(columnInteger2)),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnInteger1 = a.columnInteger2");
    }
    
    @Test
    public void testGreater() {
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnInteger1, greater("paramName1")),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnInteger1 > #{paramName1}");
    	
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnInteger1, greater()),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnInteger1 > #{parameters.p1,jdbcType=INTEGER}");
    	
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnInteger1, greater(select(columnInteger2).from(table, "b"))),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnInteger1 > (select b.columnInteger2 from foo b)");
    	
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnInteger1, greater(columnInteger2)),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnInteger1 > a.columnInteger2");
    }
    
    @Test
    public void testGreaterOrEqual() {
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnInteger1, greaterOrEqual("paramName1")),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnInteger1 >= #{paramName1}");
    	
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnInteger1, greaterOrEqual()),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnInteger1 >= #{parameters.p1,jdbcType=INTEGER}");
    	
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnInteger1, greaterOrEqual(select(columnInteger2).from(table, "b"))),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnInteger1 >= (select b.columnInteger2 from foo b)");
    	
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnInteger1, greaterOrEqual(columnInteger2)),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnInteger1 >= a.columnInteger2");
    }
    
    @Test
    public void testLess() {
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnInteger1, less("paramName1")),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnInteger1 < #{paramName1}");
    	
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnInteger1, less()),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnInteger1 < #{parameters.p1,jdbcType=INTEGER}");
    	
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnInteger1, less(select(columnInteger2).from(table, "b"))),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnInteger1 < (select b.columnInteger2 from foo b)");
    	
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnInteger1, less(columnInteger2)),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnInteger1 < a.columnInteger2");
    }
    
    @Test
    public void testLessOrEqual() {
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnInteger1, lessOrEqual("paramName1")),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnInteger1 <= #{paramName1}");
    	
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnInteger1, lessOrEqual()),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnInteger1 <= #{parameters.p1,jdbcType=INTEGER}");
    	
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnInteger1, lessOrEqual(select(columnInteger2).from(table, "b"))),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnInteger1 <= (select b.columnInteger2 from foo b)");
    	
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnInteger1, lessOrEqual(columnInteger2)),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnInteger1 <= a.columnInteger2");
    }
    
    @Test
    public void testLike() {
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnString1, like("paramName1")),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnString1 like #{paramName1}");
    	
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnString1, like()),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnString1 like #{parameters.p1,jdbcType=VARCHAR}");
    }
    
    @Test
    public void testNotEqual() {
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnInteger1, notEqual("paramName1")),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnInteger1 <> #{paramName1}");
    	
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnInteger1, notEqual()),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnInteger1 <> #{parameters.p1,jdbcType=INTEGER}");
    	
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnInteger1, notEqual(select(columnInteger2).from(table, "b"))),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnInteger1 <> (select b.columnInteger2 from foo b)");
    	
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnInteger1, notEqual(columnInteger2)),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnInteger1 <> a.columnInteger2");
    }
    
    @Test
    public void testNotLike() {
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnString1, notLike("paramName1")),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnString1 not like #{paramName1}");
    	
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnString1, notLike()),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnString1 not like #{parameters.p1,jdbcType=VARCHAR}");
    }
    
    @SuppressWarnings("rawtypes")
   	protected void test(QueryExpressionWhereBuilder selectModel, String sql) {
       	SelectStatementProvider selectStatement = ((SelectModel) selectModel.build()).render(RenderingStrategy.MYBATIS3);
       	SoftAssertions.assertSoftly(softly -> softly.assertThat(selectStatement.getSelectStatement()).isEqualTo(sql));
    }
}
