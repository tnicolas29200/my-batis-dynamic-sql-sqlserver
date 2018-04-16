package org.mybatis.dynamic.ext.sqlserver.select;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.select;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.cast;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.convert;

import java.sql.JDBCType;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.SoftAssertions;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.SelectModel;
import org.mybatis.dynamic.ext.sql.select.ConvertStyle;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL.QueryExpressionWhereBuilder;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;

@RunWith(JUnitPlatform.class)
public class ConversionFunctionsTest {

	public static final SqlTable table = SqlTable.of("foo");
    public static final SqlColumn<Integer> columnInteger1 = table.column("columnInteger1", JDBCType.INTEGER);
    public static final SqlColumn<Integer> columnInteger2 = table.column("columnInteger2", JDBCType.INTEGER);
    public static final SqlColumn<Integer> columnInteger3 = table.column("columnInteger3", JDBCType.INTEGER);
    public static final SqlColumn<Double> columnDouble1 = table.column("columnDouble1", JDBCType.DOUBLE);
    public static final SqlColumn<String> columnString1 = table.column("columnString1", JDBCType.VARCHAR);
    public static final SqlColumn<Date> columnDate1 = table.column("columnDate1", JDBCType.VARCHAR);
    
    private static final Date date;
    private static final String dateRepresentation = "'2018-03-25T19:30:11.147'";
    
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
    }
    
    
    @Test
    public void testCast() {
    	test(select(cast(columnString1, 1, JDBCType.INTEGER).as("A_COLUMN1")).from(table, "a").where(cast(columnString1, 1, JDBCType.INTEGER), isEqualTo(1)),
            "select CAST(a.columnString1 AS INTEGER) as A_COLUMN1 from foo a where CAST(a.columnString1 AS INTEGER) = #{parameters.p1,jdbcType=INTEGER}");
        
    	test(select(cast(1.2, 3, JDBCType.INTEGER).as("A_COLUMN1")).from(table, "a").where(cast(1.2, 3, JDBCType.INTEGER), isEqualTo(1)),
        	"select CAST(1.2 AS INTEGER) as A_COLUMN1 from foo a where CAST(1.2 AS INTEGER) = #{parameters.p1,jdbcType=INTEGER}");
        
    	test(select(cast(columnString1, 1, JDBCType.INTEGER, 8).as("A_COLUMN1")).from(table, "a").where(cast(columnString1, 1, JDBCType.INTEGER, 8), isEqualTo(1)),
                "select CAST(a.columnString1 AS INTEGER(8)) as A_COLUMN1 from foo a where CAST(a.columnString1 AS INTEGER(8)) = #{parameters.p1,jdbcType=INTEGER}");
    	
    	test(select(cast(1.2, 3, JDBCType.INTEGER, 8).as("A_COLUMN1")).from(table, "a").where(cast(1.2, 3, JDBCType.INTEGER, 8), isEqualTo(1)),
            	"select CAST(1.2 AS INTEGER(8)) as A_COLUMN1 from foo a where CAST(1.2 AS INTEGER(8)) = #{parameters.p1,jdbcType=INTEGER}");
    }
    
    @Test
    public void testConvert() {
    	test(select(convert(1, JDBCType.INTEGER, columnString1).as("A_COLUMN1")).from(table, "a").where(convert(1, JDBCType.INTEGER, columnString1), isEqualTo(1)),
            "select CONVERT(INTEGER, a.columnString1) as A_COLUMN1 from foo a where CONVERT(INTEGER, a.columnString1) = #{parameters.p1,jdbcType=INTEGER}");
    	
    	test(select(convert(1, JDBCType.INTEGER, 2.3).as("A_COLUMN1")).from(table, "a").where(convert(1, JDBCType.INTEGER, 2.3), isEqualTo(1)),
                "select CONVERT(INTEGER, 2.3) as A_COLUMN1 from foo a where CONVERT(INTEGER, 2.3) = #{parameters.p1,jdbcType=INTEGER}");
    	
    	test(select(convert(1, JDBCType.INTEGER, 8, columnString1).as("A_COLUMN1")).from(table, "a").where(convert(1, JDBCType.INTEGER, 8, columnString1), isEqualTo(1)),
                "select CONVERT(INTEGER(8), a.columnString1) as A_COLUMN1 from foo a where CONVERT(INTEGER(8), a.columnString1) = #{parameters.p1,jdbcType=INTEGER}");
        	
        test(select(convert(1, JDBCType.INTEGER, 8, 2.3).as("A_COLUMN1")).from(table, "a").where(convert(1, JDBCType.INTEGER, 8, 2.3), isEqualTo(1)),
                "select CONVERT(INTEGER(8), 2.3) as A_COLUMN1 from foo a where CONVERT(INTEGER(8), 2.3) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(convert(new Date(), JDBCType.DATE, columnDate1, ConvertStyle.dateDefault).as("A_COLUMN1")).from(table, "a").where(convert(new Date(), JDBCType.DATE, columnDate1, ConvertStyle.dateDefaultWithoutCentury), isEqualTo(new Date())),
                "select CONVERT(DATE, a.columnDate1, 100) as A_COLUMN1 from foo a where CONVERT(DATE, a.columnDate1, 0) = #{parameters.p1,jdbcType=DATE}");
        	
    	test(select(convert(new Date(), JDBCType.DATE, date, ConvertStyle.dateDefault).as("A_COLUMN1")).from(table, "a").where(convert(new Date(), JDBCType.DATE, date, ConvertStyle.dateDefaultWithoutCentury), isEqualTo(new Date())),
                "select CONVERT(DATE, " + dateRepresentation + ", 100) as A_COLUMN1 from foo a where CONVERT(DATE, " + dateRepresentation + ", 0) = #{parameters.p1,jdbcType=DATE}");
    	
    	test(select(convert(new Date(), JDBCType.DATE, 8, columnDate1, ConvertStyle.dateDefault).as("A_COLUMN1")).from(table, "a").where(convert(new Date(), JDBCType.DATE, 8, columnDate1, ConvertStyle.dateDefaultWithoutCentury), isEqualTo(new Date())),
                "select CONVERT(DATE(8), a.columnDate1, 100) as A_COLUMN1 from foo a where CONVERT(DATE(8), a.columnDate1, 0) = #{parameters.p1,jdbcType=DATE}");
        	
        test(select(convert(new Date(), JDBCType.DATE, 8, date, ConvertStyle.dateDefault).as("A_COLUMN1")).from(table, "a").where(convert(new Date(), JDBCType.DATE, 8, date, ConvertStyle.dateDefaultWithoutCentury), isEqualTo(new Date())),
                "select CONVERT(DATE(8), " + dateRepresentation + ", 100) as A_COLUMN1 from foo a where CONVERT(DATE(8), " + dateRepresentation + ", 0) = #{parameters.p1,jdbcType=DATE}");
    
    }
    
    @SuppressWarnings("rawtypes")
   	protected void test(QueryExpressionWhereBuilder selectModel, String sql) {
       	SelectStatementProvider selectStatement = ((SelectModel) selectModel.build()).render(RenderingStrategy.MYBATIS3);
       	SoftAssertions.assertSoftly(softly -> softly.assertThat(selectStatement.getSelectStatement()).isEqualTo(sql));
    }
}
