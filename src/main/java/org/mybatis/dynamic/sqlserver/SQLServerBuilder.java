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
package org.mybatis.dynamic.sqlserver;

import java.sql.JDBCType;
import java.util.Collection;
import java.util.LinkedList;

import org.mybatis.dynamic.sql.BindableColumn;
import org.mybatis.dynamic.sql.select.Function;

public interface SQLServerBuilder {
	
	final static String ASCII = "ASCII";
	final static String CHAR = "CHAR";
	final static String CHARINDEX = "CHARINDEX";
	final static String CONCAT = "CONCAT";
	final static String DATALENGTH = "DATALENGTH";
	final static String LEFT = "LEFT";
	final static String LEN = "LEN";
	final static String LOWER = "LOWER";
	final static String LTRIM = "LTRIM";
	final static String NCHAR = "NCHAR";
	final static String PATINDEX = "PATINDEX";
	final static String REPLACE = "REPLACE";
	final static String RIGHT = "RIGHT";
	final static String RTRIM = "RTRIM";
	final static String SPACE = "SPACE";
	final static String STR = "STR";
	final static String STUFF = "STUFF";
	final static String SUBSTRING = "SUBSTRING";
	final static String UPPER = "UPPER";
	
	static Function<BindableColumn<String>, Integer> ascii(BindableColumn<String> column) {
        return Function.of(column, ASCII, JDBCType.INTEGER);
    }
	
	static Function<String, Integer> ascii(String character) {
        return Function.of(character, ASCII, JDBCType.INTEGER);
    }
	
	static Function<BindableColumn<Integer>, String> character(BindableColumn<Integer> column) {
		return Function.of(column, CHAR, JDBCType.VARCHAR);
	}
	
	static Function<Integer, String> character(Integer ascii) {
		return Function.of(ascii, CHAR, JDBCType.VARCHAR);
	}
	
	static Function<BindableColumn<String>, Integer> charindex(BindableColumn<String> column, BindableColumn<String> substring) {
		return Function.of(column, CHARINDEX, JDBCType.INTEGER, extractParameters(substring));
	}
	
	static Function<BindableColumn<String>, Integer> charindex(BindableColumn<String> column, String substring) {
		return Function.of(column, CHARINDEX, JDBCType.INTEGER, extractParameters(substring));
	}
	
	static Function<String, Integer> charindex(String column, BindableColumn<String> substring) {
		return Function.of(column, CHARINDEX, JDBCType.INTEGER, extractParameters(substring));
	}

	static Function<String, Integer> charindex(String text, String substring) {
		return Function.of(text, CHARINDEX, JDBCType.INTEGER, extractParameters(substring));
	}
	
	static Function<BindableColumn<String>, Integer> charindex(BindableColumn<String> column, BindableColumn<String> substring, BindableColumn<Integer> startPosition) {
		return Function.of(column, CHARINDEX, JDBCType.INTEGER, extractParameters(substring, startPosition));
	}
	
	static Function<BindableColumn<String>, Integer> charindex(BindableColumn<String> column, BindableColumn<String> substring, Integer startPosition) {
		return Function.of(column, CHARINDEX, JDBCType.INTEGER, extractParameters(substring, startPosition));
	}
	
	static Function<BindableColumn<String>, Integer> charindex(BindableColumn<String> column, String substring, BindableColumn<Integer> startPosition) {
		return Function.of(column, CHARINDEX, JDBCType.INTEGER, extractParameters(substring, startPosition));
	}
	
	static Function<BindableColumn<String>, Integer> charindex(BindableColumn<String> column, String substring, Integer startPosition) {
		return Function.of(column, CHARINDEX, JDBCType.INTEGER, extractParameters(substring, startPosition));
	}
	
	static Function<String, Integer> charindex(String column, BindableColumn<String> substring, BindableColumn<Integer> startPosition) {
		return Function.of(column, CHARINDEX, JDBCType.INTEGER, extractParameters(substring, startPosition));
	}
	
	static Function<String, Integer> charindex(String column, BindableColumn<String> substring, Integer startPosition) {
		return Function.of(column, CHARINDEX, JDBCType.INTEGER, extractParameters(substring, startPosition));
	}
	
	static Function<String, Integer> charindex(String column, String substring, BindableColumn<Integer> startPosition) {
		return Function.of(column, CHARINDEX, JDBCType.INTEGER, extractParameters(substring, startPosition));
	}
	
	static Function<String, Integer> charindex(String text, String substring, Integer startPosition) {
		return Function.of(text, CHARINDEX, JDBCType.INTEGER, extractParameters(substring, startPosition));
	}
	
	@SafeVarargs
	static Function<BindableColumn<String>, String> concat(BindableColumn<String> firstColumn, BindableColumn<String> secondColumn, Object... subsequentColumns) {
		return Function.of(firstColumn, CONCAT, JDBCType.VARCHAR, extractParameters(secondColumn, subsequentColumns));	
	}
	
	@SafeVarargs
	static Function<BindableColumn<String>, String> concat(BindableColumn<String> firstColumn, String secondColumn, Object... subsequentColumns) {
		return Function.of(firstColumn, CONCAT, JDBCType.VARCHAR, extractParameters(secondColumn, subsequentColumns));	
	}
	
	@SafeVarargs
	static Function<String, String> concat(String text1, BindableColumn<String> text2, Object... otherText) {
		return Function.of(text1, CONCAT, JDBCType.VARCHAR, extractParameters(text2, otherText));	
	}
	
	@SafeVarargs
	static Function<String, String> concat(String text1, String text2, Object... otherText) {
		return Function.of(text1, CONCAT, JDBCType.VARCHAR, extractParameters(text2, otherText));	
	}
	
	static Function<BindableColumn<String>, Integer> datalength(BindableColumn<String> column) {
		return Function.of(column, DATALENGTH, JDBCType.INTEGER);
	}
	
	static Function<String, Integer> datalength(String data) {
		return Function.of(data, DATALENGTH, JDBCType.INTEGER);
	}
	
	static Function<BindableColumn<String>, String> left(BindableColumn<String> text, BindableColumn<Integer> numberOfChars) {
		return Function.of(text, LEFT, JDBCType.VARCHAR, extractParameters(numberOfChars));
	}
	
	static Function<BindableColumn<String>, String> left(BindableColumn<String> column, Integer numberOfChars) {
		return Function.of(column, LEFT, JDBCType.VARCHAR, extractParameters(numberOfChars));
	}
	
	static Function<String, String> left(String text, BindableColumn<Integer> numberOfChars) {
		return Function.of(text, LEFT, JDBCType.VARCHAR, extractParameters(numberOfChars));
	}
	
	static Function<String, String> left(String text, Integer numberOfChars) {
		return Function.of(text, LEFT, JDBCType.VARCHAR, extractParameters(numberOfChars));
	}
	
	static Function<BindableColumn<String>, Integer> len(BindableColumn<String> column) {
		return Function.of(column, LEN, JDBCType.INTEGER);
	}
	
	static Function<String, Integer> len(String text) {
		return Function.of(text, LEN, JDBCType.INTEGER);
	}
	
	static Function<BindableColumn<String>, String> lower(BindableColumn<String> column) {
		return Function.of(column, LOWER, JDBCType.VARCHAR);
	}
	
	static Function<String, String> lower(String text) {
		return Function.of(text, LOWER, JDBCType.VARCHAR);
	}
	
	static Function<BindableColumn<String>, String> ltrim(BindableColumn<String> column) {
		return Function.of(column, LTRIM, JDBCType.VARCHAR);
	}
	
	static Function<String, String> ltrim(String column) {
		return Function.of(column, LTRIM, JDBCType.VARCHAR);
	}
	
	static Function<BindableColumn<Integer>, String> nchar(BindableColumn<Integer> column) {
        return Function.of(column, NCHAR, JDBCType.NVARCHAR);
    }
	
	static Function<Integer, String> nchar(Integer text) {
        return Function.of(text, NCHAR, JDBCType.NVARCHAR);
    }
	
	static Function<BindableColumn<String>, Integer> patindex(BindableColumn<String> pattern, BindableColumn<String> text) {
		return Function.of(pattern, PATINDEX, JDBCType.INTEGER, extractParameters(text));
	}
	
	static Function<String, Integer> patindex(String pattern, BindableColumn<String> text) {
		return Function.of(pattern, PATINDEX, JDBCType.INTEGER, extractParameters(text));
	}
	
	static Function<BindableColumn<String>, Integer> patindex(BindableColumn<String> column, String text) {
		return Function.of(column, PATINDEX, JDBCType.INTEGER, extractParameters(text));
	}
	
	static Function<String, Integer> patindex(String pattern, String text) {
		return Function.of(pattern, PATINDEX, JDBCType.INTEGER, extractParameters(text));
	}
	
	static Function<BindableColumn<String>, String> replace(BindableColumn<String> text, BindableColumn<String> stringToReplace, BindableColumn<String> replacementString) {
		return Function.of(text, REPLACE, JDBCType.VARCHAR, extractParameters(stringToReplace, replacementString));
	}
	
	static Function<BindableColumn<String>, String> replace(BindableColumn<String> text, BindableColumn<String> stringToReplace, String replacementString) {
		return Function.of(text, REPLACE, JDBCType.VARCHAR, extractParameters(stringToReplace, replacementString));
	}
	
	static Function<BindableColumn<String>, String> replace(BindableColumn<String> text, String stringToReplace, BindableColumn<String> replacementString) {
		return Function.of(text, REPLACE, JDBCType.VARCHAR, extractParameters(stringToReplace, replacementString));
	}
	
	static Function<BindableColumn<String>, String> replace(BindableColumn<String> column, String stringToReplace, String replacementString) {
		return Function.of(column, REPLACE, JDBCType.VARCHAR, extractParameters(stringToReplace, replacementString));
	}
	
	static Function<String, String> replace(String text, BindableColumn<String> stringToReplace, BindableColumn<String> replacementString) {
		return Function.of(text, REPLACE, JDBCType.VARCHAR, extractParameters(stringToReplace, replacementString));
	}
	
	static Function<String, String> replace(String text, BindableColumn<String> stringToReplace, String replacementString) {
		return Function.of(text, REPLACE, JDBCType.VARCHAR, extractParameters(stringToReplace, replacementString));
	}
	
	static Function<String, String> replace(String text, String stringToReplace, BindableColumn<String> replacementString) {
		return Function.of(text, REPLACE, JDBCType.VARCHAR, extractParameters(stringToReplace, replacementString));
	}
	
	static Function<String, String> replace(String text, String stringToReplace, String replacementString) {
		return Function.of(text, REPLACE, JDBCType.VARCHAR, extractParameters(stringToReplace, replacementString));
	}
	
	static Function<BindableColumn<String>, String> right(BindableColumn<String> text, BindableColumn<Integer> numberOfChars) {
		return Function.of(text, RIGHT, JDBCType.VARCHAR, extractParameters(numberOfChars));
	}
	
	static Function<BindableColumn<String>, String> right(BindableColumn<String> column, Integer numberOfChars) {
		return Function.of(column, RIGHT, JDBCType.VARCHAR, extractParameters(numberOfChars));
	}
	
	static Function<String, String> right(String text, BindableColumn<Integer> numberOfChars) {
		return Function.of(text, RIGHT, JDBCType.VARCHAR, extractParameters(numberOfChars));
	}
	
	static Function<String, String> right(String text, Integer numberOfChars) {
		return Function.of(text, RIGHT, JDBCType.VARCHAR, extractParameters(numberOfChars));
	}
	
	static Function<BindableColumn<String>, String> rtrim(BindableColumn<String> column) {
		return Function.of(column, RTRIM, JDBCType.VARCHAR);
	}
	
	static Function<String, String> rtrim(String text) {
		return Function.of(text, RTRIM, JDBCType.VARCHAR);
	}
	
	static Function<BindableColumn<Integer>, String> space(BindableColumn<Integer> nbSpaces) {
		return Function.of(nbSpaces, SPACE, JDBCType.VARCHAR);
	}
	
	static Function<Integer, String> space(Integer nbSpaces) {
		return Function.of(nbSpaces, SPACE, JDBCType.VARCHAR);
	}
	
	static Function<BindableColumn<Integer>, String> str(BindableColumn<Integer> number) {
		return Function.of(number, STR, JDBCType.VARCHAR);
	}
	
	static Function<Integer, String> str(Integer number) {
		return Function.of(number, STR, JDBCType.VARCHAR);
	}
	
	static Function<BindableColumn<Integer>, String> str(BindableColumn<Integer> number, BindableColumn<Integer> length) {
		return Function.of(number, STR, JDBCType.VARCHAR, extractParameters(length));
	}
	
	static Function<BindableColumn<Integer>, String> str(BindableColumn<Integer> number, Integer length) {
		return Function.of(number, STR, JDBCType.VARCHAR, extractParameters(length));
	}
	
	static Function<Integer, String> str(Integer number, BindableColumn<Integer> length) {
		return Function.of(number, STR, JDBCType.VARCHAR, extractParameters(length));
	}
	
	static Function<Integer, String> str(Integer number, Integer length) {
		return Function.of(number, STR, JDBCType.VARCHAR, extractParameters(length));
	}
	
	static Function<BindableColumn<Integer>, String> str(BindableColumn<Integer> number, BindableColumn<Integer> length, BindableColumn<Integer> decimalPlaces) {
		return Function.of(number, STR, JDBCType.VARCHAR, extractParameters(length, decimalPlaces));
	}
	
	static Function<BindableColumn<Integer>, String> str(BindableColumn<Integer> number, BindableColumn<Integer> length, Integer decimalPlaces) {
		return Function.of(number, STR, JDBCType.VARCHAR, extractParameters(length, decimalPlaces));
	}
	
	static Function<BindableColumn<Integer>, String> str(BindableColumn<Integer> number, Integer length, BindableColumn<Integer> decimalPlaces) {
		return Function.of(number, STR, JDBCType.VARCHAR, extractParameters(length, decimalPlaces));
	}
	
	static Function<BindableColumn<Integer>, String> str(BindableColumn<Integer> number, Integer length, Integer decimalPlaces) {
		return Function.of(number, STR, JDBCType.VARCHAR, extractParameters(length, decimalPlaces));
	}
	
	static Function<Integer, String> str(Integer number, BindableColumn<Integer> length, BindableColumn<Integer> decimalPlaces) {
		return Function.of(number, STR, JDBCType.VARCHAR, extractParameters(length, decimalPlaces));
	}
	
	static Function<Integer, String> str(Integer number, BindableColumn<Integer> length, Integer decimalPlaces) {
		return Function.of(number, STR, JDBCType.VARCHAR, extractParameters(length, decimalPlaces));
	}
	
	static Function<Integer, String> str(Integer number, Integer length, BindableColumn<Integer> decimalPlaces) {
		return Function.of(number, STR, JDBCType.VARCHAR, extractParameters(length, decimalPlaces));
	}
	
	static Function<Integer, String> str(Integer number, Integer length, Integer decimalPlaces) {
		return Function.of(number, STR, JDBCType.VARCHAR, extractParameters(length, decimalPlaces));
	}
	
	static Function<BindableColumn<String>, String> stuff(BindableColumn<String> column, BindableColumn<Integer> start, BindableColumn<Integer> length, BindableColumn<String> addString) {
		return Function.of(column, STUFF, JDBCType.VARCHAR, extractParameters(start, length, addString));
	}
	
	static Function<BindableColumn<String>, String> stuff(BindableColumn<String> column, BindableColumn<Integer> start, BindableColumn<Integer> length, String addString) {
		return Function.of(column, STUFF, JDBCType.VARCHAR, extractParameters(start, length, addString));
	}
	
	static Function<BindableColumn<String>, String> stuff(BindableColumn<String> column, BindableColumn<Integer> start, Integer length, BindableColumn<String> addString) {
		return Function.of(column, STUFF, JDBCType.VARCHAR, extractParameters(start, length, addString));
	}
	
	static Function<BindableColumn<String>, String> stuff(BindableColumn<String> column, BindableColumn<Integer> start, Integer length, String addString) {
		return Function.of(column, STUFF, JDBCType.VARCHAR, extractParameters(start, length, addString));
	}
	
	static Function<BindableColumn<String>, String> stuff(BindableColumn<String> column, Integer start, BindableColumn<Integer> length, BindableColumn<String> addString) {
		return Function.of(column, STUFF, JDBCType.VARCHAR, extractParameters(start, length, addString));
	}
	
	static Function<BindableColumn<String>, String> stuff(BindableColumn<String> column, Integer start, BindableColumn<Integer> length, String addString) {
		return Function.of(column, STUFF, JDBCType.VARCHAR, extractParameters(start, length, addString));
	}
	
	static Function<BindableColumn<String>, String> stuff(BindableColumn<String> column, Integer start, Integer length, BindableColumn<String> addString) {
		return Function.of(column, STUFF, JDBCType.VARCHAR, extractParameters(start, length, addString));
	}
	
	static Function<BindableColumn<String>, String> stuff(BindableColumn<String> column, Integer start, Integer length, String addString) {
		return Function.of(column, STUFF, JDBCType.VARCHAR, extractParameters(start, length, addString));
	}
	
	static Function<String, String> stuff(String text, BindableColumn<Integer> start, BindableColumn<Integer> length, BindableColumn<String> addString) {
		return Function.of(text, STUFF, JDBCType.VARCHAR, extractParameters(start, length, addString));
	}
	
	static Function<String, String> stuff(String text, BindableColumn<Integer> start, BindableColumn<Integer> length, String addString) {
		return Function.of(text, STUFF, JDBCType.VARCHAR, extractParameters(start, length, addString));
	}
	
	static Function<String, String> stuff(String text, BindableColumn<Integer> start, Integer length, BindableColumn<String> addString) {
		return Function.of(text, STUFF, JDBCType.VARCHAR, extractParameters(start, length, addString));
	}
	
	static Function<String, String> stuff(String text, BindableColumn<Integer> start, Integer length, String addString) {
		return Function.of(text, STUFF, JDBCType.VARCHAR, extractParameters(start, length, addString));
	}
	
	static Function<String, String> stuff(String column, Integer start, BindableColumn<Integer> length, BindableColumn<String> addString) {
		return Function.of(column, STUFF, JDBCType.VARCHAR, extractParameters(start, length, addString));
	}
	
	static Function<String, String> stuff(String text, Integer start, BindableColumn<Integer> length, String addString) {
		return Function.of(text, STUFF, JDBCType.VARCHAR, extractParameters(start, length, addString));
	}
	
	static Function<String, String> stuff(String text, Integer start, Integer length, BindableColumn<String> addString) {
		return Function.of(text, STUFF, JDBCType.VARCHAR, extractParameters(start, length, addString));
	}
	
	static Function<String, String> stuff(String text, Integer start, Integer length, String addString) {
		return Function.of(text, STUFF, JDBCType.VARCHAR, extractParameters(start, length, addString));
	}
	
	static Function<BindableColumn<String>, String> substring(BindableColumn<String> column, BindableColumn<Integer> start, BindableColumn<Integer> length) {
		return Function.of(column, SUBSTRING, JDBCType.VARCHAR, extractParameters(start, length));
	}
	
	static Function<BindableColumn<String>, String> substring(BindableColumn<String> column, BindableColumn<Integer> start, Integer length) {
		return Function.of(column, SUBSTRING, JDBCType.VARCHAR, extractParameters(start, length));
	}
	
	static Function<BindableColumn<String>, String> substring(BindableColumn<String> column, Integer start, BindableColumn<Integer> length) {
		return Function.of(column, SUBSTRING, JDBCType.VARCHAR, extractParameters(start, length));
	}
	
	static Function<BindableColumn<String>, String> substring(BindableColumn<String> column, Integer start, Integer length) {
		return Function.of(column, SUBSTRING, JDBCType.VARCHAR, extractParameters(start, length));
	}
	
	static Function<String, String> substring(String text, BindableColumn<Integer> start, BindableColumn<Integer> length) {
		return Function.of(text, SUBSTRING, JDBCType.VARCHAR, extractParameters(start, length));
	}
	
	static Function<String, String> substring(String text, BindableColumn<Integer> start, Integer length) {
		return Function.of(text, SUBSTRING, JDBCType.VARCHAR, extractParameters(start, length));
	}
	
	static Function<String, String> substring(String text, Integer start, BindableColumn<Integer> length) {
		return Function.of(text, SUBSTRING, JDBCType.VARCHAR, extractParameters(start, length));
	}
	
	static Function<String, String> substring(String text, Integer start, Integer length) {
		return Function.of(text, SUBSTRING, JDBCType.VARCHAR, extractParameters(start, length));
	}
	
	static Function<BindableColumn<String>, String> upper(BindableColumn<String> column) {
		return Function.of(column, UPPER, JDBCType.VARCHAR);
	}
	
	static Function<String, String> upper(String text) {
		return Function.of(text, UPPER, JDBCType.VARCHAR);
	}
	
	static Collection<Object> extractParameters(Object param1, Object... params) {
		Collection<Object> result = new LinkedList<>();
		result.add(param1);
		for (Object object: params) {
			result.add(object);
		}
		return result;
	}
}
