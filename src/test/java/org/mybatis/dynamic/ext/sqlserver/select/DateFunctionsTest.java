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
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.currentTimeStamp;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.dateAdd;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.dateDiff;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.dateName;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.datePart;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.day;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.getDate;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.getUtcDate;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.month;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.year;

import java.sql.JDBCType;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.ext.sql.select.DateInterval;
import org.mybatis.dynamic.sql.select.SelectModel;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL.QueryExpressionWhereBuilder;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;

@RunWith(JUnitPlatform.class)
public class DateFunctionsTest {
	
	public static final SqlTable table = SqlTable.of("foo");
    public static final SqlColumn<Date> columnDate1 = table.column("columnDate1", JDBCType.DATE);
    public static final SqlColumn<Date> columnDate2 = table.column("columnDate2", JDBCType.DATE);
    public static final SqlColumn<Date> columnDate3 = table.column("columnDate3", JDBCType.DATE);
    public static final SqlColumn<Integer> columnInteger1 = table.column("columnInteger1", JDBCType.INTEGER);
    public static final SqlColumn<Integer> columnInteger2 = table.column("columnInteger2", JDBCType.INTEGER);
    public static final SqlColumn<Integer> columnInteger3 = table.column("columnInteger3", JDBCType.INTEGER);
    public static final SqlColumn<String> columnString1 = table.column("columnString1", JDBCType.VARCHAR);
    public static final SqlColumn<String> columnString2 = table.column("columnString2", JDBCType.VARCHAR);
    public static final SqlColumn<String> columnString3 = table.column("columnString3", JDBCType.VARCHAR);
    
    private static final Date date;
    private static final Date date2;
    private static final String dateRepresentation = "'2018-03-25T19:30:11.147'";
    private static final String date2Representation = "'2017-02-24T18:29:10.146'";
    
    static {
    	Calendar calendar = GregorianCalendar.getInstance();
    	calendar.set(Calendar.YEAR, 2018);
    	calendar.set(Calendar.MONTH, Calendar.MARCH);
    	calendar.set(Calendar.DAY_OF_MONTH, 25);
    	calendar.set(Calendar.HOUR_OF_DAY, 19);
    	calendar.set(Calendar.MINUTE, 30);
    	calendar.set(Calendar.SECOND, 11);
    	calendar.set(Calendar.MILLISECOND, 147);
    	date = new Date(calendar.getTimeInMillis());
    	
    	calendar = GregorianCalendar.getInstance();
    	calendar.set(Calendar.YEAR, 2017);
    	calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
    	calendar.set(Calendar.DAY_OF_MONTH, 24);
    	calendar.set(Calendar.HOUR_OF_DAY, 18);
    	calendar.set(Calendar.MINUTE, 29);
    	calendar.set(Calendar.SECOND, 10);
    	calendar.set(Calendar.MILLISECOND, 146);
    	date2 = new Date(calendar.getTimeInMillis());
    }
    
    @Test
    public void testCurrentTimeStamp() {
    	test(select(currentTimeStamp().as("A_COLUMN1")).from(table, "a").where(currentTimeStamp(), isEqualTo(new Date(0))),
            	"select CURRENT_TIMESTAMP() as A_COLUMN1 from foo a where CURRENT_TIMESTAMP() = #{parameters.p1,jdbcType=DATE}");
    }
    
    @Test
    public void testDateAdd() {
    	test(select(dateAdd(columnString1, columnInteger1, columnDate1).as("A_COLUMN1")).from(table, "a").where(dateAdd(columnString1, columnInteger1, columnDate1), isEqualTo(new Date(0))),
            	"select DATEADD(a.columnString1, a.columnInteger1, a.columnDate1) as A_COLUMN1 from foo a where DATEADD(a.columnString1, a.columnInteger1, a.columnDate1) = #{parameters.p1,jdbcType=DATE}");
            
        test(select(dateAdd(columnString1, columnInteger1, date).as("A_COLUMN1")).from(table, "a").where(dateAdd(columnString1, columnInteger1, date), isEqualTo(new Date(0))),
            	"select DATEADD(a.columnString1, a.columnInteger1, " + dateRepresentation + ") as A_COLUMN1 from foo a where DATEADD(a.columnString1, a.columnInteger1, " + dateRepresentation + ") = #{parameters.p1,jdbcType=DATE}");
        
        test(select(dateAdd(columnString1, 1, columnDate1).as("A_COLUMN1")).from(table, "a").where(dateAdd(columnString1, 1, columnDate1), isEqualTo(new Date(0))),
            	"select DATEADD(a.columnString1, 1, a.columnDate1) as A_COLUMN1 from foo a where DATEADD(a.columnString1, 1, a.columnDate1) = #{parameters.p1,jdbcType=DATE}");
        
        test(select(dateAdd(columnString1, 1, date).as("A_COLUMN1")).from(table, "a").where(dateAdd(columnString1, 1, date), isEqualTo(new Date(0))),
            	"select DATEADD(a.columnString1, 1, " + dateRepresentation + ") as A_COLUMN1 from foo a where DATEADD(a.columnString1, 1, " + dateRepresentation + ") = #{parameters.p1,jdbcType=DATE}");
        
        test(select(dateAdd(DateInterval.day, columnInteger1, columnDate1).as("A_COLUMN1")).from(table, "a").where(dateAdd(DateInterval.day, columnInteger1, columnDate1), isEqualTo(new Date(0))),
            	"select DATEADD(day, a.columnInteger1, a.columnDate1) as A_COLUMN1 from foo a where DATEADD(day, a.columnInteger1, a.columnDate1) = #{parameters.p1,jdbcType=DATE}");
            
        test(select(dateAdd(DateInterval.day, columnInteger1, date).as("A_COLUMN1")).from(table, "a").where(dateAdd(DateInterval.day, columnInteger1, date), isEqualTo(new Date(0))),
            	"select DATEADD(day, a.columnInteger1, " + dateRepresentation + ") as A_COLUMN1 from foo a where DATEADD(day, a.columnInteger1, " + dateRepresentation + ") = #{parameters.p1,jdbcType=DATE}");
        
        test(select(dateAdd(DateInterval.day, 1, columnDate1).as("A_COLUMN1")).from(table, "a").where(dateAdd(DateInterval.day, 1, columnDate1), isEqualTo(new Date(0))),
            	"select DATEADD(day, 1, a.columnDate1) as A_COLUMN1 from foo a where DATEADD(day, 1, a.columnDate1) = #{parameters.p1,jdbcType=DATE}");
        
        test(select(dateAdd(DateInterval.day, 1, date).as("A_COLUMN1")).from(table, "a").where(dateAdd(DateInterval.day, 1, date), isEqualTo(new Date(0))),
            	"select DATEADD(day, 1, " + dateRepresentation + ") as A_COLUMN1 from foo a where DATEADD(day, 1, " + dateRepresentation + ") = #{parameters.p1,jdbcType=DATE}");
    }
    
    @Test
    public void testDateDiff() {
    	test(select(dateDiff(columnString1, columnDate1, columnDate2).as("A_COLUMN1")).from(table, "a").where(dateDiff(columnString1, columnDate1, columnDate2), isEqualTo(new Date(0))),
            	"select DATEDIFF(a.columnString1, a.columnDate1, a.columnDate2) as A_COLUMN1 from foo a where DATEDIFF(a.columnString1, a.columnDate1, a.columnDate2) = #{parameters.p1,jdbcType=DATE}");
            
        test(select(dateDiff(columnString1, columnDate1, date).as("A_COLUMN1")).from(table, "a").where(dateDiff(columnString1, columnDate1, date), isEqualTo(new Date(0))),
            	"select DATEDIFF(a.columnString1, a.columnDate1, " + dateRepresentation + ") as A_COLUMN1 from foo a where DATEDIFF(a.columnString1, a.columnDate1, " + dateRepresentation + ") = #{parameters.p1,jdbcType=DATE}");
        
        test(select(dateDiff(columnString1, date, columnDate2).as("A_COLUMN1")).from(table, "a").where(dateDiff(columnString1, date, columnDate2), isEqualTo(new Date(0))),
            	"select DATEDIFF(a.columnString1, " + dateRepresentation + ", a.columnDate2) as A_COLUMN1 from foo a where DATEDIFF(a.columnString1, " + dateRepresentation + ", a.columnDate2) = #{parameters.p1,jdbcType=DATE}");
        
        test(select(dateDiff(columnString1, date, date2).as("A_COLUMN1")).from(table, "a").where(dateDiff(columnString1, date, date2), isEqualTo(new Date(0))),
            	"select DATEDIFF(a.columnString1, " + dateRepresentation + ", " + date2Representation + ") as A_COLUMN1 from foo a where DATEDIFF(a.columnString1, " + dateRepresentation + ", " + date2Representation + ") = #{parameters.p1,jdbcType=DATE}");
        
        test(select(dateDiff(DateInterval.day, columnDate1, columnDate2).as("A_COLUMN1")).from(table, "a").where(dateDiff(DateInterval.day, columnDate1, columnDate2), isEqualTo(new Date(0))),
            	"select DATEDIFF(day, a.columnDate1, a.columnDate2) as A_COLUMN1 from foo a where DATEDIFF(day, a.columnDate1, a.columnDate2) = #{parameters.p1,jdbcType=DATE}");
            
        test(select(dateDiff(DateInterval.day, columnDate1, date).as("A_COLUMN1")).from(table, "a").where(dateDiff(DateInterval.day, columnDate1, date), isEqualTo(new Date(0))),
            	"select DATEDIFF(day, a.columnDate1, " + dateRepresentation + ") as A_COLUMN1 from foo a where DATEDIFF(day, a.columnDate1, " + dateRepresentation + ") = #{parameters.p1,jdbcType=DATE}");
        
        test(select(dateDiff(DateInterval.day, date, columnDate1).as("A_COLUMN1")).from(table, "a").where(dateDiff(DateInterval.day, date, columnDate2), isEqualTo(new Date(0))),
            	"select DATEDIFF(day, " + dateRepresentation + ", a.columnDate1) as A_COLUMN1 from foo a where DATEDIFF(day, " + dateRepresentation + ", a.columnDate2) = #{parameters.p1,jdbcType=DATE}");
        
        test(select(dateDiff(DateInterval.day, date, date2).as("A_COLUMN1")).from(table, "a").where(dateDiff(DateInterval.day, date, date2), isEqualTo(new Date(0))),
            	"select DATEDIFF(day, " + dateRepresentation + ", " + date2Representation + ") as A_COLUMN1 from foo a where DATEDIFF(day, " + dateRepresentation + ", " + date2Representation + ") = #{parameters.p1,jdbcType=DATE}");
    }

    @Test
    public void testDateName() {
    	test(select(dateName(columnString1, columnDate1).as("A_COLUMN1")).from(table, "a").where(dateName(columnString1, columnDate1), isEqualTo(new Date(0))),
            	"select DATENAME(a.columnString1, a.columnDate1) as A_COLUMN1 from foo a where DATENAME(a.columnString1, a.columnDate1) = #{parameters.p1,jdbcType=DATE}");
            
        test(select(dateName(columnString1, date).as("A_COLUMN1")).from(table, "a").where(dateName(columnString1, date), isEqualTo(new Date(0))),
            	"select DATENAME(a.columnString1, " + dateRepresentation + ") as A_COLUMN1 from foo a where DATENAME(a.columnString1, " + dateRepresentation + ") = #{parameters.p1,jdbcType=DATE}");
        
        test(select(dateName(DateInterval.month, columnDate1).as("A_COLUMN1")).from(table, "a").where(dateName(DateInterval.month, columnDate1), isEqualTo(new Date(0))),
            	"select DATENAME(month, a.columnDate1) as A_COLUMN1 from foo a where DATENAME(month, a.columnDate1) = #{parameters.p1,jdbcType=DATE}");
        
        test(select(dateName(DateInterval.month, date).as("A_COLUMN1")).from(table, "a").where(dateName(DateInterval.month, date), isEqualTo(new Date(0))),
            	"select DATENAME(month, " + dateRepresentation + ") as A_COLUMN1 from foo a where DATENAME(month, " + dateRepresentation + ") = #{parameters.p1,jdbcType=DATE}");
    }
    
    @Test
    public void testDatePart() {
    	test(select(datePart(columnString1, columnDate1).as("A_COLUMN1")).from(table, "a").where(datePart(columnString1, columnDate1), isEqualTo(new Date(0))),
            	"select DATEPART(a.columnString1, a.columnDate1) as A_COLUMN1 from foo a where DATEPART(a.columnString1, a.columnDate1) = #{parameters.p1,jdbcType=DATE}");
            
        test(select(datePart(columnString1, date).as("A_COLUMN1")).from(table, "a").where(datePart(columnString1, date), isEqualTo(new Date(0))),
            	"select DATEPART(a.columnString1, " + dateRepresentation + ") as A_COLUMN1 from foo a where DATEPART(a.columnString1, " + dateRepresentation + ") = #{parameters.p1,jdbcType=DATE}");
        
        test(select(datePart(DateInterval.month, columnDate1).as("A_COLUMN1")).from(table, "a").where(datePart(DateInterval.month, columnDate1), isEqualTo(new Date(0))),
            	"select DATEPART(month, a.columnDate1) as A_COLUMN1 from foo a where DATEPART(month, a.columnDate1) = #{parameters.p1,jdbcType=DATE}");
        
        test(select(datePart(DateInterval.month, date).as("A_COLUMN1")).from(table, "a").where(datePart(DateInterval.month, date), isEqualTo(new Date(0))),
            	"select DATEPART(month, " + dateRepresentation + ") as A_COLUMN1 from foo a where DATEPART(month, " + dateRepresentation + ") = #{parameters.p1,jdbcType=DATE}");
    }
    
    @Test
    public void testDay() {
    	test(select(day(columnDate1).as("A_COLUMN1")).from(table, "a").where(day(columnDate1), isEqualTo(0)),
            	"select DAY(a.columnDate1) as A_COLUMN1 from foo a where DAY(a.columnDate1) = #{parameters.p1,jdbcType=INTEGER}");
            
    	test(select(day(date).as("A_COLUMN1")).from(table, "a").where(day(date), isEqualTo(0)),
            	"select DAY(" + dateRepresentation + ") as A_COLUMN1 from foo a where DAY(" + dateRepresentation + ") = #{parameters.p1,jdbcType=INTEGER}");
    }
    
    @Test
    public void testGetDate() {
    	test(select(getDate().as("A_COLUMN1")).from(table, "a").where(getDate(), isEqualTo(date)),
            	"select GETDATE() as A_COLUMN1 from foo a where GETDATE() = #{parameters.p1,jdbcType=DATE}");
    }
    
    @Test
    public void testGetUtcDate() {
    	test(select(getUtcDate().as("A_COLUMN1")).from(table, "a").where(getUtcDate(), isEqualTo(date)),
            	"select GETUTCDATE() as A_COLUMN1 from foo a where GETUTCDATE() = #{parameters.p1,jdbcType=DATE}");
    }
    
    @Test
    public void testMonth() {
    	test(select(month(columnDate1).as("A_COLUMN1")).from(table, "a").where(month(columnDate1), isEqualTo(0)),
            	"select MONTH(a.columnDate1) as A_COLUMN1 from foo a where MONTH(a.columnDate1) = #{parameters.p1,jdbcType=INTEGER}");
            
    	test(select(month(date).as("A_COLUMN1")).from(table, "a").where(month(date), isEqualTo(0)),
            	"select MONTH(" + dateRepresentation + ") as A_COLUMN1 from foo a where MONTH(" + dateRepresentation + ") = #{parameters.p1,jdbcType=INTEGER}");
    }
    
    @Test
    public void testYear() {
    	test(select(year(columnDate1).as("A_COLUMN1")).from(table, "a").where(year(columnDate1), isEqualTo(0)),
            	"select YEAR(a.columnDate1) as A_COLUMN1 from foo a where YEAR(a.columnDate1) = #{parameters.p1,jdbcType=INTEGER}");
            
    	test(select(year(date).as("A_COLUMN1")).from(table, "a").where(year(date), isEqualTo(0)),
            	"select YEAR(" + dateRepresentation + ") as A_COLUMN1 from foo a where YEAR(" + dateRepresentation + ") = #{parameters.p1,jdbcType=INTEGER}");
    }
    
    @SuppressWarnings("rawtypes")
   	protected void test(QueryExpressionWhereBuilder selectModel, String sql) {
       	SelectStatementProvider selectStatement = ((SelectModel) selectModel.build()).render(RenderingStrategy.MYBATIS3);
       	SoftAssertions.assertSoftly(softly -> softly.assertThat(selectStatement.getSelectStatement()).isEqualTo(sql));
    }
}
