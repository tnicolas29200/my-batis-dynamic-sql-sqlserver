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
package org.mybatis.dynamic.ext.sqlserver.select;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.select;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.abs;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.avg;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.ceiling;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.count;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.floor;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.max;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.min;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.rand;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.round;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.sign;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.sum;

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
    public static final SqlColumn<Integer> columnInteger1 = table.column("columnInteger1", JDBCType.INTEGER);
    public static final SqlColumn<Integer> columnInteger2 = table.column("columnInteger2", JDBCType.INTEGER);
    public static final SqlColumn<Integer> columnInteger3 = table.column("columnInteger3", JDBCType.INTEGER);
    public static final SqlColumn<Double> columnDouble1 = table.column("columnDouble1", JDBCType.DOUBLE);
    
    @Test
    public void testAbs() {
        test(select(abs(columnInteger1).as("A_COLUMN1")).from(table, "a").where(abs(columnInteger1), isEqualTo(1)),
        	"select ABS(a.columnInteger1) as A_COLUMN1 from foo a where ABS(a.columnInteger1) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(abs(1).as("A_COLUMN1")).from(table, "a").where(abs(2), isEqualTo(1)),
        	"select ABS(1) as A_COLUMN1 from foo a where ABS(2) = #{parameters.p1,jdbcType=INTEGER}");
    }
    
    @Test
    public void testAvg() {
        test(select(avg(columnInteger1).as("A_COLUMN1")).from(table, "a").where(avg(columnInteger1), isEqualTo(1)),
        	"select AVG(a.columnInteger1) as A_COLUMN1 from foo a where AVG(a.columnInteger1) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(avg(1).as("A_COLUMN1")).from(table, "a").where(avg(2), isEqualTo(1)),
        	"select AVG(1) as A_COLUMN1 from foo a where AVG(2) = #{parameters.p1,jdbcType=INTEGER}");
    }
    
    @Test
    public void testCeiling() {
        test(select(ceiling(columnDouble1).as("A_COLUMN4")).from(table, "a").where(ceiling(columnDouble1), isEqualTo(1)),
        	"select CEILING(a.columnDouble1) as A_COLUMN4 from foo a where CEILING(a.columnDouble1) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(ceiling(1.2).as("A_COLUMN1")).from(table, "a").where(ceiling(2.3), isEqualTo(1)),
        	"select CEILING(1.2) as A_COLUMN1 from foo a where CEILING(2.3) = #{parameters.p1,jdbcType=INTEGER}");
    }
    
    @Test
    public void testCount() {
        test(select(count(columnDouble1).as("A_COLUMN4")).from(table, "a").where(count(columnDouble1), isEqualTo(1)),
        	"select COUNT(a.columnDouble1) as A_COLUMN4 from foo a where COUNT(a.columnDouble1) = #{parameters.p1,jdbcType=INTEGER}");
    }
    
    @Test
    public void testFloor() {
        test(select(floor(columnDouble1).as("A_COLUMN4")).from(table, "a").where(floor(columnDouble1), isEqualTo(1)),
        	"select FLOOR(a.columnDouble1) as A_COLUMN4 from foo a where FLOOR(a.columnDouble1) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(floor(1.2).as("A_COLUMN1")).from(table, "a").where(floor(2.3), isEqualTo(1)),
        	"select FLOOR(1.2) as A_COLUMN1 from foo a where FLOOR(2.3) = #{parameters.p1,jdbcType=INTEGER}");
    }
    
    @Test
    public void testMax() {
        test(select(max(columnInteger1).as("A_COLUMN1")).from(table, "a").where(max(columnInteger1), isEqualTo(1)),
        	"select MAX(a.columnInteger1) as A_COLUMN1 from foo a where MAX(a.columnInteger1) = #{parameters.p1,jdbcType=INTEGER}");
    }
    
    @Test
    public void testMin() {
        test(select(min(columnInteger1).as("A_COLUMN1")).from(table, "a").where(min(columnInteger1), isEqualTo(1)),
        	"select MIN(a.columnInteger1) as A_COLUMN1 from foo a where MIN(a.columnInteger1) = #{parameters.p1,jdbcType=INTEGER}");
    }

    @Test
    public void testRand() {
    	test(select(rand().as("A_COLUMN1")).from(table, "a").where(rand(), isEqualTo(1)),
            	"select RAND() as A_COLUMN1 from foo a where RAND() = #{parameters.p1,jdbcType=INTEGER}");
    	
    	test(select(rand(columnInteger1).as("A_COLUMN1")).from(table, "a").where(rand(columnInteger1), isEqualTo(1)),
        	"select RAND(a.columnInteger1) as A_COLUMN1 from foo a where RAND(a.columnInteger1) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(rand(1).as("A_COLUMN1")).from(table, "a").where(rand(2), isEqualTo(1)),
        	"select RAND(1) as A_COLUMN1 from foo a where RAND(2) = #{parameters.p1,jdbcType=INTEGER}");
    }
    
    @Test
    public void testRound() {
        test(select(round(columnDouble1, columnInteger1).as("A_COLUMN1")).from(table, "a").where(round(columnDouble1, columnInteger1), isEqualTo(1)),
        	"select ROUND(a.columnDouble1, a.columnInteger1) as A_COLUMN1 from foo a where ROUND(a.columnDouble1, a.columnInteger1) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(round(columnDouble1, 1).as("A_COLUMN1")).from(table, "a").where(round(columnDouble1, 2), isEqualTo(1)),
            	"select ROUND(a.columnDouble1, 1) as A_COLUMN1 from foo a where ROUND(a.columnDouble1, 2) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(round(2.0, columnInteger1).as("A_COLUMN1")).from(table, "a").where(round(2.0, columnInteger1), isEqualTo(1)),
            	"select ROUND(2.0, a.columnInteger1) as A_COLUMN1 from foo a where ROUND(2.0, a.columnInteger1) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(round(1.0, 2).as("A_COLUMN1")).from(table, "a").where(round(2.0, 3), isEqualTo(1)),
            	"select ROUND(1.0, 2) as A_COLUMN1 from foo a where ROUND(2.0, 3) = #{parameters.p1,jdbcType=INTEGER}");
    
        test(select(round(columnDouble1, columnInteger1, columnInteger2).as("A_COLUMN1")).from(table, "a").where(round(columnDouble1, columnInteger1, columnInteger2), isEqualTo(1)),
            	"select ROUND(a.columnDouble1, a.columnInteger1, a.columnInteger2) as A_COLUMN1 from foo a where ROUND(a.columnDouble1, a.columnInteger1, a.columnInteger2) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(round(columnDouble1, columnInteger1, 1).as("A_COLUMN1")).from(table, "a").where(round(columnDouble1, columnInteger1, 2), isEqualTo(1)),
            	"select ROUND(a.columnDouble1, a.columnInteger1, 1) as A_COLUMN1 from foo a where ROUND(a.columnDouble1, a.columnInteger1, 2) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(round(columnDouble1, 1, columnInteger2).as("A_COLUMN1")).from(table, "a").where(round(columnDouble1, 2, columnInteger2), isEqualTo(1)),
            	"select ROUND(a.columnDouble1, 1, a.columnInteger2) as A_COLUMN1 from foo a where ROUND(a.columnDouble1, 2, a.columnInteger2) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(round(columnDouble1, 1, 2).as("A_COLUMN1")).from(table, "a").where(round(columnDouble1, 2, 3), isEqualTo(1)),
            	"select ROUND(a.columnDouble1, 1, 2) as A_COLUMN1 from foo a where ROUND(a.columnDouble1, 2, 3) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(round(1.0, columnInteger1, columnInteger2).as("A_COLUMN1")).from(table, "a").where(round(2.0, columnInteger1, columnInteger2), isEqualTo(1)),
            	"select ROUND(1.0, a.columnInteger1, a.columnInteger2) as A_COLUMN1 from foo a where ROUND(2.0, a.columnInteger1, a.columnInteger2) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(round(1.0, columnInteger1, 3).as("A_COLUMN1")).from(table, "a").where(round(2.0, columnInteger1, 4), isEqualTo(1)),
            	"select ROUND(1.0, a.columnInteger1, 3) as A_COLUMN1 from foo a where ROUND(2.0, a.columnInteger1, 4) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(round(1.0, 3, columnInteger2).as("A_COLUMN1")).from(table, "a").where(round(2.0, 3, columnInteger2), isEqualTo(1)),
            	"select ROUND(1.0, 3, a.columnInteger2) as A_COLUMN1 from foo a where ROUND(2.0, 3, a.columnInteger2) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(round(1.0, 2, 3).as("A_COLUMN1")).from(table, "a").where(round(2.0, 3, 4), isEqualTo(1)),
            	"select ROUND(1.0, 2, 3) as A_COLUMN1 from foo a where ROUND(2.0, 3, 4) = #{parameters.p1,jdbcType=INTEGER}");
    }
    
    @Test
    public void testSign() {
        test(select(sign(columnInteger1).as("A_COLUMN1")).from(table, "a").where(sign(columnInteger1), isEqualTo(1)),
        	"select SIGN(a.columnInteger1) as A_COLUMN1 from foo a where SIGN(a.columnInteger1) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(sign(1).as("A_COLUMN1")).from(table, "a").where(sign(2), isEqualTo(1)),
        	"select SIGN(1) as A_COLUMN1 from foo a where SIGN(2) = #{parameters.p1,jdbcType=INTEGER}");
    }
    
    @Test
    public void testSum() {
        test(select(sum(columnInteger1).as("A_COLUMN1")).from(table, "a").where(sum(columnInteger1), isEqualTo(1)),
        	"select SUM(a.columnInteger1) as A_COLUMN1 from foo a where SUM(a.columnInteger1) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(sum(1).as("A_COLUMN1")).from(table, "a").where(sum(2), isEqualTo(1)),
        	"select SUM(1) as A_COLUMN1 from foo a where SUM(2) = #{parameters.p1,jdbcType=INTEGER}");
    }
    
    @SuppressWarnings("rawtypes")
   	protected void test(QueryExpressionWhereBuilder selectModel, String sql) {
       	SelectStatementProvider selectStatement = ((SelectModel) selectModel.build()).render(RenderingStrategy.MYBATIS3);
       	SoftAssertions.assertSoftly(softly -> softly.assertThat(selectStatement.getSelectStatement()).isEqualTo(sql));
    }
}
