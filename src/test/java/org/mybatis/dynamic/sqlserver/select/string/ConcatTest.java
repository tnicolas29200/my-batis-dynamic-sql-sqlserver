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
    public void testConcat() {
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
