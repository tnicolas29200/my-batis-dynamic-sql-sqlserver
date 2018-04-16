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
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.ascii;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.character;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.charindex;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.concat;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.datalength;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.left;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.len;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.lower;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.ltrim;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.nchar;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.patindex;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.replace;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.right;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.rtrim;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.space;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.str;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.stuff;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.substring;
import static org.mybatis.dynamic.ext.sqlserver.SQLServerBuilder.upper;

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
public class StringFunctionsTest {

	public static final SqlTable table = SqlTable.of("foo");
    public static final SqlColumn<String> columnString1 = table.column("columnString1", JDBCType.VARCHAR);
    public static final SqlColumn<String> columnString2 = table.column("columnString2", JDBCType.VARCHAR);
    public static final SqlColumn<String> columnString3 = table.column("columnString3", JDBCType.VARCHAR);
    public static final SqlColumn<Integer> columnInteger1 = table.column("columnInteger1", JDBCType.INTEGER);
    public static final SqlColumn<Integer> columnInteger2 = table.column("columnInteger2", JDBCType.INTEGER);
    public static final SqlColumn<Integer> columnInteger3 = table.column("columnInteger3", JDBCType.INTEGER);
    
    @Test
    public void testAscii() {
        test(select(ascii(columnString1).as("A_COLUMN1")).from(table, "a").where(ascii(columnString1), isEqualTo(1)),
        	"select ASCII(a.columnString1) as A_COLUMN1 from foo a where ASCII(a.columnString1) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(ascii("1").as("A_COLUMN1")).from(table, "a").where(ascii("2"), isEqualTo(1)),
        	"select ASCII('1') as A_COLUMN1 from foo a where ASCII('2') = #{parameters.p1,jdbcType=INTEGER}");
    }
    
    @Test
    public void testCharIndex() {
        test(select(charindex(columnString1, columnString2).as("A_COLUMN1")).from(table, "a").where(charindex(columnString1, columnString2), isEqualTo(1)),
        	"select CHARINDEX(a.columnString1, a.columnString2) as A_COLUMN1 from foo a where CHARINDEX(a.columnString1, a.columnString2) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(charindex(columnString1, "test").as("A_COLUMN1")).from(table, "a").where(charindex(columnString1, "test"), isEqualTo(1)),
        	"select CHARINDEX(a.columnString1, 'test') as A_COLUMN1 from foo a where CHARINDEX(a.columnString1, 'test') = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(charindex("test3", columnString2).as("A_COLUMN1")).from(table, "a").where(charindex("test", columnString2), isEqualTo(1)),
        	"select CHARINDEX('test3', a.columnString2) as A_COLUMN1 from foo a where CHARINDEX('test', a.columnString2) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(charindex("test string", "test").as("A_COLUMN1")).from(table, "a").where(charindex("string test", "test"), isEqualTo(1)),
        	"select CHARINDEX('test string', 'test') as A_COLUMN1 from foo a where CHARINDEX('string test', 'test') = #{parameters.p1,jdbcType=INTEGER}");
        
        
        test(select(charindex(columnString1, columnString2, columnInteger1).as("A_COLUMN1")).from(table, "a").where(charindex(columnString1, columnString2, columnInteger1), isEqualTo(1)),
        	"select CHARINDEX(a.columnString1, a.columnString2, a.columnInteger1) as A_COLUMN1 from foo a where CHARINDEX(a.columnString1, a.columnString2, a.columnInteger1) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(charindex(columnString1, columnString2, 1).as("A_COLUMN1")).from(table, "a").where(charindex(columnString1, columnString2, 2), isEqualTo(1)),
        	"select CHARINDEX(a.columnString1, a.columnString2, 1) as A_COLUMN1 from foo a where CHARINDEX(a.columnString1, a.columnString2, 2) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(charindex(columnString1, "test", columnInteger1).as("A_COLUMN1")).from(table, "a").where(charindex(columnString1, "test", columnInteger1), isEqualTo(1)),
        	"select CHARINDEX(a.columnString1, 'test', a.columnInteger1) as A_COLUMN1 from foo a where CHARINDEX(a.columnString1, 'test', a.columnInteger1) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(charindex(columnString1, "test", 1).as("A_COLUMN1")).from(table, "a").where(charindex(columnString1, "test", 1), isEqualTo(1)),
        	"select CHARINDEX(a.columnString1, 'test', 1) as A_COLUMN1 from foo a where CHARINDEX(a.columnString1, 'test', 1) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(charindex("test", columnString2, columnInteger1).as("A_COLUMN1")).from(table, "a").where(charindex("test", columnString2, columnInteger1), isEqualTo(1)),
        	"select CHARINDEX('test', a.columnString2, a.columnInteger1) as A_COLUMN1 from foo a where CHARINDEX('test', a.columnString2, a.columnInteger1) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(charindex("test", columnString2, 1).as("A_COLUMN1")).from(table, "a").where(charindex("test", columnString2, 2), isEqualTo(1)),
        	"select CHARINDEX('test', a.columnString2, 1) as A_COLUMN1 from foo a where CHARINDEX('test', a.columnString2, 2) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(charindex("test", "test", columnInteger1).as("A_COLUMN1")).from(table, "a").where(charindex("test", "test", columnInteger1), isEqualTo(1)),
        	"select CHARINDEX('test', 'test', a.columnInteger1) as A_COLUMN1 from foo a where CHARINDEX('test', 'test', a.columnInteger1) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(charindex("test", "test", 1).as("A_COLUMN1")).from(table, "a").where(charindex("test", "test", 1), isEqualTo(1)),
        	"select CHARINDEX('test', 'test', 1) as A_COLUMN1 from foo a where CHARINDEX('test', 'test', 1) = #{parameters.p1,jdbcType=INTEGER}");
    }
    
    @Test
    public void testChar() {
        test(select(character(columnInteger1).as("A_COLUMN2")).from(table, "a").where(character(columnInteger1), isEqualTo("A")),
        	"select CHAR(a.columnInteger1) as A_COLUMN2 from foo a where CHAR(a.columnInteger1) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(character(1).as("A_COLUMN2")).from(table, "a").where(character(2), isEqualTo("A")),
        	"select CHAR(1) as A_COLUMN2 from foo a where CHAR(2) = #{parameters.p1,jdbcType=VARCHAR}");
    }
	
    @Test
    public void testConcat() {
        test(select(concat(columnString1, columnString2, columnString3).as("COLUMNS")).from(table, "a").where(concat(columnString1, columnString2), isEqualTo("A")),
        	"select CONCAT(a.columnString1, a.columnString2, a.columnString3) as COLUMNS from foo a where CONCAT(a.columnString1, a.columnString2) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(concat(columnString1, columnString2, "test").as("COLUMNS")).from(table, "a").where(concat(columnString1, columnString2, "test"), isEqualTo("A")),
            	"select CONCAT(a.columnString1, a.columnString2, 'test') as COLUMNS from foo a where CONCAT(a.columnString1, a.columnString2, 'test') = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(concat(columnString1, "test", columnString2).as("COLUMNS")).from(table, "a").where(concat(columnString1, "test", columnString2), isEqualTo("A")),
            	"select CONCAT(a.columnString1, 'test', a.columnString2) as COLUMNS from foo a where CONCAT(a.columnString1, 'test', a.columnString2) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(concat(columnString1, columnString2, "test", columnString3).as("COLUMNS")).from(table, "a").where(concat(columnString1, columnString2, "test", columnString3), isEqualTo("A")),
            	"select CONCAT(a.columnString1, a.columnString2, 'test', a.columnString3) as COLUMNS from foo a where CONCAT(a.columnString1, a.columnString2, 'test', a.columnString3) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(concat("test", columnString1, columnString2).as("COLUMNS")).from(table, "a").where(concat("test", columnString1, columnString2), isEqualTo("A")),
            	"select CONCAT('test', a.columnString1, a.columnString2) as COLUMNS from foo a where CONCAT('test', a.columnString1, a.columnString2) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(concat("test", columnString1, "test2").as("COLUMNS")).from(table, "a").where(concat("test", columnString1, "test2"), isEqualTo("A")),
            	"select CONCAT('test', a.columnString1, 'test2') as COLUMNS from foo a where CONCAT('test', a.columnString1, 'test2') = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(concat("test", "test2", columnString1).as("COLUMNS")).from(table, "a").where(concat("test", "test2", columnString1), isEqualTo("A")),
            	"select CONCAT('test', 'test2', a.columnString1) as COLUMNS from foo a where CONCAT('test', 'test2', a.columnString1) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(concat("test", "test2", "test3").as("COLUMNS")).from(table, "a").where(concat("test", "test2", "test3"), isEqualTo("A")),
            	"select CONCAT('test', 'test2', 'test3') as COLUMNS from foo a where CONCAT('test', 'test2', 'test3') = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(concat("columnString1", "columnString2", "columnString3").as("COLUMNS")).from(table, "a").where(concat("columnString1", "columnString2"), isEqualTo("A")),
        	"select CONCAT('columnString1', 'columnString2', 'columnString3') as COLUMNS from foo a where CONCAT('columnString1', 'columnString2') = #{parameters.p1,jdbcType=VARCHAR}");
    }
    
    @Test
    public void testDataLength() {
        test(select(datalength(columnString1).as("A_COLUMN1")).from(table, "a").where(datalength(columnString1), isEqualTo(0)),
        	"select DATALENGTH(a.columnString1) as A_COLUMN1 from foo a where DATALENGTH(a.columnString1) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(datalength("column").as("A_COLUMN1")).from(table, "a").where(datalength("columnInteger1"), isEqualTo(0)),
        	"select DATALENGTH('column') as A_COLUMN1 from foo a where DATALENGTH('columnInteger1') = #{parameters.p1,jdbcType=INTEGER}");
    }
    
    @Test
    public void testLeft() {
    	test(select(left(columnString1, columnInteger1).as("A_COLUMN1")).from(table, "a").where(left(columnString1, columnInteger1), isEqualTo("ABC")),
    		"select LEFT(a.columnString1, a.columnInteger1) as A_COLUMN1 from foo a where LEFT(a.columnString1, a.columnInteger1) = #{parameters.p1,jdbcType=VARCHAR}");
    	
    	test(select(left(columnString1, 2).as("A_COLUMN1")).from(table, "a").where(left(columnString1, 3), isEqualTo("ABC")),
    		"select LEFT(a.columnString1, 2) as A_COLUMN1 from foo a where LEFT(a.columnString1, 3) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(left("text", columnInteger1).as("A_COLUMN1")).from(table, "a").where(left("text", columnInteger1), isEqualTo("ABC")),
        	"select LEFT('text', a.columnInteger1) as A_COLUMN1 from foo a where LEFT('text', a.columnInteger1) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(left("column", 2).as("A_COLUMN1")).from(table, "a").where(left("columnInteger1", 3), isEqualTo("ABC")),
        	"select LEFT('column', 2) as A_COLUMN1 from foo a where LEFT('columnInteger1', 3) = #{parameters.p1,jdbcType=VARCHAR}");
    }
    
    @Test
    public void testLen() {
        test(select(len(columnString1).as("A_COLUMN1")).from(table, "a").where(len(columnString1), isEqualTo(0)),
        	"select LEN(a.columnString1) as A_COLUMN1 from foo a where LEN(a.columnString1) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(len("column").as("A_COLUMN1")).from(table, "a").where(len("columnInteger1"), isEqualTo(0)),
        	"select LEN('column') as A_COLUMN1 from foo a where LEN('columnInteger1') = #{parameters.p1,jdbcType=INTEGER}");
    }
    
    @Test
    public void testLower() {
        test(select(lower(columnString1).as("A_COLUMN1")).from(table, "a").where(lower(columnString1), isEqualTo("ABC")),
        	"select LOWER(a.columnString1) as A_COLUMN1 from foo a where LOWER(a.columnString1) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(lower("column").as("A_COLUMN1")).from(table, "a").where(lower("columnInteger1"), isEqualTo("ABC")),
        	"select LOWER('column') as A_COLUMN1 from foo a where LOWER('columnInteger1') = #{parameters.p1,jdbcType=VARCHAR}");
    }
    
    @Test
    public void testLTrim() {
        test(select(ltrim(columnString1).as("A_COLUMN1")).from(table, "a").where(ltrim(columnString1), isEqualTo("ABC")),
        	"select LTRIM(a.columnString1) as A_COLUMN1 from foo a where LTRIM(a.columnString1) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(ltrim("column").as("A_COLUMN1")).from(table, "a").where(ltrim("columnInteger1"), isEqualTo("ABC")),
        	"select LTRIM('column') as A_COLUMN1 from foo a where LTRIM('columnInteger1') = #{parameters.p1,jdbcType=VARCHAR}");
    }
    
    @Test
    public void testNChar() {
        test(select(nchar(columnInteger1).as("A_COLUMN2")).from(table, "a").where(nchar(columnInteger1), isEqualTo("A")),
        	"select NCHAR(a.columnInteger1) as A_COLUMN2 from foo a where NCHAR(a.columnInteger1) = #{parameters.p1,jdbcType=NVARCHAR}");
        
        test(select(nchar(1).as("A_COLUMN")).from(table, "a").where(nchar(2), isEqualTo("A")),
        	"select NCHAR(1) as A_COLUMN from foo a where NCHAR(2) = #{parameters.p1,jdbcType=NVARCHAR}");
    }
    
    @Test
    public void testPatIndex() {
    	test(select(patindex(columnString1, columnString2).as("A_COLUMN1")).from(table, "a").where(patindex(columnString1, columnString2), isEqualTo(0)),
    		"select PATINDEX(a.columnString1, a.columnString2) as A_COLUMN1 from foo a where PATINDEX(a.columnString1, a.columnString2) = #{parameters.p1,jdbcType=INTEGER}");
    	
    	test(select(patindex("pattern", columnString1).as("A_COLUMN1")).from(table, "a").where(patindex("pattern2", columnString1), isEqualTo(0)),
    		"select PATINDEX('pattern', a.columnString1) as A_COLUMN1 from foo a where PATINDEX('pattern2', a.columnString1) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(patindex(columnString1, "text").as("A_COLUMN1")).from(table, "a").where(patindex(columnString2, "text"), isEqualTo(0)),
        	"select PATINDEX(a.columnString1, 'text') as A_COLUMN1 from foo a where PATINDEX(a.columnString2, 'text') = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(patindex("pattern", "column").as("A_COLUMN1")).from(table, "a").where(patindex("pattern2", "columnInteger1"), isEqualTo(0)),
        	"select PATINDEX('pattern', 'column') as A_COLUMN1 from foo a where PATINDEX('pattern2', 'columnInteger1') = #{parameters.p1,jdbcType=INTEGER}");
    }
    
    @Test
    public void testReplace() {
    	test(select(replace(columnString1, columnString2, columnString3).as("A_COLUMN1")).from(table, "a").where(replace(columnString1, columnString2, columnString3), isEqualTo("column")),
    		"select REPLACE(a.columnString1, a.columnString2, a.columnString3) as A_COLUMN1 from foo a where REPLACE(a.columnString1, a.columnString2, a.columnString3) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(replace(columnString1, columnString2, "test").as("A_COLUMN1")).from(table, "a").where(replace(columnString1, columnString2, "test"), isEqualTo("column")),
        	"select REPLACE(a.columnString1, a.columnString2, 'test') as A_COLUMN1 from foo a where REPLACE(a.columnString1, a.columnString2, 'test') = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(replace(columnString1, "test", columnString2).as("A_COLUMN1")).from(table, "a").where(replace(columnString1, "test", columnString2), isEqualTo("column")),
        	"select REPLACE(a.columnString1, 'test', a.columnString2) as A_COLUMN1 from foo a where REPLACE(a.columnString1, 'test', a.columnString2) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(replace("column", columnString2, columnString3).as("A_COLUMN1")).from(table, "a").where(replace("column", columnString2, columnString3), isEqualTo("column")),
        	"select REPLACE('column', a.columnString2, a.columnString3) as A_COLUMN1 from foo a where REPLACE('column', a.columnString2, a.columnString3) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(replace("column", columnString2, "test").as("A_COLUMN1")).from(table, "a").where(replace("column", columnString2, "test"), isEqualTo("column")),
        	"select REPLACE('column', a.columnString2, 'test') as A_COLUMN1 from foo a where REPLACE('column', a.columnString2, 'test') = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(replace("column", "test", columnString2).as("A_COLUMN1")).from(table, "a").where(replace("column", "test", columnString2), isEqualTo("column")),
        	"select REPLACE('column', 'test', a.columnString2) as A_COLUMN1 from foo a where REPLACE('column', 'test', a.columnString2) = #{parameters.p1,jdbcType=VARCHAR}");
        
    	test(select(replace(columnString1, "textToReplace", "textReplaced").as("A_COLUMN1")).from(table, "a").where(replace(columnString1, "textToReplace", "textReplaced"), isEqualTo("ABC")),
    		"select REPLACE(a.columnString1, 'textToReplace', 'textReplaced') as A_COLUMN1 from foo a where REPLACE(a.columnString1, 'textToReplace', 'textReplaced') = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(replace("column", "textToReplace", "textReplaced").as("A_COLUMN1")).from(table, "a").where(replace("columnInteger1", "textToReplace", "textReplaced"), isEqualTo("ABC")),
        	"select REPLACE('column', 'textToReplace', 'textReplaced') as A_COLUMN1 from foo a where REPLACE('columnInteger1', 'textToReplace', 'textReplaced') = #{parameters.p1,jdbcType=VARCHAR}");
    }
    
    @Test
    public void testRight() {
    	test(select(right(columnString1, columnInteger1).as("A_COLUMN1")).from(table, "a").where(right(columnString1, columnInteger1), isEqualTo("ABC")),
    		"select RIGHT(a.columnString1, a.columnInteger1) as A_COLUMN1 from foo a where RIGHT(a.columnString1, a.columnInteger1) = #{parameters.p1,jdbcType=VARCHAR}");
    	
    	test(select(right(columnString1, 2).as("A_COLUMN1")).from(table, "a").where(right(columnString1, 3), isEqualTo("ABC")),
    		"select RIGHT(a.columnString1, 2) as A_COLUMN1 from foo a where RIGHT(a.columnString1, 3) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(right("text", columnInteger1).as("A_COLUMN1")).from(table, "a").where(right("text", columnInteger1), isEqualTo("ABC")),
        	"select RIGHT('text', a.columnInteger1) as A_COLUMN1 from foo a where RIGHT('text', a.columnInteger1) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(right("column", 2).as("A_COLUMN1")).from(table, "a").where(right("columnInteger1", 3), isEqualTo("ABC")),
        	"select RIGHT('column', 2) as A_COLUMN1 from foo a where RIGHT('columnInteger1', 3) = #{parameters.p1,jdbcType=VARCHAR}");
    }
    
    @Test
    public void testRTrim() {
        test(select(rtrim(columnString1).as("A_COLUMN1")).from(table, "a").where(rtrim(columnString1), isEqualTo("ABC")),
        	"select RTRIM(a.columnString1) as A_COLUMN1 from foo a where RTRIM(a.columnString1) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(rtrim("column").as("A_COLUMN1")).from(table, "a").where(rtrim("columnInteger1"), isEqualTo("ABC")), 
        	"select RTRIM('column') as A_COLUMN1 from foo a where RTRIM('columnInteger1') = #{parameters.p1,jdbcType=VARCHAR}");
    }
    
    @Test
    public void testSpace() {
        test(select(space(columnInteger1).as("A_COLUMN2")).from(table, "a").where(space(columnInteger1), isEqualTo("ABC")),
        	"select SPACE(a.columnInteger1) as A_COLUMN2 from foo a where SPACE(a.columnInteger1) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(space(1).as("A_COLUMN1")).from(table, "a").where(space(1), isEqualTo("ABC")),
        	"select SPACE(1) as A_COLUMN1 from foo a where SPACE(1) = #{parameters.p1,jdbcType=VARCHAR}");
    }
    
    @Test
    public void testStr() {
        test(select(str(columnInteger1).as("A_COLUMN2")).from(table, "a").where(str(columnInteger1), isEqualTo("ABC")),
        	"select STR(a.columnInteger1) as A_COLUMN2 from foo a where STR(a.columnInteger1) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(str(1).as("A_COLUMN1")).from(table, "a").where(str(1), isEqualTo("ABC")),
        	"select STR(1) as A_COLUMN1 from foo a where STR(1) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(str(columnInteger1, columnInteger2).as("A_COLUMN2")).from(table, "a").where(str(columnInteger1, columnInteger2), isEqualTo("ABC")),
            	"select STR(a.columnInteger1, a.columnInteger2) as A_COLUMN2 from foo a where STR(a.columnInteger1, a.columnInteger2) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(str(columnInteger1, 1).as("A_COLUMN2")).from(table, "a").where(str(columnInteger1, 1), isEqualTo("ABC")),
            	"select STR(a.columnInteger1, 1) as A_COLUMN2 from foo a where STR(a.columnInteger1, 1) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(str(1, columnInteger2).as("A_COLUMN2")).from(table, "a").where(str(1, columnInteger2), isEqualTo("ABC")),
            	"select STR(1, a.columnInteger2) as A_COLUMN2 from foo a where STR(1, a.columnInteger2) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(str(1, 2).as("A_COLUMN2")).from(table, "a").where(str(1, 2), isEqualTo("ABC")),
            	"select STR(1, 2) as A_COLUMN2 from foo a where STR(1, 2) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(str(columnInteger1, columnInteger2, columnInteger3).as("A_COLUMN2")).from(table, "a").where(str(columnInteger1, columnInteger2, columnInteger3), isEqualTo("ABC")),
            	"select STR(a.columnInteger1, a.columnInteger2, a.columnInteger3) as A_COLUMN2 from foo a where STR(a.columnInteger1, a.columnInteger2, a.columnInteger3) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(str(columnInteger1, columnInteger2, 1).as("A_COLUMN2")).from(table, "a").where(str(columnInteger1, columnInteger2, 1), isEqualTo("ABC")),
            	"select STR(a.columnInteger1, a.columnInteger2, 1) as A_COLUMN2 from foo a where STR(a.columnInteger1, a.columnInteger2, 1) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(str(columnInteger1, 1, columnInteger3).as("A_COLUMN2")).from(table, "a").where(str(columnInteger1, 1, columnInteger3), isEqualTo("ABC")),
            	"select STR(a.columnInteger1, 1, a.columnInteger3) as A_COLUMN2 from foo a where STR(a.columnInteger1, 1, a.columnInteger3) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(str(columnInteger1, 1, 2).as("A_COLUMN2")).from(table, "a").where(str(columnInteger1, 1, 2), isEqualTo("ABC")),
            	"select STR(a.columnInteger1, 1, 2) as A_COLUMN2 from foo a where STR(a.columnInteger1, 1, 2) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(str(1, columnInteger2, columnInteger3).as("A_COLUMN2")).from(table, "a").where(str(1, columnInteger2, columnInteger3), isEqualTo("ABC")),
            	"select STR(1, a.columnInteger2, a.columnInteger3) as A_COLUMN2 from foo a where STR(1, a.columnInteger2, a.columnInteger3) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(str(1, columnInteger2, 3).as("A_COLUMN2")).from(table, "a").where(str(1, columnInteger2, 3), isEqualTo("ABC")),
            	"select STR(1, a.columnInteger2, 3) as A_COLUMN2 from foo a where STR(1, a.columnInteger2, 3) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(str(1, 2, columnInteger3).as("A_COLUMN2")).from(table, "a").where(str(1, 2, columnInteger3), isEqualTo("ABC")),
            	"select STR(1, 2, a.columnInteger3) as A_COLUMN2 from foo a where STR(1, 2, a.columnInteger3) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(str(1, 2, 3).as("A_COLUMN2")).from(table, "a").where(str(1, 2, 3), isEqualTo("ABC")),
            	"select STR(1, 2, 3) as A_COLUMN2 from foo a where STR(1, 2, 3) = #{parameters.p1,jdbcType=VARCHAR}");
    }
    
    @Test
    public void testStuff() {
        test(select(stuff(columnString1, columnInteger1, columnInteger2, columnString2).as("A_COLUMN2")).from(table, "a").where(stuff(columnString1, columnInteger1, columnInteger2, columnString2), isEqualTo("ABC")),
        	"select STUFF(a.columnString1, a.columnInteger1, a.columnInteger2, a.columnString2) as A_COLUMN2 from foo a where STUFF(a.columnString1, a.columnInteger1, a.columnInteger2, a.columnString2) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(stuff(columnString1, columnInteger1, columnInteger2, "test").as("A_COLUMN2")).from(table, "a").where(stuff(columnString1, columnInteger1, columnInteger2, "test"), isEqualTo("ABC")),
            	"select STUFF(a.columnString1, a.columnInteger1, a.columnInteger2, 'test') as A_COLUMN2 from foo a where STUFF(a.columnString1, a.columnInteger1, a.columnInteger2, 'test') = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(stuff(columnString1, columnInteger1, 1, columnString2).as("A_COLUMN2")).from(table, "a").where(stuff(columnString1, columnInteger1, 1, columnString2), isEqualTo("ABC")),
            	"select STUFF(a.columnString1, a.columnInteger1, 1, a.columnString2) as A_COLUMN2 from foo a where STUFF(a.columnString1, a.columnInteger1, 1, a.columnString2) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(stuff(columnString1, columnInteger1, 1, "test").as("A_COLUMN2")).from(table, "a").where(stuff(columnString1, columnInteger1, 1, "test"), isEqualTo("ABC")),
            	"select STUFF(a.columnString1, a.columnInteger1, 1, 'test') as A_COLUMN2 from foo a where STUFF(a.columnString1, a.columnInteger1, 1, 'test') = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(stuff(columnString1, 1, columnInteger2, columnString2).as("A_COLUMN2")).from(table, "a").where(stuff(columnString1, 1, columnInteger2, columnString2), isEqualTo("ABC")),
            	"select STUFF(a.columnString1, 1, a.columnInteger2, a.columnString2) as A_COLUMN2 from foo a where STUFF(a.columnString1, 1, a.columnInteger2, a.columnString2) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(stuff(columnString1, 1, columnInteger2, "test").as("A_COLUMN2")).from(table, "a").where(stuff(columnString1, 1, columnInteger2, "test"), isEqualTo("ABC")),
            	"select STUFF(a.columnString1, 1, a.columnInteger2, 'test') as A_COLUMN2 from foo a where STUFF(a.columnString1, 1, a.columnInteger2, 'test') = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(stuff(columnString1, 1, 2, columnString2).as("A_COLUMN2")).from(table, "a").where(stuff(columnString1, 1, 2, columnString2), isEqualTo("ABC")),
            	"select STUFF(a.columnString1, 1, 2, a.columnString2) as A_COLUMN2 from foo a where STUFF(a.columnString1, 1, 2, a.columnString2) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(stuff(columnString1, 1, 2, "test").as("A_COLUMN2")).from(table, "a").where(stuff(columnString1, 1, 2, "test"), isEqualTo("ABC")),
            	"select STUFF(a.columnString1, 1, 2, 'test') as A_COLUMN2 from foo a where STUFF(a.columnString1, 1, 2, 'test') = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(stuff("test", columnInteger1, columnInteger2, columnString2).as("A_COLUMN2")).from(table, "a").where(stuff("test", columnInteger1, columnInteger2, columnString2), isEqualTo("ABC")),
            	"select STUFF('test', a.columnInteger1, a.columnInteger2, a.columnString2) as A_COLUMN2 from foo a where STUFF('test', a.columnInteger1, a.columnInteger2, a.columnString2) = #{parameters.p1,jdbcType=VARCHAR}");
            
        test(select(stuff("test", columnInteger1, columnInteger2, "test").as("A_COLUMN2")).from(table, "a").where(stuff("test", columnInteger1, columnInteger2, "test"), isEqualTo("ABC")),
            	"select STUFF('test', a.columnInteger1, a.columnInteger2, 'test') as A_COLUMN2 from foo a where STUFF('test', a.columnInteger1, a.columnInteger2, 'test') = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(stuff("test", columnInteger1, 1, columnString2).as("A_COLUMN2")).from(table, "a").where(stuff("test", columnInteger1, 1, columnString2), isEqualTo("ABC")),
            	"select STUFF('test', a.columnInteger1, 1, a.columnString2) as A_COLUMN2 from foo a where STUFF('test', a.columnInteger1, 1, a.columnString2) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(stuff("test", columnInteger1, 1, "test").as("A_COLUMN2")).from(table, "a").where(stuff("test", columnInteger1, 1, "test"), isEqualTo("ABC")),
            	"select STUFF('test', a.columnInteger1, 1, 'test') as A_COLUMN2 from foo a where STUFF('test', a.columnInteger1, 1, 'test') = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(stuff("test", 1, columnInteger2, columnString2).as("A_COLUMN2")).from(table, "a").where(stuff("test", 1, columnInteger2, columnString2), isEqualTo("ABC")),
            	"select STUFF('test', 1, a.columnInteger2, a.columnString2) as A_COLUMN2 from foo a where STUFF('test', 1, a.columnInteger2, a.columnString2) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(stuff("test", 1, columnInteger2, "test").as("A_COLUMN2")).from(table, "a").where(stuff("test", 1, columnInteger2, "test"), isEqualTo("ABC")),
            	"select STUFF('test', 1, a.columnInteger2, 'test') as A_COLUMN2 from foo a where STUFF('test', 1, a.columnInteger2, 'test') = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(stuff("test", 1, 2, columnString2).as("A_COLUMN2")).from(table, "a").where(stuff("test", 1, 2, columnString2), isEqualTo("ABC")),
            	"select STUFF('test', 1, 2, a.columnString2) as A_COLUMN2 from foo a where STUFF('test', 1, 2, a.columnString2) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(stuff("test", 1, 2, "test").as("A_COLUMN2")).from(table, "a").where(stuff("test", 1, 2, "test"), isEqualTo("ABC")),
            	"select STUFF('test', 1, 2, 'test') as A_COLUMN2 from foo a where STUFF('test', 1, 2, 'test') = #{parameters.p1,jdbcType=VARCHAR}");
    }
    
    @Test
    public void testSubstring() {
        test(select(substring(columnString1, columnInteger1, columnInteger2).as("A_COLUMN2")).from(table, "a").where(substring(columnString1, columnInteger1, columnInteger2), isEqualTo("ABC")),
        	"select SUBSTRING(a.columnString1, a.columnInteger1, a.columnInteger2) as A_COLUMN2 from foo a where SUBSTRING(a.columnString1, a.columnInteger1, a.columnInteger2) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(substring(columnString1, columnInteger1, 1).as("A_COLUMN2")).from(table, "a").where(substring(columnString1, columnInteger1, 1), isEqualTo("ABC")),
            	"select SUBSTRING(a.columnString1, a.columnInteger1, 1) as A_COLUMN2 from foo a where SUBSTRING(a.columnString1, a.columnInteger1, 1) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(substring(columnString1, 1, columnInteger2).as("A_COLUMN2")).from(table, "a").where(substring(columnString1, 1, columnInteger2), isEqualTo("ABC")),
            	"select SUBSTRING(a.columnString1, 1, a.columnInteger2) as A_COLUMN2 from foo a where SUBSTRING(a.columnString1, 1, a.columnInteger2) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(substring(columnString1, 1, 2).as("A_COLUMN2")).from(table, "a").where(substring(columnString1, 1, 2), isEqualTo("ABC")),
            	"select SUBSTRING(a.columnString1, 1, 2) as A_COLUMN2 from foo a where SUBSTRING(a.columnString1, 1, 2) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(substring("test", columnInteger1, columnInteger2).as("A_COLUMN2")).from(table, "a").where(substring("test", columnInteger1, columnInteger2), isEqualTo("ABC")),
            	"select SUBSTRING('test', a.columnInteger1, a.columnInteger2) as A_COLUMN2 from foo a where SUBSTRING('test', a.columnInteger1, a.columnInteger2) = #{parameters.p1,jdbcType=VARCHAR}");
            
        test(select(substring("test", columnInteger1, 1).as("A_COLUMN2")).from(table, "a").where(substring("test", columnInteger1, 1), isEqualTo("ABC")),
            	"select SUBSTRING('test', a.columnInteger1, 1) as A_COLUMN2 from foo a where SUBSTRING('test', a.columnInteger1, 1) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(substring("test", 1, columnInteger2).as("A_COLUMN2")).from(table, "a").where(substring("test", 1, columnInteger2), isEqualTo("ABC")),
            	"select SUBSTRING('test', 1, a.columnInteger2) as A_COLUMN2 from foo a where SUBSTRING('test', 1, a.columnInteger2) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(substring("test", 1, 2).as("A_COLUMN2")).from(table, "a").where(substring("test", 1, 2), isEqualTo("ABC")),
            	"select SUBSTRING('test', 1, 2) as A_COLUMN2 from foo a where SUBSTRING('test', 1, 2) = #{parameters.p1,jdbcType=VARCHAR}");
    }
    
    @Test
    public void testUpper() {
        test(select(upper(columnString1).as("A_COLUMN1")).from(table, "a").where(upper(columnString1), isEqualTo("ABC")),
        	"select UPPER(a.columnString1) as A_COLUMN1 from foo a where UPPER(a.columnString1) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(upper("column").as("A_COLUMN1")).from(table, "a").where(upper("columnInteger1"), isEqualTo("ABC")),
        	"select UPPER('column') as A_COLUMN1 from foo a where UPPER('columnInteger1') = #{parameters.p1,jdbcType=VARCHAR}");
    }
    
    
    @SuppressWarnings("rawtypes")
	protected void test(QueryExpressionWhereBuilder selectModel, String sql) {
    	SelectStatementProvider selectStatement = ((SelectModel) selectModel.build()).render(RenderingStrategy.MYBATIS3);
    	SoftAssertions.assertSoftly(softly -> softly.assertThat(selectStatement.getSelectStatement()).isEqualTo(sql));
    }
}
