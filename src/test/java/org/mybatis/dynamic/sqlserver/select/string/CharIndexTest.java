package org.mybatis.dynamic.sqlserver.select.string;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.select;
import static org.mybatis.dynamic.sqlserver.SQLServerBuilder.charindex;

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
public class CharIndexTest {

	public static final SqlTable table = SqlTable.of("foo");
    public static final SqlColumn<String> column1 = table.column("column1", JDBCType.VARCHAR);
    
    @Test
    public void testCharIndex() {
        SelectStatementProvider selectStatement = select(charindex(column1, "test").as("A_COLUMN1"))
                .from(table, "a")
                .where(charindex(column1, "test", 2), isEqualTo("A"))
                .build()
                .render(RenderingStrategy.MYBATIS3);

        SoftAssertions.assertSoftly(softly -> {
            String expectedFullStatement = "select CHARINDEX(a.column1, 'test') as A_COLUMN1 "
                    + "from foo a "
                    + "where CHARINDEX(a.column1, 'test', 2) = #{parameters.p1,jdbcType=INTEGER}";

            softly.assertThat(selectStatement.getSelectStatement()).isEqualTo(expectedFullStatement);
        });
    }
}
