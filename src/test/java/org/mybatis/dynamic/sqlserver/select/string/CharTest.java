package org.mybatis.dynamic.sqlserver.select.string;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.select;
import static org.mybatis.dynamic.sqlserver.SQLServerBuilder.character;

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
public class CharTest {

	public static final SqlTable table = SqlTable.of("foo");
    public static final SqlColumn<Integer> column1 = table.column("column1", JDBCType.INTEGER);
    
    @Test
    public void testAscii() {
        SelectStatementProvider selectStatement = select(character(column1).as("A_COLUMN1"))
                .from(table, "a")
                .where(character(column1), isEqualTo(1))
                .build()
                .render(RenderingStrategy.MYBATIS3);

        SoftAssertions.assertSoftly(softly -> {
            String expectedFullStatement = "select CHAR(a.column1) as A_COLUMN1 "
                    + "from foo a "
                    + "where CHAR(a.column1) = #{parameters.p1,jdbcType=VARCHAR}";

            softly.assertThat(selectStatement.getSelectStatement()).isEqualTo(expectedFullStatement);
        });
    }
}
