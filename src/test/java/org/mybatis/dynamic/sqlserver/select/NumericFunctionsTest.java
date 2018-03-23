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
package org.mybatis.dynamic.sqlserver.select;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.select;
import static org.mybatis.dynamic.sqlserver.SQLServerBuilder.abs;
import static org.mybatis.dynamic.sqlserver.SQLServerBuilder.avg;
import static org.mybatis.dynamic.sqlserver.SQLServerBuilder.ceiling;
import static org.mybatis.dynamic.sqlserver.SQLServerBuilder.count;
import static org.mybatis.dynamic.sqlserver.SQLServerBuilder.floor;
import static org.mybatis.dynamic.sqlserver.SQLServerBuilder.max;
import static org.mybatis.dynamic.sqlserver.SQLServerBuilder.min;
import static org.mybatis.dynamic.sqlserver.SQLServerBuilder.rand;
import static org.mybatis.dynamic.sqlserver.SQLServerBuilder.round;
import static org.mybatis.dynamic.sqlserver.SQLServerBuilder.sign;
import static org.mybatis.dynamic.sqlserver.SQLServerBuilder.sum;

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
public class NumericFunctionsTest {

	public static final SqlTable table = SqlTable.of("foo");
    public static final SqlColumn<Integer> column1 = table.column("column1", JDBCType.INTEGER);
    public static final SqlColumn<Integer> column2 = table.column("column2", JDBCType.INTEGER);
    public static final SqlColumn<Integer> column3 = table.column("column3", JDBCType.INTEGER);
    public static final SqlColumn<Double> column4 = table.column("column4", JDBCType.DOUBLE);
    
    @Test
    public void testAbs() {
        test(select(abs(column1).as("A_COLUMN1")).from(table, "a").where(abs(column1), isEqualTo(1)),
        	"select ABS(a.column1) as A_COLUMN1 from foo a where ABS(a.column1) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(abs(1).as("A_COLUMN1")).from(table, "a").where(abs(2), isEqualTo(1)),
        	"select ABS(1) as A_COLUMN1 from foo a where ABS(2) = #{parameters.p1,jdbcType=INTEGER}");
    }
    
    @Test
    public void testAvg() {
        test(select(avg(column1).as("A_COLUMN1")).from(table, "a").where(avg(column1), isEqualTo(1)),
        	"select AVG(a.column1) as A_COLUMN1 from foo a where AVG(a.column1) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(avg(1).as("A_COLUMN1")).from(table, "a").where(avg(2), isEqualTo(1)),
        	"select AVG(1) as A_COLUMN1 from foo a where AVG(2) = #{parameters.p1,jdbcType=INTEGER}");
    }
    
    @Test
    public void testCeiling() {
        test(select(ceiling(column4).as("A_COLUMN4")).from(table, "a").where(ceiling(column4), isEqualTo(1)),
        	"select CEILING(a.column4) as A_COLUMN4 from foo a where CEILING(a.column4) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(ceiling(1.2).as("A_COLUMN1")).from(table, "a").where(ceiling(2.3), isEqualTo(1)),
        	"select CEILING(1.2) as A_COLUMN1 from foo a where CEILING(2.3) = #{parameters.p1,jdbcType=INTEGER}");
    }
    
    @Test
    public void testCount() {
        test(select(count(column4).as("A_COLUMN4")).from(table, "a").where(count(column4), isEqualTo(1)),
        	"select COUNT(a.column4) as A_COLUMN4 from foo a where COUNT(a.column4) = #{parameters.p1,jdbcType=INTEGER}");
    }
    
    @Test
    public void testFloor() {
        test(select(floor(column4).as("A_COLUMN4")).from(table, "a").where(floor(column4), isEqualTo(1)),
        	"select FLOOR(a.column4) as A_COLUMN4 from foo a where FLOOR(a.column4) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(floor(1.2).as("A_COLUMN1")).from(table, "a").where(floor(2.3), isEqualTo(1)),
        	"select FLOOR(1.2) as A_COLUMN1 from foo a where FLOOR(2.3) = #{parameters.p1,jdbcType=INTEGER}");
    }
    
    @Test
    public void testMax() {
        test(select(max(column1).as("A_COLUMN1")).from(table, "a").where(max(column1), isEqualTo(1)),
        	"select MAX(a.column1) as A_COLUMN1 from foo a where MAX(a.column1) = #{parameters.p1,jdbcType=INTEGER}");
    }
    
    @Test
    public void testMin() {
        test(select(min(column1).as("A_COLUMN1")).from(table, "a").where(min(column1), isEqualTo(1)),
        	"select MIN(a.column1) as A_COLUMN1 from foo a where MIN(a.column1) = #{parameters.p1,jdbcType=INTEGER}");
    }

    @Test
    public void testRand() {
        test(select(rand(column1).as("A_COLUMN1")).from(table, "a").where(rand(column1), isEqualTo(1)),
        	"select RAND(a.column1) as A_COLUMN1 from foo a where RAND(a.column1) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(rand(1).as("A_COLUMN1")).from(table, "a").where(rand(2), isEqualTo(1)),
        	"select RAND(1) as A_COLUMN1 from foo a where RAND(2) = #{parameters.p1,jdbcType=INTEGER}");
    }
    
    @Test
    public void testRound() {
        test(select(round(column4, column1).as("A_COLUMN1")).from(table, "a").where(round(column4, column1), isEqualTo(1)),
        	"select ROUND(a.column4, a.column1) as A_COLUMN1 from foo a where ROUND(a.column4, a.column1) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(round(column4, 1).as("A_COLUMN1")).from(table, "a").where(round(column4, 2), isEqualTo(1)),
            	"select ROUND(a.column4, 1) as A_COLUMN1 from foo a where ROUND(a.column4, 2) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(round(2.0, column1).as("A_COLUMN1")).from(table, "a").where(round(2.0, column1), isEqualTo(1)),
            	"select ROUND(2.0, a.column1) as A_COLUMN1 from foo a where ROUND(2.0, a.column1) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(round(1.0, 2).as("A_COLUMN1")).from(table, "a").where(round(2.0, 3), isEqualTo(1)),
            	"select ROUND(1.0, 2) as A_COLUMN1 from foo a where ROUND(2.0, 3) = #{parameters.p1,jdbcType=INTEGER}");
    
        test(select(round(column4, column1, column2).as("A_COLUMN1")).from(table, "a").where(round(column4, column1, column2), isEqualTo(1)),
            	"select ROUND(a.column4, a.column1, a.column2) as A_COLUMN1 from foo a where ROUND(a.column4, a.column1, a.column2) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(round(column4, column1, 1).as("A_COLUMN1")).from(table, "a").where(round(column4, column1, 2), isEqualTo(1)),
            	"select ROUND(a.column4, a.column1, 1) as A_COLUMN1 from foo a where ROUND(a.column4, a.column1, 2) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(round(column4, 1, column2).as("A_COLUMN1")).from(table, "a").where(round(column4, 2, column2), isEqualTo(1)),
            	"select ROUND(a.column4, 1, a.column2) as A_COLUMN1 from foo a where ROUND(a.column4, 2, a.column2) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(round(column4, 1, 2).as("A_COLUMN1")).from(table, "a").where(round(column4, 2, 3), isEqualTo(1)),
            	"select ROUND(a.column4, 1, 2) as A_COLUMN1 from foo a where ROUND(a.column4, 2, 3) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(round(1.0, column1, column2).as("A_COLUMN1")).from(table, "a").where(round(2.0, column1, column2), isEqualTo(1)),
            	"select ROUND(1.0, a.column1, a.column2) as A_COLUMN1 from foo a where ROUND(2.0, a.column1, a.column2) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(round(1.0, column1, 3).as("A_COLUMN1")).from(table, "a").where(round(2.0, column1, 4), isEqualTo(1)),
            	"select ROUND(1.0, a.column1, 3) as A_COLUMN1 from foo a where ROUND(2.0, a.column1, 4) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(round(1.0, 3, column2).as("A_COLUMN1")).from(table, "a").where(round(2.0, 3, column2), isEqualTo(1)),
            	"select ROUND(1.0, 3, a.column2) as A_COLUMN1 from foo a where ROUND(2.0, 3, a.column2) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(round(1.0, 2, 3).as("A_COLUMN1")).from(table, "a").where(round(2.0, 3, 4), isEqualTo(1)),
            	"select ROUND(1.0, 2, 3) as A_COLUMN1 from foo a where ROUND(2.0, 3, 4) = #{parameters.p1,jdbcType=INTEGER}");
    }
    
    @Test
    public void testSign() {
        test(select(sign(column1).as("A_COLUMN1")).from(table, "a").where(sign(column1), isEqualTo(1)),
        	"select SIGN(a.column1) as A_COLUMN1 from foo a where SIGN(a.column1) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(sign(1).as("A_COLUMN1")).from(table, "a").where(sign(2), isEqualTo(1)),
        	"select SIGN(1) as A_COLUMN1 from foo a where SIGN(2) = #{parameters.p1,jdbcType=INTEGER}");
    }
    
    @Test
    public void testSum() {
        test(select(sum(column1).as("A_COLUMN1")).from(table, "a").where(sum(column1), isEqualTo(1)),
        	"select SUM(a.column1) as A_COLUMN1 from foo a where SUM(a.column1) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(sum(1).as("A_COLUMN1")).from(table, "a").where(sum(2), isEqualTo(1)),
        	"select SUM(1) as A_COLUMN1 from foo a where SUM(2) = #{parameters.p1,jdbcType=INTEGER}");
    }
    
    @SuppressWarnings("rawtypes")
   	protected void test(QueryExpressionWhereBuilder selectModel, String sql) {
       	SelectStatementProvider selectStatement = ((SelectModel) selectModel.build()).render(RenderingStrategy.MYBATIS3);
       	SoftAssertions.assertSoftly(softly -> softly.assertThat(selectStatement.getSelectStatement()).isEqualTo(sql));
    }
}
