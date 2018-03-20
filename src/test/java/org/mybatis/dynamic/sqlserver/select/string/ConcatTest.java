package org.mybatis.dynamic.sqlserver.select.string;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.select;
import static org.mybatis.dynamic.sqlserver.SQLServerBuilder.concat;

import java.sql.JDBCType;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;

@RunWith(JUnitPlatform.class)
public class ConcatTest {

	public static final SqlTable table = SqlTable.of("foo");
    public static final SqlColumn<String> column1 = table.column("column1", JDBCType.VARCHAR);
    public static final SqlColumn<String> column2 = table.column("column2", JDBCType.VARCHAR);
    public static final SqlColumn<String> column3 = table.column("column3", JDBCType.VARCHAR);
    public static final SqlColumn<String> column4 = table.column("column4", JDBCType.VARCHAR);
    
    @Test
    public void testAscii() {
        SelectStatementProvider selectStatement = select(concat(column1, column2, column3, column4).as("A_COLUMN1"))
                .from(table, "a")
                .where(concat(column1, column2), isEqualTo("A"))
                .build()
                .render(RenderingStrategy.MYBATIS3);

        SoftAssertions.assertSoftly(softly -> {
            String expectedFullStatement = "select CONCAT(a.column1, a.column2, a.column3, a.column4) as A_COLUMN1 "
                    + "from foo a "
                    + "where CONCAT(a.column1, a.column2) = #{parameters.p1,jdbcType=VARCHAR}";

            softly.assertThat(selectStatement.getSelectStatement()).isEqualTo(expectedFullStatement);
        });
    }
}
