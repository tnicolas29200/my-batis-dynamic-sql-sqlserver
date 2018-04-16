package org.mybatis.dynamic.ext.sqlserver.select;

import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.coalesce;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.currentUser;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.isDate;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.isNull;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.isNumeric;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.nullIf;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.sessionProperty;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.sessionUser;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.systemUser;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.userName;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.select;

import java.sql.JDBCType;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL.QueryExpressionWhereBuilder;
import org.mybatis.dynamic.sql.select.SelectModel;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;

@RunWith(JUnitPlatform.class)
public class SystemFunctionsTest {

	public static final SqlTable table = SqlTable.of("foo");
    public static final SqlColumn<String> columnString1 = table.column("columnString1", JDBCType.VARCHAR);
    public static final SqlColumn<String> columnString2 = table.column("columnString2", JDBCType.VARCHAR);
    public static final SqlColumn<String> columnString3 = table.column("columnString3", JDBCType.VARCHAR);
    public static final SqlColumn<Integer> columnInteger1 = table.column("columnInteger1", JDBCType.INTEGER);
    public static final SqlColumn<Integer> columnInteger2 = table.column("columnInteger2", JDBCType.INTEGER);
    public static final SqlColumn<Integer> columnInteger3 = table.column("columnInteger3", JDBCType.INTEGER);
    
    @Test
    public void testCoalesce() {
        test(select(coalesce(columnString1, columnString2).as("A_COLUMN1")).from(table, "a").where(coalesce(columnString1, columnString2), isEqualTo(columnString1)),
        	"select COALESCE(a.columnString1, a.columnString2) as A_COLUMN1 from foo a where COALESCE(a.columnString1, a.columnString2) = a.columnString1");
    }
    
    @Test
    public void testCurrentUser() {
        test(select(currentUser().as("A_COLUMN1")).from(table, "a").where(currentUser(), isEqualTo("ABC")),
        	"select CURRENT_USER() as A_COLUMN1 from foo a where CURRENT_USER() = #{parameters.p1,jdbcType=VARCHAR}");
    }
    
    @Test
    public void testIsDate() {
        test(select(isDate(columnInteger1).as("A_COLUMN1")).from(table, "a").where(isDate(columnInteger1), isEqualTo(true)),
        	"select ISDATE(a.columnInteger1) as A_COLUMN1 from foo a where ISDATE(a.columnInteger1) = #{parameters.p1,jdbcType=BOOLEAN}");
    }
    
    @Test
    public void testIsNull() {
        test(select(isNull(columnInteger1).as("A_COLUMN1")).from(table, "a").where(isNull(columnInteger1), isEqualTo(true)),
        	"select ISNULL(a.columnInteger1) as A_COLUMN1 from foo a where ISNULL(a.columnInteger1) = #{parameters.p1,jdbcType=BOOLEAN}");
    }
    
    @Test
    public void testIsNumeric() {
        test(select(isNumeric(columnInteger1).as("A_COLUMN1")).from(table, "a").where(isNumeric(columnInteger1), isEqualTo(true)),
        	"select ISNUMERIC(a.columnInteger1) as A_COLUMN1 from foo a where ISNUMERIC(a.columnInteger1) = #{parameters.p1,jdbcType=BOOLEAN}");
    }
    
    @Test
    public void testNullIf() {
        test(select(nullIf(columnString1, columnString2).as("A_COLUMN1")).from(table, "a").where(nullIf(columnString1, columnString2), isEqualTo("ABC")),
        	"select NULLIF(a.columnString1, a.columnString2) as A_COLUMN1 from foo a where NULLIF(a.columnString1, a.columnString2) = #{parameters.p1,jdbcType=NULL}");
    }
    
    @Test
    public void testSessionProperty() {
        test(select(sessionProperty(columnString1).as("A_COLUMN1")).from(table, "a").where(sessionProperty(columnString1), isEqualTo("ABC")),
        	"select SESSIONPROPERTY(a.columnString1) as A_COLUMN1 from foo a where SESSIONPROPERTY(a.columnString1) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(sessionProperty("property").as("A_COLUMN1")).from(table, "a").where(sessionProperty("property"), isEqualTo("ABC")),
            	"select SESSIONPROPERTY('property') as A_COLUMN1 from foo a where SESSIONPROPERTY('property') = #{parameters.p1,jdbcType=VARCHAR}");
    }
    
    @Test
    public void testSessionUser() {
        test(select(sessionUser().as("A_COLUMN1")).from(table, "a").where(sessionUser(), isEqualTo("ABC")),
        	"select SESSION_USER() as A_COLUMN1 from foo a where SESSION_USER() = #{parameters.p1,jdbcType=VARCHAR}");
    }
    
    @Test
    public void testSystemUser() {
        test(select(systemUser().as("A_COLUMN1")).from(table, "a").where(systemUser(), isEqualTo("ABC")),
        	"select SYSTEM_USER() as A_COLUMN1 from foo a where SYSTEM_USER() = #{parameters.p1,jdbcType=VARCHAR}");
    }
    
    @Test
    public void testUserName() {
        test(select(userName().as("A_COLUMN1")).from(table, "a").where(userName(), isEqualTo("ABC")),
        	"select USER_NAME() as A_COLUMN1 from foo a where USER_NAME() = #{parameters.p1,jdbcType=VARCHAR}");
    }
    
    @SuppressWarnings("rawtypes")
	protected void test(QueryExpressionWhereBuilder selectModel, String sql) {
    	SelectStatementProvider selectStatement = ((SelectModel) selectModel.build()).render(RenderingStrategy.MYBATIS3);
    	SoftAssertions.assertSoftly(softly -> softly.assertThat(selectStatement.getSelectStatement()).isEqualTo(sql));
    }
}
