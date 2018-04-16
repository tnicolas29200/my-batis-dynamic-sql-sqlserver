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
package org.mybatis.dynamic.ext.sqlserver;

import java.sql.JDBCType;
import java.util.Date;

import org.mybatis.dynamic.ext.sql.select.ConvertStyle;
import org.mybatis.dynamic.ext.sql.select.DateInterval;
import org.mybatis.dynamic.ext.sql.select.Function;
import org.mybatis.dynamic.ext.sql.select.UnparametrizedColumn;
import org.mybatis.dynamic.ext.sql.select.UnquotedString;
import org.mybatis.dynamic.sql.BindableColumn;
import org.mybatis.dynamic.sql.SqlBuilder;

public interface SQLServerBuilder extends SqlBuilder {
	
	static Function<Integer> ascii(BindableColumn<String> character) {
		return SqlServerHelper.ascii(character);
    }
	
	static Function<Integer> ascii(String character) {
		return SqlServerHelper.ascii(character);
    }
	
	static Function<String> character(BindableColumn<Integer> ascii) {
		return SqlServerHelper.character(ascii);
	}
	
	static Function<String> character(Integer ascii) {
		return SqlServerHelper.character(ascii);
	}
	
	static Function<Integer> charindex(BindableColumn<String> text, BindableColumn<String> substring) {
		return SqlServerHelper.charindex(text, substring);
	}
	
	static Function<Integer> charindex(BindableColumn<String> text, String substring) {
		return SqlServerHelper.charindex(text, substring);
	}
	
	static Function<Integer> charindex(String text, BindableColumn<String> substring) {
		return SqlServerHelper.charindex(text, substring);
	}

	static Function<Integer> charindex(String text, String substring) {
		return SqlServerHelper.charindex(text, substring);
	}
	
	static Function<Integer> charindex(BindableColumn<String> text, BindableColumn<String> substring, BindableColumn<Integer> startPosition) {
		return SqlServerHelper.charindex(text, substring, startPosition);
	}
	
	static Function<Integer> charindex(BindableColumn<String> text, BindableColumn<String> substring, Integer startPosition) {
		return SqlServerHelper.charindex(text, substring, startPosition);
	}
	
	static Function<Integer> charindex(BindableColumn<String> text, String substring, BindableColumn<Integer> startPosition) {
		return SqlServerHelper.charindex(text, substring, startPosition);
	}
	
	static Function<Integer> charindex(BindableColumn<String> text, String substring, Integer startPosition) {
		return SqlServerHelper.charindex(text, substring, startPosition);
	}
	
	static Function<Integer> charindex(String text, BindableColumn<String> substring, BindableColumn<Integer> startPosition) {
		return SqlServerHelper.charindex(text, substring, startPosition);
	}
	
	static Function<Integer> charindex(String text, BindableColumn<String> substring, Integer startPosition) {
		return SqlServerHelper.charindex(text, substring, startPosition);
	}
	
	static Function<Integer> charindex(String text, String substring, BindableColumn<Integer> startPosition) {
		return SqlServerHelper.charindex(text, substring, startPosition);
	}
	
	static Function<Integer> charindex(String text, String substring, Integer startPosition) {
		return SqlServerHelper.charindex(text, substring, startPosition);
	}
	
	@SafeVarargs
	static Function<String> concat(BindableColumn<String> text1, BindableColumn<String> text2, Object... otherText) {
		return SqlServerHelper.concat(text1, text2, otherText);	
	}
	
	@SafeVarargs
	static Function<String> concat(BindableColumn<String> text1, String text2, Object... otherText) {
		return SqlServerHelper.concat(text1, text2, otherText);	
	}
	
	@SafeVarargs
	static Function<String> concat(String text1, BindableColumn<String> text2, Object... otherText) {
		return SqlServerHelper.concat(text1, text2, otherText);
	}
	
	@SafeVarargs
	static Function<String> concat(String text1, String text2, Object... otherText) {
		return SqlServerHelper.concat(text1, text2, otherText);
	}
	
	static Function<Integer> datalength(BindableColumn<String> data) {
		return SqlServerHelper.datalength(data);
	}
	
	static Function<Integer> datalength(String data) {
		return SqlServerHelper.datalength(data);
	}
	
	static Function<String> left(BindableColumn<String> text, BindableColumn<Integer> numberOfChars) {
		return SqlServerHelper.left(text, numberOfChars);
	}
	
	static Function<String> left(BindableColumn<String> text, Integer numberOfChars) {
		return SqlServerHelper.left(text, numberOfChars);
	}
	
	static Function<String> left(String text, BindableColumn<Integer> numberOfChars) {
		return SqlServerHelper.left(text, numberOfChars);
	}
	
	static Function<String> left(String text, Integer numberOfChars) {
		return SqlServerHelper.left(text, numberOfChars);
	}
	
	static Function<Integer> len(BindableColumn<String> text) {
		return SqlServerHelper.len(text);
	}
	
	static Function<Integer> len(String text) {
		return SqlServerHelper.len(text);
	}
	
	static Function<String> lower(BindableColumn<String> text) {
		return SqlServerHelper.lower(text);
	}
	
	static Function<String> lower(String text) {
		return SqlServerHelper.lower(text);
	}
	
	static Function<String> ltrim(BindableColumn<String> text) {
		return SqlServerHelper.ltrim(text);
	}
	
	static Function<String> ltrim(String text) {
		return SqlServerHelper.ltrim(text);
	}
	
	static Function<String> nchar(BindableColumn<Integer> text) {
		return SqlServerHelper.nchar(text);
    }
	
	static Function<String> nchar(Integer text) {
		return SqlServerHelper.nchar(text);
    }
	
	static Function<Integer> patindex(BindableColumn<String> pattern, BindableColumn<String> text) {
		return SqlServerHelper.patindex(pattern, text);
	}
	
	static Function<Integer> patindex(String pattern, BindableColumn<String> text) {
		return SqlServerHelper.patindex(pattern, text);
	}
	
	static Function<Integer> patindex(BindableColumn<String> pattern, String text) {
		return SqlServerHelper.patindex(pattern, text);
	}
	
	static Function<Integer> patindex(String pattern, String text) {
		return SqlServerHelper.patindex(pattern, text);
	}
	
	static Function<String> replace(BindableColumn<String> text, BindableColumn<String> stringToReplace, BindableColumn<String> replacementString) {
		return SqlServerHelper.replace(text, stringToReplace, replacementString);
	}
	
	static Function<String> replace(BindableColumn<String> text, BindableColumn<String> stringToReplace, String replacementString) {
		return SqlServerHelper.replace(text, stringToReplace, replacementString);
	}
	
	static Function<String> replace(BindableColumn<String> text, String stringToReplace, BindableColumn<String> replacementString) {
		return SqlServerHelper.replace(text, stringToReplace, replacementString);
	}
	
	static Function<String> replace(BindableColumn<String> text, String stringToReplace, String replacementString) {
		return SqlServerHelper.replace(text, stringToReplace, replacementString);
	}
	
	static Function<String> replace(String text, BindableColumn<String> stringToReplace, BindableColumn<String> replacementString) {
		return SqlServerHelper.replace(text, stringToReplace, replacementString);
	}
	
	static Function<String> replace(String text, BindableColumn<String> stringToReplace, String replacementString) {
		return SqlServerHelper.replace(text, stringToReplace, replacementString);
	}
	
	static Function<String> replace(String text, String stringToReplace, BindableColumn<String> replacementString) {
		return SqlServerHelper.replace(text, stringToReplace, replacementString);
	}
	
	static Function<String> replace(String text, String stringToReplace, String replacementString) {
		return SqlServerHelper.replace(text, stringToReplace, replacementString);
	}
	
	static Function<String> right(BindableColumn<String> text, BindableColumn<Integer> numberOfChars) {
		return SqlServerHelper.right(text, numberOfChars);
	}
	
	static Function<String> right(BindableColumn<String> text, Integer numberOfChars) {
		return SqlServerHelper.right(text, numberOfChars);
	}
	
	static Function<String> right(String text, BindableColumn<Integer> numberOfChars) {
		return SqlServerHelper.right(text, numberOfChars);
	}
	
	static Function<String> right(String text, Integer numberOfChars) {
		return SqlServerHelper.right(text, numberOfChars);
	}
	
	static Function<String> rtrim(BindableColumn<String> text) {
		return SqlServerHelper.rtrim(text);
	}
	
	static Function<String> rtrim(String text) {
		return SqlServerHelper.rtrim(text);
	}
	
	static Function<String> space(BindableColumn<Integer> nbSpaces) {
		return SqlServerHelper.space(nbSpaces);
	}
	
	static Function<String> space(Integer nbSpaces) {
		return SqlServerHelper.space(nbSpaces);
	}
	
	static Function<String> str(BindableColumn<Integer> number) {
		return SqlServerHelper.str(number);
	}
	
	static Function<String> str(Integer number) {
		return SqlServerHelper.str(number);
	}
	
	static Function<String> str(BindableColumn<Integer> number, BindableColumn<Integer> length) {
		return SqlServerHelper.str(number, length);
	}
	
	static Function<String> str(BindableColumn<Integer> number, Integer length) {
		return SqlServerHelper.str(number, length);
	}
	
	static Function<String> str(Integer number, BindableColumn<Integer> length) {
		return SqlServerHelper.str(number, length);
	}
	
	static Function<String> str(Integer number, Integer length) {
		return SqlServerHelper.str(number, length);
	}
	
	static Function<String> str(BindableColumn<Integer> number, BindableColumn<Integer> length, BindableColumn<Integer> decimalPlaces) {
		return SqlServerHelper.str(number, length, decimalPlaces);
	}
	
	static Function<String> str(BindableColumn<Integer> number, BindableColumn<Integer> length, Integer decimalPlaces) {
		return SqlServerHelper.str(number, length, decimalPlaces);
	}
	
	static Function<String> str(BindableColumn<Integer> number, Integer length, BindableColumn<Integer> decimalPlaces) {
		return SqlServerHelper.str(number, length, decimalPlaces);
	}
	
	static Function<String> str(BindableColumn<Integer> number, Integer length, Integer decimalPlaces) {
		return SqlServerHelper.str(number, length, decimalPlaces);
	}
	
	static Function<String> str(Integer number, BindableColumn<Integer> length, BindableColumn<Integer> decimalPlaces) {
		return SqlServerHelper.str(number, length, decimalPlaces);
	}
	
	static Function<String> str(Integer number, BindableColumn<Integer> length, Integer decimalPlaces) {
		return SqlServerHelper.str(number, length, decimalPlaces);
	}
	
	static Function<String> str(Integer number, Integer length, BindableColumn<Integer> decimalPlaces) {
		return SqlServerHelper.str(number, length, decimalPlaces);
	}
	
	static Function<String> str(Integer number, Integer length, Integer decimalPlaces) {
		return SqlServerHelper.str(number, length, decimalPlaces);
	}
	
	static Function<String> stuff(BindableColumn<String> text, BindableColumn<Integer> start, BindableColumn<Integer> length, BindableColumn<String> addString) {
		return SqlServerHelper.stuff(text, start, length, addString);
	}
	
	static Function<String> stuff(BindableColumn<String> text, BindableColumn<Integer> start, BindableColumn<Integer> length, String addString) {
		return SqlServerHelper.stuff(text, start, length, addString);
	}
	
	static Function<String> stuff(BindableColumn<String> text, BindableColumn<Integer> start, Integer length, BindableColumn<String> addString) {
		return SqlServerHelper.stuff(text, start, length, addString);
	}
	
	static Function<String> stuff(BindableColumn<String> text, BindableColumn<Integer> start, Integer length, String addString) {
		return SqlServerHelper.stuff(text, start, length, addString);
	}
	
	static Function<String> stuff(BindableColumn<String> text, Integer start, BindableColumn<Integer> length, BindableColumn<String> addString) {
		return SqlServerHelper.stuff(text, start, length, addString);
	}
	
	static Function<String> stuff(BindableColumn<String> text, Integer start, BindableColumn<Integer> length, String addString) {
		return SqlServerHelper.stuff(text, start, length, addString);
	}
	
	static Function<String> stuff(BindableColumn<String> text, Integer start, Integer length, BindableColumn<String> addString) {
		return SqlServerHelper.stuff(text, start, length, addString);
	}
	
	static Function<String> stuff(BindableColumn<String> text, Integer start, Integer length, String addString) {
		return SqlServerHelper.stuff(text, start, length, addString);
	}
	
	static Function<String> stuff(String text, BindableColumn<Integer> start, BindableColumn<Integer> length, BindableColumn<String> addString) {
		return SqlServerHelper.stuff(text, start, length, addString);
	}
	
	static Function<String> stuff(String text, BindableColumn<Integer> start, BindableColumn<Integer> length, String addString) {
		return SqlServerHelper.stuff(text, start, length, addString);
	}
	
	static Function<String> stuff(String text, BindableColumn<Integer> start, Integer length, BindableColumn<String> addString) {
		return SqlServerHelper.stuff(text, start, length, addString);
	}
	
	static Function<String> stuff(String text, BindableColumn<Integer> start, Integer length, String addString) {
		return SqlServerHelper.stuff(text, start, length, addString);
	}
	
	static Function<String> stuff(String text, Integer start, BindableColumn<Integer> length, BindableColumn<String> addString) {
		return SqlServerHelper.stuff(text, start, length, addString);
	}
	
	static Function<String> stuff(String text, Integer start, BindableColumn<Integer> length, String addString) {
		return SqlServerHelper.stuff(text, start, length, addString);
	}
	
	static Function<String> stuff(String text, Integer start, Integer length, BindableColumn<String> addString) {
		return SqlServerHelper.stuff(text, start, length, addString);
	}
	
	static Function<String> stuff(String text, Integer start, Integer length, String addString) {
		return SqlServerHelper.stuff(text, start, length, addString);
	}
	
	static Function<String> substring(BindableColumn<String> text, BindableColumn<Integer> start, BindableColumn<Integer> length) {
		return SqlServerHelper.substring(text, start, length);
	}
	
	static Function<String> substring(BindableColumn<String> text, BindableColumn<Integer> start, Integer length) {
		return SqlServerHelper.substring(text, start, length);
	}
	
	static Function<String> substring(BindableColumn<String> text, Integer start, BindableColumn<Integer> length) {
		return SqlServerHelper.substring(text, start, length);
	}
	
	static Function<String> substring(BindableColumn<String> text, Integer start, Integer length) {
		return SqlServerHelper.substring(text, start, length);
	}
	
	static Function<String> substring(String text, BindableColumn<Integer> start, BindableColumn<Integer> length) {
		return SqlServerHelper.substring(text, start, length);
	}
	
	static Function<String> substring(String text, BindableColumn<Integer> start, Integer length) {
		return SqlServerHelper.substring(text, start, length);
	}
	
	static Function<String> substring(String text, Integer start, BindableColumn<Integer> length) {
		return SqlServerHelper.substring(text, start, length);
	}
	
	static Function<String> substring(String text, Integer start, Integer length) {
		return SqlServerHelper.substring(text, start, length);
	}
	
	static Function<String> upper(BindableColumn<String> text) {
		return SqlServerHelper.upper(text);
	}
	
	static Function<String> upper(String text) {
		return SqlServerHelper.upper(text);
	}
	
	static Function<Integer> abs(BindableColumn<Integer> number) {
		return SqlServerHelper.abs(number);
	}
	
	static Function<Integer> abs(Integer number) {
		return SqlServerHelper.abs(number);
	}
	
	static Function<Integer> avg(BindableColumn<Integer> number) {
		return SqlServerHelper.avg(number);
	}
	
	static Function<Integer> avg(Integer number) {
		return SqlServerHelper.avg(number);
	}
	
	static Function<Integer> ceiling(BindableColumn<Double> number) {
		return SqlServerHelper.ceiling(number);
	}
	
	static Function<Integer> ceiling(Double number) {
		return SqlServerHelper.ceiling(number);
	}
	
	static Function<Integer> count(BindableColumn<?> column) {
		return SqlServerHelper.count(column);
	}
	
	static Function<Integer> floor(BindableColumn<Double> number) {
		return SqlServerHelper.floor(number);
	}
	
	static Function<Integer> floor(Double number) {
		return SqlServerHelper.floor(number);
	}
	
	static Function<Integer> max(BindableColumn<Integer> number) {
		return SqlServerHelper.max(number);
	}
	
	static Function<Integer> min(BindableColumn<Integer> number) {
		return SqlServerHelper.min(number);
	}
	
	static Function<Integer> rand() {
		return SqlServerHelper.rand();
	}
	
	static Function<Integer> rand(BindableColumn<Integer> seed) {
		return SqlServerHelper.rand(seed);
	}
	
	static Function<Integer> rand(Integer seed) {
		return SqlServerHelper.rand(seed);
	}
	
	static Function<Integer> round(BindableColumn<Double> number, BindableColumn<Integer> decimal) {
		return SqlServerHelper.round(number, decimal);
	}
	
	static Function<Integer> round(BindableColumn<Double> number, Integer decimal) {
		return SqlServerHelper.round(number, decimal);
	}
	
	static Function<Integer> round(Double number, BindableColumn<Integer> decimal) {
		return SqlServerHelper.round(number, decimal);
	}
	
	static Function<Integer> round(Double number, Integer decimal) {
		return SqlServerHelper.round(number, decimal);
	}
	
	static Function<Integer> round(BindableColumn<Double> number, BindableColumn<Integer> decimal, BindableColumn<Integer> operation) {
		return SqlServerHelper.round(number, decimal, operation);
	}
	
	static Function<Integer> round(BindableColumn<Double> number, BindableColumn<Integer> decimal, Integer operation) {
		return SqlServerHelper.round(number, decimal, operation);
	}
	
	static Function<Integer> round(BindableColumn<Double> number, Integer decimal, BindableColumn<Integer> operation) {
		return SqlServerHelper.round(number, decimal, operation);
	}
	
	static Function<Integer> round(BindableColumn<Double> number, Integer decimal, Integer operation) {
		return SqlServerHelper.round(number, decimal, operation);
	}
	
	static Function<Integer> round(Double number, BindableColumn<Integer> decimal, BindableColumn<Integer> operation) {
		return SqlServerHelper.round(number, decimal, operation);
	}
	
	static Function<Integer> round(Double number, BindableColumn<Integer> decimal, Integer operation) {
		return SqlServerHelper.round(number, decimal, operation);
	}
	
	static Function<Integer> round(Double number, Integer decimal, BindableColumn<Integer> operation) {
		return SqlServerHelper.round(number, decimal, operation);
	}
	
	static Function<Integer> round(Double number, Integer decimal, Integer operation) {
		return SqlServerHelper.round(number, decimal, operation);
	}

	static Function<Integer> sign(BindableColumn<Integer> number) {
		return SqlServerHelper.sign(number);
	}
	
	static Function<Integer> sign(Integer number) {
		return SqlServerHelper.sign(number);
	}
	
	static Function<Integer> sum(BindableColumn<Integer> number) {
		return SqlServerHelper.sum(number);
	}
	
	static Function<Integer> sum(Integer number) {
		return SqlServerHelper.sum(number);
	}
	
	static Function<Date> currentTimeStamp() {
		return SqlServerHelper.currentTimeStamp();
	}
	
	static Function<Date> dateAdd(BindableColumn<String> interval, BindableColumn<Integer> number, BindableColumn<Date> date) {
		
		return SqlServerHelper.dateAdd(interval, number, date);
	}
	
	static Function<Date> dateAdd(BindableColumn<String> interval, BindableColumn<Integer> number, Date date) {
		return SqlServerHelper.dateAdd(interval, number, date);
	}
	
	static Function<Date> dateAdd(BindableColumn<String> interval, Integer number, BindableColumn<Date> date) {
		return SqlServerHelper.dateAdd(interval, number, date);
	}
	
	static Function<Date> dateAdd(BindableColumn<String> interval, Integer number, Date date) {
		return SqlServerHelper.dateAdd(interval, number, date);
	}
	
	static Function<Date> dateAdd(DateInterval interval, BindableColumn<Integer> number, BindableColumn<Date> date) {
		return SqlServerHelper.dateAdd(interval, number, date);
	}
	
	static Function<Date> dateAdd(DateInterval interval, BindableColumn<Integer> number, Date date) {
		return SqlServerHelper.dateAdd(interval, number, date);
	}
	
	static Function<Date> dateAdd(DateInterval interval, Integer number, BindableColumn<Date> date) {
		return SqlServerHelper.dateAdd(interval, number, date);
	}
	
	static Function<Date> dateAdd(DateInterval interval, Integer number, Date date) {
		return SqlServerHelper.dateAdd(interval, number, date);
	}
	
	static Function<Date> dateDiff(BindableColumn<String> interval, BindableColumn<Date> date1, BindableColumn<Date> date2) {
		return SqlServerHelper.dateDiff(interval, date1, date2);
	}
	
	static Function<Date> dateDiff(BindableColumn<String> interval, BindableColumn<Date> date1, Date date2) {
		return SqlServerHelper.dateDiff(interval, date1, date2);
	}
	
	static Function<Date> dateDiff(BindableColumn<String> interval, Date date1, BindableColumn<Date> date2) {
		return SqlServerHelper.dateDiff(interval, date1, date2);
	}
	
	static Function<Date> dateDiff(BindableColumn<String> interval, Date date1, Date date2) {
		return SqlServerHelper.dateDiff(interval, date1, date2);
	}
	
	static Function<Date> dateDiff(DateInterval interval, BindableColumn<Date> date1, BindableColumn<Date> date2) {
		return SqlServerHelper.dateDiff(interval, date1, date2);
	}
	
	static Function<Date> dateDiff(DateInterval interval, BindableColumn<Date> date1, Date date2) {
		return SqlServerHelper.dateDiff(interval, date1, date2);
	}
	
	static Function<Date> dateDiff(DateInterval interval, Date date1, BindableColumn<Date> date2) {
		return SqlServerHelper.dateDiff(interval, date1, date2);
	}
	
	static Function<Date> dateDiff(DateInterval interval, Date date1, Date date2) {
		return SqlServerHelper.dateDiff(interval, date1, date2);
	}
	
	static Function<Date> dateName(BindableColumn<String> interval, BindableColumn<Date> date) {
		return SqlServerHelper.dateName(interval, date);
	}
	
	static Function<Date> dateName(BindableColumn<String> interval, Date date) {
		return SqlServerHelper.dateName(interval, date);
	}
	
	static Function<Date> dateName(DateInterval interval, BindableColumn<Date> date) {
		return SqlServerHelper.dateName(interval, date);
	}
	
	static Function<Date> dateName(DateInterval interval, Date date) {
		return SqlServerHelper.dateName(interval, date);
	}
	
	static Function<Date> datePart(BindableColumn<String> interval, BindableColumn<Date> date) {
		return SqlServerHelper.datePart(interval, date);
	}
	
	static Function<Date> datePart(BindableColumn<String> interval, Date date) {
		return SqlServerHelper.datePart(interval, date);
	}
	
	static Function<Date> datePart(DateInterval interval, BindableColumn<Date> date) {
		return SqlServerHelper.datePart(interval, date);
	}
	
	static Function<Date> datePart(DateInterval interval, Date date) {
		return SqlServerHelper.datePart(interval, date);
	}
	
	static Function<Integer> day(BindableColumn<Date> date) {
		return SqlServerHelper.day(date);
	}
	
	static Function<Integer> day(Date date) {
		return SqlServerHelper.day(date);
	}
	
	static Function<Date> getDate() {
		return SqlServerHelper.getDate();
	}
	
	static Function<Date> getUtcDate() {
		return SqlServerHelper.getUtcDate();
	}
	
	static Function<Integer> month(BindableColumn<Date> date) {
		return SqlServerHelper.month(date);
	}
	
	static Function<Integer> month(Date date) {
		return SqlServerHelper.month(date);
	}
	
	static Function<Integer> year(BindableColumn<Date> date) {
		return SqlServerHelper.year(date);
	}
	
	static Function<Integer> year(Date date) {
		return SqlServerHelper.year(date);
	}
	
	static <T, U> Function<U> cast(BindableColumn<T> value, U dataType, JDBCType jdbcType) {
		UnquotedString result = new UnquotedString(" AS " + jdbcType.getName());
		UnparametrizedColumn<T> column = new UnparametrizedColumn<>(value);
		return SqlServerHelper.cast(dataType, jdbcType, column, result);
	}
	
	static <T, U> Function<U> cast(T value, U dataType, JDBCType jdbcType) {
		UnquotedString result = new UnquotedString(value + " AS " + jdbcType.getName());
		return SqlServerHelper.cast(dataType, jdbcType, result);
	}
	
	static <T, U> Function<U> cast(BindableColumn<T> value, U dataType, JDBCType jdbcType, Integer length) {
		UnquotedString result = new UnquotedString(" AS " + jdbcType.getName() + "(" + length + ")");
		UnparametrizedColumn<T> column = new UnparametrizedColumn<>(value);
		return SqlServerHelper.cast(dataType, jdbcType, column, result);
	}
	
	static <T, U> Function<U> cast(T value, U dataType, JDBCType jdbcType, Integer length) {
		UnquotedString result = new UnquotedString(value + " AS " + jdbcType.getName() + "(" + length + ")");
		return SqlServerHelper.cast(dataType, jdbcType, result);
	}
	
	static <T, U> Function<U> convert(U dataType, JDBCType jdbcType, BindableColumn<T> column) {
		UnquotedString result = new UnquotedString(jdbcType.getName());
		return SqlServerHelper.convert(dataType, jdbcType, result, column);
	}
	
	static <T, U> Function<U> convert(U dataType, JDBCType jdbcType, T value) {
		UnquotedString result = new UnquotedString(jdbcType.getName());
		return SqlServerHelper.convert(dataType, jdbcType, result, value);
	}
	
	static <T, U> Function<U> convert(U dataType, JDBCType jdbcType, Integer length, BindableColumn<T> column) {
		UnquotedString result = new UnquotedString(jdbcType.getName() + "(" + length + ")");
		return SqlServerHelper.convert(dataType, jdbcType, result, column);
	}
	
	static <T, U> Function<U> convert(U dataType, JDBCType jdbcType, Integer length, T value) {
		UnquotedString result = new UnquotedString(jdbcType.getName() + "(" + length + ")");
		return SqlServerHelper.convert(dataType, jdbcType, result, value);
	}	
	
	static <T, U> Function<U> convert(U dataType, JDBCType jdbcType, BindableColumn<T> column, ConvertStyle style) {
		UnquotedString result = new UnquotedString(jdbcType.getName());
		return SqlServerHelper.convert(dataType, jdbcType, result, column, style);
	}
	
	static <T, U> Function<U> convert(U dataType, JDBCType jdbcType, T value, ConvertStyle style) {
		UnquotedString result = new UnquotedString(jdbcType.getName());
		return SqlServerHelper.convert(dataType, jdbcType, result, value, style);
	}
	
	static <T, U> Function<U> convert(U dataType, JDBCType jdbcType, Integer length, BindableColumn<T> column, ConvertStyle style) {
		UnquotedString result = new UnquotedString(jdbcType.getName() + "(" + length + ")");
		return SqlServerHelper.convert(dataType, jdbcType, result, column, style);
	}
	
	static <T, U> Function<U> convert(U dataType, JDBCType jdbcType, Integer length, T value, ConvertStyle style) {
		UnquotedString result = new UnquotedString(jdbcType.getName() + "(" + length + ")");
		return SqlServerHelper.convert(dataType, jdbcType, result, value, style);
	}
	
	@SuppressWarnings("unchecked")
	static <T> Function<T> coalesce(BindableColumn<T>... columns) {
		return SqlServerHelper.coalesce(JDBCType.OTHER, columns);
	}
	
	static Function<String> currentUser() {
		return SqlServerHelper.currentUser();
	}
	
	static Function<Boolean> isDate(Object date) {
		return SqlServerHelper.isDate(date);
	}
	
	static Function<Boolean> isNull(Object date) {
		return SqlServerHelper.isNull(date);
	}
	
	static Function<Boolean> isNumeric(Object date) {
		return SqlServerHelper.isNumeric(date);
	}
	
	static <T> Function<T> nullIf(T column, Object column2) {
		return SqlServerHelper.nullIf(column, column2);
	}
	
	static Function<String> sessionUser() {
		return SqlServerHelper.sessionUser();
	}
	
	static Function<String> sessionProperty(String property) {
		return SqlServerHelper.sessionProperty(property);
	}
	
	static Function<String> sessionProperty(BindableColumn<String> property) {
		return SqlServerHelper.sessionProperty(property);
	}
	
	static Function<String> systemUser() {
		return SqlServerHelper.systemUser();
	}
	
	static Function<String> userName() {
		return SqlServerHelper.userName();
	}
}
