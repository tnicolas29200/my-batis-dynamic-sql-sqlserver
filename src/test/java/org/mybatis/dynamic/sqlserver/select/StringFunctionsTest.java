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
import static org.mybatis.dynamic.sqlserver.SQLServerBuilder.space;
import static org.mybatis.dynamic.sqlserver.SQLServerBuilder.str;
import static org.mybatis.dynamic.sqlserver.SQLServerBuilder.stuff;
import static org.mybatis.dynamic.sqlserver.SQLServerBuilder.substring;
import static org.mybatis.dynamic.sqlserver.SQLServerBuilder.upper;

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
    public static final SqlColumn<String> column1 = table.column("column1", JDBCType.VARCHAR);
    public static final SqlColumn<Integer> column2 = table.column("column2", JDBCType.INTEGER);
    public static final SqlColumn<String> column3 = table.column("column3", JDBCType.VARCHAR);
    public static final SqlColumn<String> column4 = table.column("column4", JDBCType.VARCHAR);
    public static final SqlColumn<Integer> column5 = table.column("column5", JDBCType.INTEGER);
    public static final SqlColumn<Integer> column6 = table.column("column6", JDBCType.INTEGER);
    
    @Test
    public void testAscii() {
        test(select(ascii(column1).as("A_COLUMN1")).from(table, "a").where(ascii(column1), isEqualTo(1)),
        	"select ASCII(a.column1) as A_COLUMN1 from foo a where ASCII(a.column1) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(ascii("1").as("A_COLUMN1")).from(table, "a").where(ascii("2"), isEqualTo(1)),
        	"select ASCII('1') as A_COLUMN1 from foo a where ASCII('2') = #{parameters.p1,jdbcType=INTEGER}");
    }
    
    @Test
    public void testCharIndex() {
        test(select(charindex(column1, column3).as("A_COLUMN1")).from(table, "a").where(charindex(column1, column3), isEqualTo(1)),
        	"select CHARINDEX(a.column1, a.column3) as A_COLUMN1 from foo a where CHARINDEX(a.column1, a.column3) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(charindex(column1, "test").as("A_COLUMN1")).from(table, "a").where(charindex(column1, "test"), isEqualTo(1)),
        	"select CHARINDEX(a.column1, 'test') as A_COLUMN1 from foo a where CHARINDEX(a.column1, 'test') = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(charindex("test3", column3).as("A_COLUMN1")).from(table, "a").where(charindex("test", column3), isEqualTo(1)),
        	"select CHARINDEX('test3', a.column3) as A_COLUMN1 from foo a where CHARINDEX('test', a.column3) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(charindex("test string", "test").as("A_COLUMN1")).from(table, "a").where(charindex("string test", "test"), isEqualTo(1)),
        	"select CHARINDEX('test string', 'test') as A_COLUMN1 from foo a where CHARINDEX('string test', 'test') = #{parameters.p1,jdbcType=INTEGER}");
        
        
        test(select(charindex(column1, column3, column2).as("A_COLUMN1")).from(table, "a").where(charindex(column1, column3, column2), isEqualTo(1)),
        	"select CHARINDEX(a.column1, a.column3, a.column2) as A_COLUMN1 from foo a where CHARINDEX(a.column1, a.column3, a.column2) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(charindex(column1, column3, 1).as("A_COLUMN1")).from(table, "a").where(charindex(column1, column3, 2), isEqualTo(1)),
        	"select CHARINDEX(a.column1, a.column3, 1) as A_COLUMN1 from foo a where CHARINDEX(a.column1, a.column3, 2) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(charindex(column1, "test", column2).as("A_COLUMN1")).from(table, "a").where(charindex(column1, "test", column2), isEqualTo(1)),
        	"select CHARINDEX(a.column1, 'test', a.column2) as A_COLUMN1 from foo a where CHARINDEX(a.column1, 'test', a.column2) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(charindex(column1, "test", 1).as("A_COLUMN1")).from(table, "a").where(charindex(column1, "test", 1), isEqualTo(1)),
        	"select CHARINDEX(a.column1, 'test', 1) as A_COLUMN1 from foo a where CHARINDEX(a.column1, 'test', 1) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(charindex("test", column3, column2).as("A_COLUMN1")).from(table, "a").where(charindex("test", column3, column2), isEqualTo(1)),
        	"select CHARINDEX('test', a.column3, a.column2) as A_COLUMN1 from foo a where CHARINDEX('test', a.column3, a.column2) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(charindex("test", column3, 1).as("A_COLUMN1")).from(table, "a").where(charindex("test", column3, 2), isEqualTo(1)),
        	"select CHARINDEX('test', a.column3, 1) as A_COLUMN1 from foo a where CHARINDEX('test', a.column3, 2) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(charindex("test", "test", column2).as("A_COLUMN1")).from(table, "a").where(charindex("test", "test", column2), isEqualTo(1)),
        	"select CHARINDEX('test', 'test', a.column2) as A_COLUMN1 from foo a where CHARINDEX('test', 'test', a.column2) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(charindex("test", "test", 1).as("A_COLUMN1")).from(table, "a").where(charindex("test", "test", 1), isEqualTo(1)),
        	"select CHARINDEX('test', 'test', 1) as A_COLUMN1 from foo a where CHARINDEX('test', 'test', 1) = #{parameters.p1,jdbcType=INTEGER}");
    }
    
    @Test
    public void testChar() {
        test(select(character(column2).as("A_COLUMN2")).from(table, "a").where(character(column2), isEqualTo("A")),
        	"select CHAR(a.column2) as A_COLUMN2 from foo a where CHAR(a.column2) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(character(1).as("A_COLUMN2")).from(table, "a").where(character(2), isEqualTo("A")),
        	"select CHAR(1) as A_COLUMN2 from foo a where CHAR(2) = #{parameters.p1,jdbcType=VARCHAR}");
    }
	
    @Test
    public void testConcat() {
        test(select(concat(column1, column3, column4).as("COLUMNS")).from(table, "a").where(concat(column1, column3), isEqualTo("A")),
        	"select CONCAT(a.column1, a.column3, a.column4) as COLUMNS from foo a where CONCAT(a.column1, a.column3) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(concat(column1, column3, "test").as("COLUMNS")).from(table, "a").where(concat(column1, column3, "test"), isEqualTo("A")),
            	"select CONCAT(a.column1, a.column3, 'test') as COLUMNS from foo a where CONCAT(a.column1, a.column3, 'test') = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(concat(column1, column3, "test", column4).as("COLUMNS")).from(table, "a").where(concat(column1, column3, "test", column4), isEqualTo("A")),
            	"select CONCAT(a.column1, a.column3, 'test', a.column4) as COLUMNS from foo a where CONCAT(a.column1, a.column3, 'test', a.column4) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(concat("column1", "column3", "column4").as("COLUMNS")).from(table, "a").where(concat("column1", "column3"), isEqualTo("A")),
        	"select CONCAT('column1', 'column3', 'column4') as COLUMNS from foo a where CONCAT('column1', 'column3') = #{parameters.p1,jdbcType=VARCHAR}");
    }
    
    @Test
    public void testDataLength() {
        test(select(datalength(column1).as("A_COLUMN1")).from(table, "a").where(datalength(column1), isEqualTo(0)),
        	"select DATALENGTH(a.column1) as A_COLUMN1 from foo a where DATALENGTH(a.column1) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(datalength("column").as("A_COLUMN1")).from(table, "a").where(datalength("column2"), isEqualTo(0)),
        	"select DATALENGTH('column') as A_COLUMN1 from foo a where DATALENGTH('column2') = #{parameters.p1,jdbcType=INTEGER}");
    }
    
    @Test
    public void testLeft() {
    	test(select(left(column1, column2).as("A_COLUMN1")).from(table, "a").where(left(column1, column2), isEqualTo("ABC")),
    		"select LEFT(a.column1, a.column2) as A_COLUMN1 from foo a where LEFT(a.column1, a.column2) = #{parameters.p1,jdbcType=VARCHAR}");
    	
    	test(select(left(column1, 2).as("A_COLUMN1")).from(table, "a").where(left(column1, 3), isEqualTo("ABC")),
    		"select LEFT(a.column1, 2) as A_COLUMN1 from foo a where LEFT(a.column1, 3) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(left("text", column2).as("A_COLUMN1")).from(table, "a").where(left("text", column2), isEqualTo("ABC")),
        	"select LEFT('text', a.column2) as A_COLUMN1 from foo a where LEFT('text', a.column2) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(left("column", 2).as("A_COLUMN1")).from(table, "a").where(left("column2", 3), isEqualTo("ABC")),
        	"select LEFT('column', 2) as A_COLUMN1 from foo a where LEFT('column2', 3) = #{parameters.p1,jdbcType=VARCHAR}");
    }
    
    @Test
    public void testLen() {
        test(select(len(column1).as("A_COLUMN1")).from(table, "a").where(len(column1), isEqualTo(0)),
        	"select LEN(a.column1) as A_COLUMN1 from foo a where LEN(a.column1) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(len("column").as("A_COLUMN1")).from(table, "a").where(len("column2"), isEqualTo(0)),
        	"select LEN('column') as A_COLUMN1 from foo a where LEN('column2') = #{parameters.p1,jdbcType=INTEGER}");
    }
    
    @Test
    public void testLower() {
        test(select(lower(column1).as("A_COLUMN1")).from(table, "a").where(lower(column1), isEqualTo("ABC")),
        	"select LOWER(a.column1) as A_COLUMN1 from foo a where LOWER(a.column1) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(lower("column").as("A_COLUMN1")).from(table, "a").where(lower("column2"), isEqualTo("ABC")),
        	"select LOWER('column') as A_COLUMN1 from foo a where LOWER('column2') = #{parameters.p1,jdbcType=VARCHAR}");
    }
    
    @Test
    public void testLTrim() {
        test(select(ltrim(column1).as("A_COLUMN1")).from(table, "a").where(ltrim(column1), isEqualTo("ABC")),
        	"select LTRIM(a.column1) as A_COLUMN1 from foo a where LTRIM(a.column1) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(ltrim("column").as("A_COLUMN1")).from(table, "a").where(ltrim("column2"), isEqualTo("ABC")),
        	"select LTRIM('column') as A_COLUMN1 from foo a where LTRIM('column2') = #{parameters.p1,jdbcType=VARCHAR}");
    }
    
    @Test
    public void testNChar() {
        test(select(nchar(column2).as("A_COLUMN2")).from(table, "a").where(nchar(column2), isEqualTo("A")),
        	"select NCHAR(a.column2) as A_COLUMN2 from foo a where NCHAR(a.column2) = #{parameters.p1,jdbcType=NVARCHAR}");
        
        test(select(nchar(1).as("A_COLUMN")).from(table, "a").where(nchar(2), isEqualTo("A")),
        	"select NCHAR(1) as A_COLUMN from foo a where NCHAR(2) = #{parameters.p1,jdbcType=NVARCHAR}");
    }
    
    @Test
    public void testPatIndex() {
    	test(select(patindex(column1, column3).as("A_COLUMN1")).from(table, "a").where(patindex(column1, column3), isEqualTo(0)),
    		"select PATINDEX(a.column1, a.column3) as A_COLUMN1 from foo a where PATINDEX(a.column1, a.column3) = #{parameters.p1,jdbcType=INTEGER}");
    	
    	test(select(patindex("pattern", column1).as("A_COLUMN1")).from(table, "a").where(patindex("pattern2", column1), isEqualTo(0)),
    		"select PATINDEX('pattern', a.column1) as A_COLUMN1 from foo a where PATINDEX('pattern2', a.column1) = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(patindex(column1, "text").as("A_COLUMN1")).from(table, "a").where(patindex(column3, "text"), isEqualTo(0)),
        	"select PATINDEX(a.column1, 'text') as A_COLUMN1 from foo a where PATINDEX(a.column3, 'text') = #{parameters.p1,jdbcType=INTEGER}");
        
        test(select(patindex("pattern", "column").as("A_COLUMN1")).from(table, "a").where(patindex("pattern2", "column2"), isEqualTo(0)),
        	"select PATINDEX('pattern', 'column') as A_COLUMN1 from foo a where PATINDEX('pattern2', 'column2') = #{parameters.p1,jdbcType=INTEGER}");
    }
    
    @Test
    public void testReplace() {
    	test(select(replace(column1, column3, column4).as("A_COLUMN1")).from(table, "a").where(replace(column1, column3, column4), isEqualTo("column")),
    		"select REPLACE(a.column1, a.column3, a.column4) as A_COLUMN1 from foo a where REPLACE(a.column1, a.column3, a.column4) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(replace(column1, column3, "test").as("A_COLUMN1")).from(table, "a").where(replace(column1, column3, "test"), isEqualTo("column")),
        	"select REPLACE(a.column1, a.column3, 'test') as A_COLUMN1 from foo a where REPLACE(a.column1, a.column3, 'test') = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(replace(column1, "test", column3).as("A_COLUMN1")).from(table, "a").where(replace(column1, "test", column3), isEqualTo("column")),
        	"select REPLACE(a.column1, 'test', a.column3) as A_COLUMN1 from foo a where REPLACE(a.column1, 'test', a.column3) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(replace("column", column3, column4).as("A_COLUMN1")).from(table, "a").where(replace("column", column3, column4), isEqualTo("column")),
        	"select REPLACE('column', a.column3, a.column4) as A_COLUMN1 from foo a where REPLACE('column', a.column3, a.column4) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(replace("column", column3, "test").as("A_COLUMN1")).from(table, "a").where(replace("column", column3, "test"), isEqualTo("column")),
        	"select REPLACE('column', a.column3, 'test') as A_COLUMN1 from foo a where REPLACE('column', a.column3, 'test') = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(replace("column", "test", column3).as("A_COLUMN1")).from(table, "a").where(replace("column", "test", column3), isEqualTo("column")),
        	"select REPLACE('column', 'test', a.column3) as A_COLUMN1 from foo a where REPLACE('column', 'test', a.column3) = #{parameters.p1,jdbcType=VARCHAR}");
        
    	test(select(replace(column1, "textToReplace", "textReplaced").as("A_COLUMN1")).from(table, "a").where(replace(column1, "textToReplace", "textReplaced"), isEqualTo("ABC")),
    		"select REPLACE(a.column1, 'textToReplace', 'textReplaced') as A_COLUMN1 from foo a where REPLACE(a.column1, 'textToReplace', 'textReplaced') = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(replace("column", "textToReplace", "textReplaced").as("A_COLUMN1")).from(table, "a").where(replace("column2", "textToReplace", "textReplaced"), isEqualTo("ABC")),
        	"select REPLACE('column', 'textToReplace', 'textReplaced') as A_COLUMN1 from foo a where REPLACE('column2', 'textToReplace', 'textReplaced') = #{parameters.p1,jdbcType=VARCHAR}");
    }
    
    @Test
    public void testRight() {
    	test(select(right(column1, column2).as("A_COLUMN1")).from(table, "a").where(right(column1, column2), isEqualTo("ABC")),
    		"select RIGHT(a.column1, a.column2) as A_COLUMN1 from foo a where RIGHT(a.column1, a.column2) = #{parameters.p1,jdbcType=VARCHAR}");
    	
    	test(select(right(column1, 2).as("A_COLUMN1")).from(table, "a").where(right(column1, 3), isEqualTo("ABC")),
    		"select RIGHT(a.column1, 2) as A_COLUMN1 from foo a where RIGHT(a.column1, 3) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(right("text", column2).as("A_COLUMN1")).from(table, "a").where(right("text", column2), isEqualTo("ABC")),
        	"select RIGHT('text', a.column2) as A_COLUMN1 from foo a where RIGHT('text', a.column2) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(right("column", 2).as("A_COLUMN1")).from(table, "a").where(right("column2", 3), isEqualTo("ABC")),
        	"select RIGHT('column', 2) as A_COLUMN1 from foo a where RIGHT('column2', 3) = #{parameters.p1,jdbcType=VARCHAR}");
    }
    
    @Test
    public void testRTrim() {
        test(select(rtrim(column1).as("A_COLUMN1")).from(table, "a").where(rtrim(column1), isEqualTo("ABC")),
        	"select RTRIM(a.column1) as A_COLUMN1 from foo a where RTRIM(a.column1) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(rtrim("column").as("A_COLUMN1")).from(table, "a").where(rtrim("column2"), isEqualTo("ABC")), 
        	"select RTRIM('column') as A_COLUMN1 from foo a where RTRIM('column2') = #{parameters.p1,jdbcType=VARCHAR}");
    }
    
    @Test
    public void testSpace() {
        test(select(space(column2).as("A_COLUMN2")).from(table, "a").where(space(column2), isEqualTo("ABC")),
        	"select SPACE(a.column2) as A_COLUMN2 from foo a where SPACE(a.column2) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(space(1).as("A_COLUMN1")).from(table, "a").where(space(1), isEqualTo("ABC")),
        	"select SPACE(1) as A_COLUMN1 from foo a where SPACE(1) = #{parameters.p1,jdbcType=VARCHAR}");
    }
    
    @Test
    public void testStr() {
        test(select(str(column2).as("A_COLUMN2")).from(table, "a").where(str(column2), isEqualTo("ABC")),
        	"select STR(a.column2) as A_COLUMN2 from foo a where STR(a.column2) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(str(1).as("A_COLUMN1")).from(table, "a").where(str(1), isEqualTo("ABC")),
        	"select STR(1) as A_COLUMN1 from foo a where STR(1) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(str(column2, column5).as("A_COLUMN2")).from(table, "a").where(str(column2, column5), isEqualTo("ABC")),
            	"select STR(a.column2, a.column5) as A_COLUMN2 from foo a where STR(a.column2, a.column5) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(str(column2, 1).as("A_COLUMN2")).from(table, "a").where(str(column2, 1), isEqualTo("ABC")),
            	"select STR(a.column2, 1) as A_COLUMN2 from foo a where STR(a.column2, 1) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(str(1, column5).as("A_COLUMN2")).from(table, "a").where(str(1, column5), isEqualTo("ABC")),
            	"select STR(1, a.column5) as A_COLUMN2 from foo a where STR(1, a.column5) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(str(1, 2).as("A_COLUMN2")).from(table, "a").where(str(1, 2), isEqualTo("ABC")),
            	"select STR(1, 2) as A_COLUMN2 from foo a where STR(1, 2) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(str(column2, column5, column6).as("A_COLUMN2")).from(table, "a").where(str(column2, column5, column6), isEqualTo("ABC")),
            	"select STR(a.column2, a.column5, a.column6) as A_COLUMN2 from foo a where STR(a.column2, a.column5, a.column6) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(str(column2, column5, 1).as("A_COLUMN2")).from(table, "a").where(str(column2, column5, 1), isEqualTo("ABC")),
            	"select STR(a.column2, a.column5, 1) as A_COLUMN2 from foo a where STR(a.column2, a.column5, 1) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(str(column2, 1, column6).as("A_COLUMN2")).from(table, "a").where(str(column2, 1, column6), isEqualTo("ABC")),
            	"select STR(a.column2, 1, a.column6) as A_COLUMN2 from foo a where STR(a.column2, 1, a.column6) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(str(column2, 1, 2).as("A_COLUMN2")).from(table, "a").where(str(column2, 1, 2), isEqualTo("ABC")),
            	"select STR(a.column2, 1, 2) as A_COLUMN2 from foo a where STR(a.column2, 1, 2) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(str(1, column5, column6).as("A_COLUMN2")).from(table, "a").where(str(1, column5, column6), isEqualTo("ABC")),
            	"select STR(1, a.column5, a.column6) as A_COLUMN2 from foo a where STR(1, a.column5, a.column6) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(str(1, column5, 3).as("A_COLUMN2")).from(table, "a").where(str(1, column5, 3), isEqualTo("ABC")),
            	"select STR(1, a.column5, 3) as A_COLUMN2 from foo a where STR(1, a.column5, 3) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(str(1, 2, column6).as("A_COLUMN2")).from(table, "a").where(str(1, 2, column6), isEqualTo("ABC")),
            	"select STR(1, 2, a.column6) as A_COLUMN2 from foo a where STR(1, 2, a.column6) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(str(1, 2, 3).as("A_COLUMN2")).from(table, "a").where(str(1, 2, 3), isEqualTo("ABC")),
            	"select STR(1, 2, 3) as A_COLUMN2 from foo a where STR(1, 2, 3) = #{parameters.p1,jdbcType=VARCHAR}");
    }
    
    @Test
    public void testStuff() {
        test(select(stuff(column1, column2, column5, column3).as("A_COLUMN2")).from(table, "a").where(stuff(column1, column2, column5, column3), isEqualTo("ABC")),
        	"select STUFF(a.column1, a.column2, a.column5, a.column3) as A_COLUMN2 from foo a where STUFF(a.column1, a.column2, a.column5, a.column3) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(stuff(column1, column2, column5, "test").as("A_COLUMN2")).from(table, "a").where(stuff(column1, column2, column5, "test"), isEqualTo("ABC")),
            	"select STUFF(a.column1, a.column2, a.column5, 'test') as A_COLUMN2 from foo a where STUFF(a.column1, a.column2, a.column5, 'test') = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(stuff(column1, column2, 1, column3).as("A_COLUMN2")).from(table, "a").where(stuff(column1, column2, 1, column3), isEqualTo("ABC")),
            	"select STUFF(a.column1, a.column2, 1, a.column3) as A_COLUMN2 from foo a where STUFF(a.column1, a.column2, 1, a.column3) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(stuff(column1, column2, 1, "test").as("A_COLUMN2")).from(table, "a").where(stuff(column1, column2, 1, "test"), isEqualTo("ABC")),
            	"select STUFF(a.column1, a.column2, 1, 'test') as A_COLUMN2 from foo a where STUFF(a.column1, a.column2, 1, 'test') = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(stuff(column1, 1, column5, column3).as("A_COLUMN2")).from(table, "a").where(stuff(column1, 1, column5, column3), isEqualTo("ABC")),
            	"select STUFF(a.column1, 1, a.column5, a.column3) as A_COLUMN2 from foo a where STUFF(a.column1, 1, a.column5, a.column3) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(stuff(column1, 1, column5, "test").as("A_COLUMN2")).from(table, "a").where(stuff(column1, 1, column5, "test"), isEqualTo("ABC")),
            	"select STUFF(a.column1, 1, a.column5, 'test') as A_COLUMN2 from foo a where STUFF(a.column1, 1, a.column5, 'test') = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(stuff(column1, 1, 2, column3).as("A_COLUMN2")).from(table, "a").where(stuff(column1, 1, 2, column3), isEqualTo("ABC")),
            	"select STUFF(a.column1, 1, 2, a.column3) as A_COLUMN2 from foo a where STUFF(a.column1, 1, 2, a.column3) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(stuff(column1, 1, 2, "test").as("A_COLUMN2")).from(table, "a").where(stuff(column1, 1, 2, "test"), isEqualTo("ABC")),
            	"select STUFF(a.column1, 1, 2, 'test') as A_COLUMN2 from foo a where STUFF(a.column1, 1, 2, 'test') = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(stuff("test", column2, column5, column3).as("A_COLUMN2")).from(table, "a").where(stuff("test", column2, column5, column3), isEqualTo("ABC")),
            	"select STUFF('test', a.column2, a.column5, a.column3) as A_COLUMN2 from foo a where STUFF('test', a.column2, a.column5, a.column3) = #{parameters.p1,jdbcType=VARCHAR}");
            
        test(select(stuff("test", column2, column5, "test").as("A_COLUMN2")).from(table, "a").where(stuff("test", column2, column5, "test"), isEqualTo("ABC")),
            	"select STUFF('test', a.column2, a.column5, 'test') as A_COLUMN2 from foo a where STUFF('test', a.column2, a.column5, 'test') = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(stuff("test", column2, 1, column3).as("A_COLUMN2")).from(table, "a").where(stuff("test", column2, 1, column3), isEqualTo("ABC")),
            	"select STUFF('test', a.column2, 1, a.column3) as A_COLUMN2 from foo a where STUFF('test', a.column2, 1, a.column3) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(stuff("test", column2, 1, "test").as("A_COLUMN2")).from(table, "a").where(stuff("test", column2, 1, "test"), isEqualTo("ABC")),
            	"select STUFF('test', a.column2, 1, 'test') as A_COLUMN2 from foo a where STUFF('test', a.column2, 1, 'test') = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(stuff("test", 1, column5, column3).as("A_COLUMN2")).from(table, "a").where(stuff("test", 1, column5, column3), isEqualTo("ABC")),
            	"select STUFF('test', 1, a.column5, a.column3) as A_COLUMN2 from foo a where STUFF('test', 1, a.column5, a.column3) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(stuff("test", 1, column5, "test").as("A_COLUMN2")).from(table, "a").where(stuff("test", 1, column5, "test"), isEqualTo("ABC")),
            	"select STUFF('test', 1, a.column5, 'test') as A_COLUMN2 from foo a where STUFF('test', 1, a.column5, 'test') = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(stuff("test", 1, 2, column3).as("A_COLUMN2")).from(table, "a").where(stuff("test", 1, 2, column3), isEqualTo("ABC")),
            	"select STUFF('test', 1, 2, a.column3) as A_COLUMN2 from foo a where STUFF('test', 1, 2, a.column3) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(stuff("test", 1, 2, "test").as("A_COLUMN2")).from(table, "a").where(stuff("test", 1, 2, "test"), isEqualTo("ABC")),
            	"select STUFF('test', 1, 2, 'test') as A_COLUMN2 from foo a where STUFF('test', 1, 2, 'test') = #{parameters.p1,jdbcType=VARCHAR}");
    }
    
    @Test
    public void testSubstring() {
        test(select(substring(column1, column2, column5).as("A_COLUMN2")).from(table, "a").where(substring(column1, column2, column5), isEqualTo("ABC")),
        	"select SUBSTRING(a.column1, a.column2, a.column5) as A_COLUMN2 from foo a where SUBSTRING(a.column1, a.column2, a.column5) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(substring(column1, column2, 1).as("A_COLUMN2")).from(table, "a").where(substring(column1, column2, 1), isEqualTo("ABC")),
            	"select SUBSTRING(a.column1, a.column2, 1) as A_COLUMN2 from foo a where SUBSTRING(a.column1, a.column2, 1) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(substring(column1, 1, column5).as("A_COLUMN2")).from(table, "a").where(substring(column1, 1, column5), isEqualTo("ABC")),
            	"select SUBSTRING(a.column1, 1, a.column5) as A_COLUMN2 from foo a where SUBSTRING(a.column1, 1, a.column5) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(substring(column1, 1, 2).as("A_COLUMN2")).from(table, "a").where(substring(column1, 1, 2), isEqualTo("ABC")),
            	"select SUBSTRING(a.column1, 1, 2) as A_COLUMN2 from foo a where SUBSTRING(a.column1, 1, 2) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(substring("test", column2, column5).as("A_COLUMN2")).from(table, "a").where(substring("test", column2, column5), isEqualTo("ABC")),
            	"select SUBSTRING('test', a.column2, a.column5) as A_COLUMN2 from foo a where SUBSTRING('test', a.column2, a.column5) = #{parameters.p1,jdbcType=VARCHAR}");
            
        test(select(substring("test", column2, 1).as("A_COLUMN2")).from(table, "a").where(substring("test", column2, 1), isEqualTo("ABC")),
            	"select SUBSTRING('test', a.column2, 1) as A_COLUMN2 from foo a where SUBSTRING('test', a.column2, 1) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(substring("test", 1, column5).as("A_COLUMN2")).from(table, "a").where(substring("test", 1, column5), isEqualTo("ABC")),
            	"select SUBSTRING('test', 1, a.column5) as A_COLUMN2 from foo a where SUBSTRING('test', 1, a.column5) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(substring("test", 1, 2).as("A_COLUMN2")).from(table, "a").where(substring("test", 1, 2), isEqualTo("ABC")),
            	"select SUBSTRING('test', 1, 2) as A_COLUMN2 from foo a where SUBSTRING('test', 1, 2) = #{parameters.p1,jdbcType=VARCHAR}");
    }
    
    @Test
    public void testUpper() {
        test(select(upper(column1).as("A_COLUMN1")).from(table, "a").where(upper(column1), isEqualTo("ABC")),
        	"select UPPER(a.column1) as A_COLUMN1 from foo a where UPPER(a.column1) = #{parameters.p1,jdbcType=VARCHAR}");
        
        test(select(upper("column").as("A_COLUMN1")).from(table, "a").where(upper("column2"), isEqualTo("ABC")),
        	"select UPPER('column') as A_COLUMN1 from foo a where UPPER('column2') = #{parameters.p1,jdbcType=VARCHAR}");
    }
    
    
    @SuppressWarnings("rawtypes")
	protected void test(QueryExpressionWhereBuilder selectModel, String sql) {
    	SelectStatementProvider selectStatement = ((SelectModel) selectModel.build()).render(RenderingStrategy.MYBATIS3);
    	SoftAssertions.assertSoftly(softly -> softly.assertThat(selectStatement.getSelectStatement()).isEqualTo(sql));
    }
}
