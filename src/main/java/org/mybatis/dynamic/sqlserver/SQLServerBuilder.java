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
	
	final static String ABS = "ABS";
	final static String AVG = "AVG";
	final static String CEILING = "CEILING";
	final static String COUNT = "COUNT";
	final static String FLOOR = "FLOOR";
	final static String MAX = "MAX";
	final static String MIN = "MIN";
	final static String RAND = "RAND";
	final static String ROUND = "ROUND";
	final static String SIGN = "SIGN";
	final static String SUM = "SUM";
	
	final static String DATEADD = "DATEADD";
	final static String DATEDIFF = "DATEDIFF";
	final static String DATENAME = "DATENAME";
	final static String DATEPART = "DATEPART";
	final static String DAY = "DAY";
	final static String GETDATE = "GETDATE";
	final static String GETUTCDATE = "GETUTCDATE";
	final static String MONTH = "MONTH";
	final static String YEAR = "YEAR";
	
	static Function<Integer> ascii(BindableColumn<String> column) {
        return Function.of(ASCII, JDBCType.INTEGER, extractParameters(column));
    }
	
	static Function<Integer> ascii(String character) {
        return Function.of(ASCII, JDBCType.INTEGER, extractParameters(character));
    }
	
	static Function<String> character(BindableColumn<Integer> column) {
		return Function.of(CHAR, JDBCType.VARCHAR, extractParameters(column));
	}
	
	static Function<String> character(Integer ascii) {
		return Function.of(CHAR, JDBCType.VARCHAR, extractParameters(ascii));
	}
	
	static Function<Integer> charindex(BindableColumn<String> column, BindableColumn<String> substring) {
		return Function.of(CHARINDEX, JDBCType.INTEGER, extractParameters(column, substring));
	}
	
	static Function<Integer> charindex(BindableColumn<String> column, String substring) {
		return Function.of(CHARINDEX, JDBCType.INTEGER, extractParameters(column, substring));
	}
	
	static Function<Integer> charindex(String column, BindableColumn<String> substring) {
		return Function.of(CHARINDEX, JDBCType.INTEGER, extractParameters(column, substring));
	}

	static Function<Integer> charindex(String text, String substring) {
		return Function.of(CHARINDEX, JDBCType.INTEGER, extractParameters(text, substring));
	}
	
	static Function<Integer> charindex(BindableColumn<String> column, BindableColumn<String> substring, BindableColumn<Integer> startPosition) {
		return Function.of(CHARINDEX, JDBCType.INTEGER, extractParameters(column, substring, startPosition));
	}
	
	static Function<Integer> charindex(BindableColumn<String> column, BindableColumn<String> substring, Integer startPosition) {
		return Function.of(CHARINDEX, JDBCType.INTEGER, extractParameters(column, substring, startPosition));
	}
	
	static Function<Integer> charindex(BindableColumn<String> column, String substring, BindableColumn<Integer> startPosition) {
		return Function.of(CHARINDEX, JDBCType.INTEGER, extractParameters(column, substring, startPosition));
	}
	
	static Function<Integer> charindex(BindableColumn<String> column, String substring, Integer startPosition) {
		return Function.of(CHARINDEX, JDBCType.INTEGER, extractParameters(column, substring, startPosition));
	}
	
	static Function<Integer> charindex(String column, BindableColumn<String> substring, BindableColumn<Integer> startPosition) {
		return Function.of(CHARINDEX, JDBCType.INTEGER, extractParameters(column, substring, startPosition));
	}
	
	static Function<Integer> charindex(String column, BindableColumn<String> substring, Integer startPosition) {
		return Function.of(CHARINDEX, JDBCType.INTEGER, extractParameters(column, substring, startPosition));
	}
	
	static Function<Integer> charindex(String column, String substring, BindableColumn<Integer> startPosition) {
		return Function.of(CHARINDEX, JDBCType.INTEGER, extractParameters(column, substring, startPosition));
	}
	
	static Function<Integer> charindex(String text, String substring, Integer startPosition) {
		return Function.of(CHARINDEX, JDBCType.INTEGER, extractParameters(text, substring, startPosition));
	}
	
	@SafeVarargs
	static Function<String> concat(BindableColumn<String> firstColumn, BindableColumn<String> secondColumn, Object... subsequentColumns) {
		return Function.of(CONCAT, JDBCType.VARCHAR, extractParameters(firstColumn, secondColumn, subsequentColumns));	
	}
	
	@SafeVarargs
	static Function<String> concat(BindableColumn<String> firstColumn, String secondColumn, Object... subsequentColumns) {
		return Function.of(CONCAT, JDBCType.VARCHAR, extractParameters(firstColumn, secondColumn, subsequentColumns));	
	}
	
	@SafeVarargs
	static Function<String> concat(String text1, BindableColumn<String> text2, Object... otherText) {
		return Function.of(CONCAT, JDBCType.VARCHAR, extractParameters(text1, text2, otherText));	
	}
	
	@SafeVarargs
	static Function<String> concat(String text1, String text2, Object... otherText) {
		return Function.of(CONCAT, JDBCType.VARCHAR, extractParameters(text1, text2, otherText));	
	}
	
	static Function<Integer> datalength(BindableColumn<String> column) {
		return Function.of(DATALENGTH, JDBCType.INTEGER, extractParameters(column));
	}
	
	static Function<Integer> datalength(String data) {
		return Function.of(DATALENGTH, JDBCType.INTEGER, extractParameters(data));
	}
	
	static Function<String> left(BindableColumn<String> text, BindableColumn<Integer> numberOfChars) {
		return Function.of(LEFT, JDBCType.VARCHAR, extractParameters(text, numberOfChars));
	}
	
	static Function<String> left(BindableColumn<String> column, Integer numberOfChars) {
		return Function.of(LEFT, JDBCType.VARCHAR, extractParameters(column, numberOfChars));
	}
	
	static Function<String> left(String text, BindableColumn<Integer> numberOfChars) {
		return Function.of(LEFT, JDBCType.VARCHAR, extractParameters(text, numberOfChars));
	}
	
	static Function<String> left(String text, Integer numberOfChars) {
		return Function.of(LEFT, JDBCType.VARCHAR, extractParameters(text, numberOfChars));
	}
	
	static Function<Integer> len(BindableColumn<String> column) {
		return Function.of(LEN, JDBCType.INTEGER, extractParameters(column));
	}
	
	static Function<Integer> len(String text) {
		return Function.of(LEN, JDBCType.INTEGER, extractParameters(text));
	}
	
	static Function<String> lower(BindableColumn<String> column) {
		return Function.of(LOWER, JDBCType.VARCHAR, extractParameters(column));
	}
	
	static Function<String> lower(String text) {
		return Function.of(LOWER, JDBCType.VARCHAR, extractParameters(text));
	}
	
	static Function<String> ltrim(BindableColumn<String> column) {
		return Function.of(LTRIM, JDBCType.VARCHAR, extractParameters(column));
	}
	
	static Function<String> ltrim(String column) {
		return Function.of(LTRIM, JDBCType.VARCHAR, extractParameters(column));
	}
	
	static Function<String> nchar(BindableColumn<Integer> column) {
        return Function.of(NCHAR, JDBCType.NVARCHAR, extractParameters(column));
    }
	
	static Function<String> nchar(Integer text) {
        return Function.of(NCHAR, JDBCType.NVARCHAR, extractParameters(text));
    }
	
	static Function<Integer> patindex(BindableColumn<String> pattern, BindableColumn<String> text) {
		return Function.of(PATINDEX, JDBCType.INTEGER, extractParameters(pattern, text));
	}
	
	static Function<Integer> patindex(String pattern, BindableColumn<String> text) {
		return Function.of(PATINDEX, JDBCType.INTEGER, extractParameters(pattern, text));
	}
	
	static Function<Integer> patindex(BindableColumn<String> column, String text) {
		return Function.of(PATINDEX, JDBCType.INTEGER, extractParameters(column, text));
	}
	
	static Function<Integer> patindex(String pattern, String text) {
		return Function.of(PATINDEX, JDBCType.INTEGER, extractParameters(pattern, text));
	}
	
	static Function<String> replace(BindableColumn<String> text, BindableColumn<String> stringToReplace, BindableColumn<String> replacementString) {
		return Function.of(REPLACE, JDBCType.VARCHAR, extractParameters(text, stringToReplace, replacementString));
	}
	
	static Function<String> replace(BindableColumn<String> text, BindableColumn<String> stringToReplace, String replacementString) {
		return Function.of(REPLACE, JDBCType.VARCHAR, extractParameters(text, stringToReplace, replacementString));
	}
	
	static Function<String> replace(BindableColumn<String> text, String stringToReplace, BindableColumn<String> replacementString) {
		return Function.of(REPLACE, JDBCType.VARCHAR, extractParameters(text, stringToReplace, replacementString));
	}
	
	static Function<String> replace(BindableColumn<String> column, String stringToReplace, String replacementString) {
		return Function.of(REPLACE, JDBCType.VARCHAR, extractParameters(column, stringToReplace, replacementString));
	}
	
	static Function<String> replace(String text, BindableColumn<String> stringToReplace, BindableColumn<String> replacementString) {
		return Function.of(REPLACE, JDBCType.VARCHAR, extractParameters(text, stringToReplace, replacementString));
	}
	
	static Function<String> replace(String text, BindableColumn<String> stringToReplace, String replacementString) {
		return Function.of(REPLACE, JDBCType.VARCHAR, extractParameters(text, stringToReplace, replacementString));
	}
	
	static Function<String> replace(String text, String stringToReplace, BindableColumn<String> replacementString) {
		return Function.of(REPLACE, JDBCType.VARCHAR, extractParameters(text, stringToReplace, replacementString));
	}
	
	static Function<String> replace(String text, String stringToReplace, String replacementString) {
		return Function.of(REPLACE, JDBCType.VARCHAR, extractParameters(text, stringToReplace, replacementString));
	}
	
	static Function<String> right(BindableColumn<String> text, BindableColumn<Integer> numberOfChars) {
		return Function.of(RIGHT, JDBCType.VARCHAR, extractParameters(text, numberOfChars));
	}
	
	static Function<String> right(BindableColumn<String> column, Integer numberOfChars) {
		return Function.of(RIGHT, JDBCType.VARCHAR, extractParameters(column, numberOfChars));
	}
	
	static Function<String> right(String text, BindableColumn<Integer> numberOfChars) {
		return Function.of(RIGHT, JDBCType.VARCHAR, extractParameters(text, numberOfChars));
	}
	
	static Function<String> right(String text, Integer numberOfChars) {
		return Function.of(RIGHT, JDBCType.VARCHAR, extractParameters(text, numberOfChars));
	}
	
	static Function<String> rtrim(BindableColumn<String> column) {
		return Function.of(RTRIM, JDBCType.VARCHAR, extractParameters(column));
	}
	
	static Function<String> rtrim(String text) {
		return Function.of(RTRIM, JDBCType.VARCHAR, extractParameters(text));
	}
	
	static Function<String> space(BindableColumn<Integer> nbSpaces) {
		return Function.of(SPACE, JDBCType.VARCHAR, extractParameters(nbSpaces));
	}
	
	static Function<String> space(Integer nbSpaces) {
		return Function.of(SPACE, JDBCType.VARCHAR, extractParameters(nbSpaces));
	}
	
	static Function<String> str(BindableColumn<Integer> number) {
		return Function.of(STR, JDBCType.VARCHAR, extractParameters(number));
	}
	
	static Function<String> str(Integer number) {
		return Function.of(STR, JDBCType.VARCHAR, extractParameters(number));
	}
	
	static Function<String> str(BindableColumn<Integer> number, BindableColumn<Integer> length) {
		return Function.of(STR, JDBCType.VARCHAR, extractParameters(number, length));
	}
	
	static Function<String> str(BindableColumn<Integer> number, Integer length) {
		return Function.of(STR, JDBCType.VARCHAR, extractParameters(number, length));
	}
	
	static Function<String> str(Integer number, BindableColumn<Integer> length) {
		return Function.of(STR, JDBCType.VARCHAR, extractParameters(number, length));
	}
	
	static Function<String> str(Integer number, Integer length) {
		return Function.of(STR, JDBCType.VARCHAR, extractParameters(number, length));
	}
	
	static Function<String> str(BindableColumn<Integer> number, BindableColumn<Integer> length, BindableColumn<Integer> decimalPlaces) {
		return Function.of(STR, JDBCType.VARCHAR, extractParameters(number, length, decimalPlaces));
	}
	
	static Function<String> str(BindableColumn<Integer> number, BindableColumn<Integer> length, Integer decimalPlaces) {
		return Function.of(STR, JDBCType.VARCHAR, extractParameters(number, length, decimalPlaces));
	}
	
	static Function<String> str(BindableColumn<Integer> number, Integer length, BindableColumn<Integer> decimalPlaces) {
		return Function.of(STR, JDBCType.VARCHAR, extractParameters(number, length, decimalPlaces));
	}
	
	static Function<String> str(BindableColumn<Integer> number, Integer length, Integer decimalPlaces) {
		return Function.of(STR, JDBCType.VARCHAR, extractParameters(number, length, decimalPlaces));
	}
	
	static Function<String> str(Integer number, BindableColumn<Integer> length, BindableColumn<Integer> decimalPlaces) {
		return Function.of(STR, JDBCType.VARCHAR, extractParameters(number, length, decimalPlaces));
	}
	
	static Function<String> str(Integer number, BindableColumn<Integer> length, Integer decimalPlaces) {
		return Function.of(STR, JDBCType.VARCHAR, extractParameters(number, length, decimalPlaces));
	}
	
	static Function<String> str(Integer number, Integer length, BindableColumn<Integer> decimalPlaces) {
		return Function.of(STR, JDBCType.VARCHAR, extractParameters(number, length, decimalPlaces));
	}
	
	static Function<String> str(Integer number, Integer length, Integer decimalPlaces) {
		return Function.of(STR, JDBCType.VARCHAR, extractParameters(number, length, decimalPlaces));
	}
	
	static Function<String> stuff(BindableColumn<String> column, BindableColumn<Integer> start, BindableColumn<Integer> length, BindableColumn<String> addString) {
		return Function.of(STUFF, JDBCType.VARCHAR, extractParameters(column, start, length, addString));
	}
	
	static Function<String> stuff(BindableColumn<String> column, BindableColumn<Integer> start, BindableColumn<Integer> length, String addString) {
		return Function.of(STUFF, JDBCType.VARCHAR, extractParameters(column, start, length, addString));
	}
	
	static Function<String> stuff(BindableColumn<String> column, BindableColumn<Integer> start, Integer length, BindableColumn<String> addString) {
		return Function.of(STUFF, JDBCType.VARCHAR, extractParameters(column, start, length, addString));
	}
	
	static Function<String> stuff(BindableColumn<String> column, BindableColumn<Integer> start, Integer length, String addString) {
		return Function.of(STUFF, JDBCType.VARCHAR, extractParameters(column, start, length, addString));
	}
	
	static Function<String> stuff(BindableColumn<String> column, Integer start, BindableColumn<Integer> length, BindableColumn<String> addString) {
		return Function.of(STUFF, JDBCType.VARCHAR, extractParameters(column, start, length, addString));
	}
	
	static Function<String> stuff(BindableColumn<String> column, Integer start, BindableColumn<Integer> length, String addString) {
		return Function.of(STUFF, JDBCType.VARCHAR, extractParameters(column, start, length, addString));
	}
	
	static Function<String> stuff(BindableColumn<String> column, Integer start, Integer length, BindableColumn<String> addString) {
		return Function.of(STUFF, JDBCType.VARCHAR, extractParameters(column, start, length, addString));
	}
	
	static Function<String> stuff(BindableColumn<String> column, Integer start, Integer length, String addString) {
		return Function.of(STUFF, JDBCType.VARCHAR, extractParameters(column, start, length, addString));
	}
	
	static Function<String> stuff(String text, BindableColumn<Integer> start, BindableColumn<Integer> length, BindableColumn<String> addString) {
		return Function.of(STUFF, JDBCType.VARCHAR, extractParameters(text, start, length, addString));
	}
	
	static Function<String> stuff(String text, BindableColumn<Integer> start, BindableColumn<Integer> length, String addString) {
		return Function.of(STUFF, JDBCType.VARCHAR, extractParameters(text, start, length, addString));
	}
	
	static Function<String> stuff(String text, BindableColumn<Integer> start, Integer length, BindableColumn<String> addString) {
		return Function.of(STUFF, JDBCType.VARCHAR, extractParameters(text, start, length, addString));
	}
	
	static Function<String> stuff(String text, BindableColumn<Integer> start, Integer length, String addString) {
		return Function.of(STUFF, JDBCType.VARCHAR, extractParameters(text, start, length, addString));
	}
	
	static Function<String> stuff(String column, Integer start, BindableColumn<Integer> length, BindableColumn<String> addString) {
		return Function.of(STUFF, JDBCType.VARCHAR, extractParameters(column, start, length, addString));
	}
	
	static Function<String> stuff(String text, Integer start, BindableColumn<Integer> length, String addString) {
		return Function.of(STUFF, JDBCType.VARCHAR, extractParameters(text, start, length, addString));
	}
	
	static Function<String> stuff(String text, Integer start, Integer length, BindableColumn<String> addString) {
		return Function.of(STUFF, JDBCType.VARCHAR, extractParameters(text, start, length, addString));
	}
	
	static Function<String> stuff(String text, Integer start, Integer length, String addString) {
		return Function.of(STUFF, JDBCType.VARCHAR, extractParameters(text, start, length, addString));
	}
	
	static Function<String> substring(BindableColumn<String> column, BindableColumn<Integer> start, BindableColumn<Integer> length) {
		return Function.of(SUBSTRING, JDBCType.VARCHAR, extractParameters(column, start, length));
	}
	
	static Function<String> substring(BindableColumn<String> column, BindableColumn<Integer> start, Integer length) {
		return Function.of(SUBSTRING, JDBCType.VARCHAR, extractParameters(column, start, length));
	}
	
	static Function<String> substring(BindableColumn<String> column, Integer start, BindableColumn<Integer> length) {
		return Function.of(SUBSTRING, JDBCType.VARCHAR, extractParameters(column, start, length));
	}
	
	static Function<String> substring(BindableColumn<String> column, Integer start, Integer length) {
		return Function.of(SUBSTRING, JDBCType.VARCHAR, extractParameters(column, start, length));
	}
	
	static Function<String> substring(String text, BindableColumn<Integer> start, BindableColumn<Integer> length) {
		return Function.of(SUBSTRING, JDBCType.VARCHAR, extractParameters(text, start, length));
	}
	
	static Function<String> substring(String text, BindableColumn<Integer> start, Integer length) {
		return Function.of(SUBSTRING, JDBCType.VARCHAR, extractParameters(text, start, length));
	}
	
	static Function<String> substring(String text, Integer start, BindableColumn<Integer> length) {
		return Function.of(SUBSTRING, JDBCType.VARCHAR, extractParameters(text, start, length));
	}
	
	static Function<String> substring(String text, Integer start, Integer length) {
		return Function.of(SUBSTRING, JDBCType.VARCHAR, extractParameters(text, start, length));
	}
	
	static Function<String> upper(BindableColumn<String> column) {
		return Function.of(UPPER, JDBCType.VARCHAR, extractParameters(column));
	}
	
	static Function<String> upper(String text) {
		return Function.of(UPPER, JDBCType.VARCHAR, extractParameters(text));
	}
	
	static Function<Integer> abs(BindableColumn<Integer> column) {
		return Function.of(ABS, JDBCType.INTEGER, extractParameters(column));
	}
	
	static Function<Integer> abs(Integer number) {
		return Function.of(ABS, JDBCType.INTEGER, extractParameters(number));
	}
	
	static Function<Integer> avg(BindableColumn<Integer> column) {
		return Function.of(AVG, JDBCType.INTEGER, extractParameters(column));
	}
	
	static Function<Integer> avg(Integer number) {
		return Function.of(AVG, JDBCType.INTEGER, extractParameters(number));
	}
	
	static Function<Integer> ceiling(BindableColumn<Double> column) {
		return Function.of(CEILING, JDBCType.INTEGER, extractParameters(column));
	}
	
	static Function<Integer> ceiling(Double number) {
		return Function.of(CEILING, JDBCType.INTEGER, extractParameters(number));
	}
	
	static Function<Integer> count(BindableColumn<?> column) {
		return Function.of(COUNT, JDBCType.INTEGER, extractParameters(column));
	}
	
	static Function<Integer> floor(BindableColumn<Double> column) {
		return Function.of(FLOOR, JDBCType.INTEGER, extractParameters(column));
	}
	
	static Function<Integer> floor(Double number) {
		return Function.of(FLOOR, JDBCType.INTEGER, extractParameters(number));
	}
	
	static Function<Integer> max(BindableColumn<Integer> column) {
		return Function.of(MAX, JDBCType.INTEGER, extractParameters(column));
	}
	
	static Function<Integer> min(BindableColumn<Integer> column) {
		return Function.of(MIN, JDBCType.INTEGER, extractParameters(column));
	}
	
	static Function<Integer> rand() {
		return Function.of(RAND, JDBCType.INTEGER);
	}
	
	static Function<Integer> rand(BindableColumn<Integer> column) {
		return Function.of(RAND, JDBCType.INTEGER, extractParameters(column));
	}
	
	static Function<Integer> rand(Integer seed) {
		return Function.of(RAND, JDBCType.INTEGER, extractParameters(seed));
	}
	
	static Function<Integer> round(BindableColumn<Double> column, BindableColumn<Integer> decimal) {
		return Function.of(ROUND, JDBCType.INTEGER, extractParameters(column, decimal));
	}
	
	static Function<Integer> round(BindableColumn<Double> column, Integer decimal) {
		return Function.of(ROUND, JDBCType.INTEGER, extractParameters(column, decimal));
	}
	
	static Function<Integer> round(Double column, BindableColumn<Integer> decimal) {
		return Function.of(ROUND, JDBCType.INTEGER, extractParameters(column, decimal));
	}
	
	static Function<Integer> round(Double number, Integer decimal) {
		return Function.of(ROUND, JDBCType.INTEGER, extractParameters(number, decimal));
	}
	
	static Function<Integer> round(BindableColumn<Double> column, BindableColumn<Integer> decimal, BindableColumn<Integer> operation) {
		return Function.of(ROUND, JDBCType.INTEGER, extractParameters(column, decimal, operation));
	}
	
	static Function<Integer> round(BindableColumn<Double> column, BindableColumn<Integer> decimal, Integer operation) {
		return Function.of(ROUND, JDBCType.INTEGER, extractParameters(column, decimal, operation));
	}
	
	static Function<Integer> round(BindableColumn<Double> column, Integer decimal, BindableColumn<Integer> operation) {
		return Function.of(ROUND, JDBCType.INTEGER, extractParameters(column, decimal, operation));
	}
	
	static Function<Integer> round(BindableColumn<Double> column, Integer decimal, Integer operation) {
		return Function.of(ROUND, JDBCType.INTEGER, extractParameters(column, decimal, operation));
	}
	
	static Function<Integer> round(Double number, BindableColumn<Integer> decimal, BindableColumn<Integer> operation) {
		return Function.of(ROUND, JDBCType.INTEGER, extractParameters(number, decimal, operation));
	}
	
	static Function<Integer> round(Double number, BindableColumn<Integer> decimal, Integer operation) {
		return Function.of(ROUND, JDBCType.INTEGER, extractParameters(number, decimal, operation));
	}
	
	static Function<Integer> round(Double number, Integer decimal, BindableColumn<Integer> operation) {
		return Function.of(ROUND, JDBCType.INTEGER, extractParameters(number, decimal, operation));
	}
	
	static Function<Integer> round(Double number, Integer decimal, Integer operation) {
		return Function.of(ROUND, JDBCType.INTEGER, extractParameters(number, decimal, operation));
	}

	static Function<Integer> sign(BindableColumn<Integer> column) {
		return Function.of(SIGN, JDBCType.INTEGER, extractParameters(column));
	}
	
	static Function<Integer> sign(Integer seed) {
		return Function.of(SIGN, JDBCType.INTEGER, extractParameters(seed));
	}
	
	static Function<Integer> sum(BindableColumn<Integer> column) {
		return Function.of(SUM, JDBCType.INTEGER, extractParameters(column));
	}
	
	static Function<Integer> sum(Integer number) {
		return Function.of(SUM, JDBCType.INTEGER, extractParameters(number));
	}
	
	static Collection<Object> extractParameters(Object param1, Object... params) {
		Collection<Object> result = new LinkedList<>();
		result.add(param1);
		for (Object object: params) {
			if (object instanceof Object[]) {
				for (Object object2: (Object[]) object) {
					result.add(object2);	
				}
			} else {
				result.add(object);
			}
		}
		return result;
	}
}
