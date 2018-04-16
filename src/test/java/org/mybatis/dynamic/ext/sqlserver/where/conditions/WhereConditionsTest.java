package org.mybatis.dynamic.ext.sqlserver.where.conditions;

import static org.mybatis.dynamic.sql.SqlBuilder.select;
import static org.mybatis.dynamic.ext.sql.SqlBuilder.equal;
import static org.mybatis.dynamic.ext.sql.SqlBuilder.equalConstant;
import static org.mybatis.dynamic.ext.sql.SqlBuilder.greater;
import static org.mybatis.dynamic.ext.sql.SqlBuilder.greaterConstant;
import static org.mybatis.dynamic.ext.sql.SqlBuilder.greaterOrEqual;
import static org.mybatis.dynamic.ext.sql.SqlBuilder.greaterOrEqualConstant;
import static org.mybatis.dynamic.ext.sql.SqlBuilder.less;
import static org.mybatis.dynamic.ext.sql.SqlBuilder.lessConstant;
import static org.mybatis.dynamic.ext.sql.SqlBuilder.lessOrEqual;
import static org.mybatis.dynamic.ext.sql.SqlBuilder.lessOrEqualConstant;
import static org.mybatis.dynamic.ext.sql.SqlBuilder.like;
import static org.mybatis.dynamic.ext.sql.SqlBuilder.likeConstant;
import static org.mybatis.dynamic.ext.sql.SqlBuilder.in;
import static org.mybatis.dynamic.ext.sql.SqlBuilder.inConstant;
import static org.mybatis.dynamic.ext.sql.SqlBuilder.notIn;
import static org.mybatis.dynamic.ext.sql.SqlBuilder.notInConstant;
import static org.mybatis.dynamic.ext.sql.SqlBuilder.notEqual;
import static org.mybatis.dynamic.ext.sql.SqlBuilder.notEqualConstant;
import static org.mybatis.dynamic.ext.sql.SqlBuilder.notLike;
import static org.mybatis.dynamic.ext.sql.SqlBuilder.notLikeConstant;

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
    public static final SqlColumn<Boolean> columnBoolean1 = table.column("columnBoolean1", JDBCType.BOOLEAN);
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
    	
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnInteger1, equalConstant(1)),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnInteger1 = 1");
    	
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnBoolean1, equalConstant(true)),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnBoolean1 = 1");
    	
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnBoolean1, equalConstant(false)),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnBoolean1 = 0");
    	
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnString1, equalConstant("test")),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnString1 = 'test'");
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
    	
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnInteger1, greaterConstant(1)),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnInteger1 > 1");
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
    	
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnInteger1, greaterOrEqualConstant(1)),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnInteger1 >= 1");
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
    	
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnInteger1, lessConstant(1)),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnInteger1 < 1");
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
    	
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnInteger1, lessOrEqualConstant(1)),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnInteger1 <= 1");
    }
    
    @Test
    public void testLike() {
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnString1, like("paramName1")),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnString1 like #{paramName1}");
    	
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnString1, like()),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnString1 like #{parameters.p1,jdbcType=VARCHAR}");
    	
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnString1, likeConstant("%test%")),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnString1 like '%test%'");
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
    	
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnInteger1, notEqualConstant(1)),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnInteger1 <> 1");
    }
    
    @Test
    public void testNotLike() {
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnString1, notLike("paramName1")),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnString1 NOT LIKE #{paramName1}");
    	
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnString1, notLike()),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnString1 NOT LIKE #{parameters.p1,jdbcType=VARCHAR}");
    	
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnString1, notLikeConstant("%test%")),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnString1 NOT LIKE '%test%'");
    }
    
    @Test
    public void testIn() {
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnString1, in("paramName1")),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnString1 IN (#{paramName1})");
    	
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnInteger1, inConstant("1, 2, 3", Integer.class)),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnInteger1 IN (1, 2, 3)");
    }
    
    @Test
    public void testNotIn() {
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnString1, notIn("paramName1")),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnString1 NOT IN (#{paramName1})");
    	
    	test(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnInteger1, notInConstant("1, 2, 3", Integer.class)),
                "select a.columnInteger1 as A_COLUMN1 from foo a where a.columnInteger1 NOT IN (1, 2, 3)");
    }
    
    @SuppressWarnings("rawtypes")
   	protected void test(QueryExpressionWhereBuilder selectModel, String sql) {
       	SelectStatementProvider selectStatement = ((SelectModel) selectModel.build()).render(RenderingStrategy.MYBATIS3);
       	SoftAssertions.assertSoftly(softly -> softly.assertThat(selectStatement.getSelectStatement()).isEqualTo(sql));
    }
}
