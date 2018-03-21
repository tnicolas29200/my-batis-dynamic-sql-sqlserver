package org.mybatis.dynamic.sqlserver.select;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.select;
import static org.mybatis.dynamic.sqlserver.SQLServerBuilder.ascii;
import static org.mybatis.dynamic.sqlserver.SQLServerBuilder.character;
import static org.mybatis.dynamic.sqlserver.SQLServerBuilder.charindex;
import static org.mybatis.dynamic.sqlserver.SQLServerBuilder.concat;
import static org.mybatis.dynamic.sqlserver.SQLServerBuilder.datalength;
import static org.mybatis.dynamic.sqlserver.SQLServerBuilder.left;
import static org.mybatis.dynamic.sqlserver.SQLServerBuilder.len;
import static org.mybatis.dynamic.sqlserver.SQLServerBuilder.lower;
import static org.mybatis.dynamic.sqlserver.SQLServerBuilder.ltrim;
import static org.mybatis.dynamic.sqlserver.SQLServerBuilder.nchar;
import static org.mybatis.dynamic.sqlserver.SQLServerBuilder.patindex;
import static org.mybatis.dynamic.sqlserver.SQLServerBuilder.replace;
import static org.mybatis.dynamic.sqlserver.SQLServerBuilder.right;
import static org.mybatis.dynamic.sqlserver.SQLServerBuilder.rtrim;

import java.sql.JDBCType;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;

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

@RunWith(JUnitPlatform.class)
public class StringFunctionsTest {

	public static final SqlTable table = SqlTable.of("foo");
    public static final SqlColumn<String> column1 = table.column("column1", JDBCType.VARCHAR);
    public static final SqlColumn<Integer> column2 = table.column("column2", JDBCType.INTEGER);
    public static final SqlColumn<String> column3 = table.column("column3", JDBCType.VARCHAR);
    public static final SqlColumn<String> column4 = table.column("column4", JDBCType.VARCHAR);
    
    @Test
    public void testAscii() {
        SelectStatementProvider selectStatement = select(ascii(column1).as("A_COLUMN1"))
                .from(table, "a")
                .where(ascii(column1), isEqualTo("A"))
                .build().render(RenderingStrategy.MYBATIS3);
        SoftAssertions.assertSoftly(softly -> softly.assertThat(selectStatement.getSelectStatement()).isEqualTo("select ASCII(a.column1) as A_COLUMN1 from foo a where ASCII(a.column1) = #{parameters.p1,jdbcType=VARCHAR}"));
    }
    
    @Test
    public void testCharIndex() {
        SelectStatementProvider selectStatement = select(charindex(column1, "test").as("A_COLUMN1"))
                .from(table, "a")
                .where(charindex(column1, "test", 2), isEqualTo("A"))
                .build().render(RenderingStrategy.MYBATIS3);
        SoftAssertions.assertSoftly(softly -> softly.assertThat(selectStatement.getSelectStatement()).isEqualTo("select CHARINDEX(a.column1, 'test') as A_COLUMN1 from foo a where CHARINDEX(a.column1, 'test', 2) = #{parameters.p1,jdbcType=INTEGER}"));
    }
    
    @Test
    public void testChar() {
        SelectStatementProvider selectStatement = select(character(column2).as("A_COLUMN2"))
                .from(table, "a")
                .where(character(column2), isEqualTo(1))
                .build().render(RenderingStrategy.MYBATIS3);
        SoftAssertions.assertSoftly(softly -> softly.assertThat(selectStatement.getSelectStatement()).isEqualTo("select CHAR(a.column2) as A_COLUMN2 from foo a where CHAR(a.column2) = #{parameters.p1,jdbcType=VARCHAR}"));
    }
	
    @Test
    public void testConcat() {
        SelectStatementProvider selectStatement = select(concat(column1, column3, column4).as("COLUMNS"))
                .from(table, "a")
                .where(concat(column1, column3), isEqualTo("A"))
                .build().render(RenderingStrategy.MYBATIS3);
        SoftAssertions.assertSoftly(softly -> softly.assertThat(selectStatement.getSelectStatement()).isEqualTo("select CONCAT(a.column1, a.column3, a.column4) as COLUMNS from foo a where CONCAT(a.column1, a.column3) = #{parameters.p1,jdbcType=VARCHAR}"));
    }
    
    @Test
    public void testDataLength() {
        SelectStatementProvider selectStatement = select(datalength(column1).as("A_COLUMN1"))
                .from(table, "a")
                .where(datalength(column1), isEqualTo("A"))
                .build().render(RenderingStrategy.MYBATIS3);
        SoftAssertions.assertSoftly(softly -> softly.assertThat(selectStatement.getSelectStatement()).isEqualTo("select DATALENGTH(a.column1) as A_COLUMN1 from foo a where DATALENGTH(a.column1) = #{parameters.p1,jdbcType=INTEGER}"));
    }
    
    @Test
    public void testLeft() {
        SelectStatementProvider selectStatement = select(left(column1, 2).as("A_COLUMN1"))
                .from(table, "a")
                .where(left(column1, 3), isEqualTo("ABC"))
                .build().render(RenderingStrategy.MYBATIS3);
        SoftAssertions.assertSoftly(softly -> softly.assertThat(selectStatement.getSelectStatement()).isEqualTo("select LEFT(a.column1, 2) as A_COLUMN1 from foo a where LEFT(a.column1, 3) = #{parameters.p1,jdbcType=VARCHAR}"));
    }
    
    @Test
    public void testLen() {
        SelectStatementProvider selectStatement = select(len(column1).as("A_COLUMN1"))
                .from(table, "a")
                .where(len(column1), isEqualTo("ABC"))
                .build().render(RenderingStrategy.MYBATIS3);
        SoftAssertions.assertSoftly(softly -> softly.assertThat(selectStatement.getSelectStatement()).isEqualTo("select LEN(a.column1) as A_COLUMN1 from foo a where LEN(a.column1) = #{parameters.p1,jdbcType=INTEGER}"));
    }
    
    @Test
    public void testLower() {
        SelectStatementProvider selectStatement = select(lower(column1).as("A_COLUMN1"))
                .from(table, "a")
                .where(lower(column1), isEqualTo("ABC"))
                .build()
                .render(RenderingStrategy.MYBATIS3);
        SoftAssertions.assertSoftly(softly -> softly.assertThat(selectStatement.getSelectStatement()).isEqualTo("select LOWER(a.column1) as A_COLUMN1 from foo a where LOWER(a.column1) = #{parameters.p1,jdbcType=VARCHAR}"));
    }
    
    @Test
    public void testLTrim() {
        SelectStatementProvider selectStatement = select(ltrim(column1).as("A_COLUMN1"))
                .from(table, "a")
                .where(ltrim(column1), isEqualTo("ABC"))
                .build()
                .render(RenderingStrategy.MYBATIS3);
        SoftAssertions.assertSoftly(softly -> softly.assertThat(selectStatement.getSelectStatement()).isEqualTo("select LTRIM(a.column1) as A_COLUMN1 from foo a where LTRIM(a.column1) = #{parameters.p1,jdbcType=VARCHAR}"));
    }
    
    @Test
    public void testNChar() {
        SelectStatementProvider selectStatement = select(nchar(column2).as("A_COLUMN2"))
                .from(table, "a")
                .where(nchar(column2), isEqualTo(0))
                .build()
                .render(RenderingStrategy.MYBATIS3);
        SoftAssertions.assertSoftly(softly -> softly.assertThat(selectStatement.getSelectStatement()).isEqualTo("select NCHAR(a.column2) as A_COLUMN2 from foo a where NCHAR(a.column2) = #{parameters.p1,jdbcType=NVARCHAR}"));
    }
    
    @Test
    public void testPatIndex() {
    	SelectStatementProvider selectStatement = select(patindex("pattern", column1).as("A_COLUMN1"))
                .from(table, "a")
                .where(patindex("pattern2", column1), isEqualTo("ABC"))
                .build()
                .render(RenderingStrategy.MYBATIS3);
        SoftAssertions.assertSoftly(softly -> softly.assertThat(selectStatement.getSelectStatement()).isEqualTo("select PATINDEX('pattern', a.column1) as A_COLUMN1 from foo a where PATINDEX('pattern2', a.column1) = #{parameters.p1,jdbcType=VARCHAR}"));
    }
    
    @Test
    public void testReplace() {
    	SelectStatementProvider selectStatement = select(replace(column1, "textToReplace", "textReplaced").as("A_COLUMN1"))
                .from(table, "a")
                .where(replace(column1, "textToReplace", "textReplaced"), isEqualTo("ABC"))
                .build()
                .render(RenderingStrategy.MYBATIS3);
        SoftAssertions.assertSoftly(softly -> softly.assertThat(selectStatement.getSelectStatement()).isEqualTo("select REPLACE(a.column1, 'textToReplace', 'textReplaced') as A_COLUMN1 from foo a where REPLACE(a.column1, 'textToReplace', 'textReplaced') = #{parameters.p1,jdbcType=VARCHAR}"));
    }
    
    @Test
    public void testRight() {
        SelectStatementProvider selectStatement = select(right(column1, 2).as("A_COLUMN1"))
                .from(table, "a")
                .where(right(column1, 3), isEqualTo("ABC"))
                .build().render(RenderingStrategy.MYBATIS3);
        SoftAssertions.assertSoftly(softly -> softly.assertThat(selectStatement.getSelectStatement()).isEqualTo("select RIGHT(a.column1, 2) as A_COLUMN1 from foo a where RIGHT(a.column1, 3) = #{parameters.p1,jdbcType=VARCHAR}"));
    }
    
    @Test
    public void testRTrim() {
        SelectStatementProvider selectStatement = select(rtrim(column1).as("A_COLUMN1"))
                .from(table, "a")
                .where(rtrim(column1), isEqualTo("ABC"))
                .build()
                .render(RenderingStrategy.MYBATIS3);
        SoftAssertions.assertSoftly(softly -> softly.assertThat(selectStatement.getSelectStatement()).isEqualTo("select RTRIM(a.column1) as A_COLUMN1 from foo a where RTRIM(a.column1) = #{parameters.p1,jdbcType=VARCHAR}"));
    }
}
