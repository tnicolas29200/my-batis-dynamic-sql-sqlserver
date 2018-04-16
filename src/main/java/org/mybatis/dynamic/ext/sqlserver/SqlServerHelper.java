package org.mybatis.dynamic.ext.sqlserver;

import java.sql.JDBCType;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import org.mybatis.dynamic.ext.sql.select.Function;

public interface SqlServerHelper {
	static Function<Integer> ascii(Object... columns) {
		return of("ASCII", JDBCType.INTEGER, columns);
    }
	
	static Function<String> character(Object... columns) {
		return of("CHAR", JDBCType.VARCHAR, columns);
	}
	
	static Function<Integer> charindex(Object... columns) {
		return of("CHARINDEX", JDBCType.INTEGER, columns);
	}
	
	static Function<String> concat(Object... columns) {
		return of("CONCAT", JDBCType.VARCHAR, columns);	
	}
	
	static Function<Integer> datalength(Object... columns) {
		return of("DATALENGTH", JDBCType.INTEGER, columns);
	}
	
	static Function<String> left(Object... columns) {
		return of("LEFT", JDBCType.VARCHAR, columns);
	}
	
	static Function<Integer> len(Object... columns) {
		return of("LEN", JDBCType.INTEGER, columns);
	}
	
	static Function<String> lower(Object... columns) {
		return of("LOWER", JDBCType.VARCHAR, columns);
	}
	
	static Function<String> ltrim(Object... columns) {
		return of("LTRIM", JDBCType.VARCHAR, columns);
	}
	
	static Function<String> nchar(Object... columns) {
        return of("NCHAR", JDBCType.NVARCHAR, columns);
    }
	
	static Function<Integer> patindex(Object... columns) {
		return of("PATINDEX", JDBCType.INTEGER, columns);
	}
	
	static Function<String> replace(Object... columns) {
		return of("REPLACE", JDBCType.VARCHAR, columns);
	}
	
	static Function<String> right(Object... columns) {
		return of("RIGHT", JDBCType.VARCHAR, columns);
	}
	
	static Function<String> rtrim(Object... columns) {
		return of("RTRIM", JDBCType.VARCHAR, columns);
	}
	
	static Function<String> space(Object... columns) {
		return of("SPACE", JDBCType.VARCHAR, columns);
	}
	
	static Function<String> str(Object... columns) {
		return of("STR", JDBCType.VARCHAR, columns);
	}
	
	static Function<String> stuff(Object... columns) {
		return of("STUFF", JDBCType.VARCHAR, columns);
	}
	
	static Function<String> substring(Object... columns) {
		return of("SUBSTRING", JDBCType.VARCHAR, columns);
	}
	
	static Function<String> upper(Object... columns) {
		return of("UPPER", JDBCType.VARCHAR, columns);
	}
	
	static Function<Integer> abs(Object... columns) {
		return of("ABS", JDBCType.INTEGER, columns);
	}
	
	static Function<Integer> avg(Object... columns) {
		return of("AVG", JDBCType.INTEGER, columns);
	}
	
	static Function<Integer> ceiling(Object... columns) {
		return of("CEILING", JDBCType.INTEGER, columns);
	}
	
	static Function<Integer> count(Object... columns) {
		return of("COUNT", JDBCType.INTEGER, columns);
	}
	
	static Function<Integer> floor(Object... columns) {
		return of("FLOOR", JDBCType.INTEGER, columns);
	}
	
	static Function<Integer> max(Object... columns) {
		return of("MAX", JDBCType.INTEGER, columns);
	}
	
	static Function<Integer> min(Object... columns) {
		return of("MIN", JDBCType.INTEGER, columns);
	}
	
	static Function<Integer> rand(Object... columns) {
		return of("RAND", JDBCType.INTEGER, columns);
	}
	
	static Function<Integer> round(Object... columns) {
		return of("ROUND", JDBCType.INTEGER, columns);
	}
	
	static Function<Integer> sign(Object... columns) {
		return of("SIGN", JDBCType.INTEGER, columns);
	}
	
	static Function<Integer> sum(Object... columns) {
		return of("SUM", JDBCType.INTEGER, columns);
	}
	
	static Function<Date> currentTimeStamp(Object... columns) {
		return of("CURRENT_TIMESTAMP", JDBCType.DATE, columns);
	}
	
	static Function<Date> dateAdd(Object... columns) {
		return of("DATEADD", JDBCType.DATE, columns);
	}
	
	static Function<Date> dateDiff(Object... columns) {
		return of("DATEDIFF", JDBCType.DATE, columns);
	}
	
	static Function<Date> dateName(Object... columns) {
		return of("DATENAME", JDBCType.DATE, columns);
	}
	
	static Function<Date> datePart(Object... columns) {
		return of("DATEPART", JDBCType.DATE, columns);
	}
	
	static Function<Integer> day(Object... columns) {
		return of("DAY", JDBCType.INTEGER, columns);
	}
	
	static Function<Date> getDate(Object... columns) {
		return of("GETDATE", JDBCType.DATE, columns);
	}
	
	static Function<Date> getUtcDate(Object... columns) {
		return of("GETUTCDATE", JDBCType.DATE, columns);
	}
	
	static Function<Integer> month(Object... columns) {
		return of("MONTH", JDBCType.INTEGER, columns);
	}
	
	static Function<Integer> year(Object... columns) {
		return of("YEAR", JDBCType.INTEGER, columns);
	}
	
	static <U> Function<U> cast(U dataType, JDBCType type, Object... columns) {
		return of("CAST", type, columns);
	}
	
	static <U> Function<U> convert(U dataType, JDBCType type, Object... columns) {
		return of("CONVERT", type, columns);
	}
	
	static <U> Function<U> coalesce(JDBCType type, Object... columns) {
		return of("COALESCE", type, columns);
	}
	
	static Function<String> currentUser() {
		return of("CURRENT_USER", JDBCType.VARCHAR);
	}
	
	static Function<Boolean> isDate(Object... columns) {
		return of("ISDATE", JDBCType.BOOLEAN, columns);
	}
	
	static Function<Boolean> isNull(Object... columns) {
		return of("ISNULL", JDBCType.BOOLEAN, columns);
	}
	
	static Function<Boolean> isNumeric(Object... columns) {
		return of("ISNUMERIC", JDBCType.BOOLEAN, columns);
	}
	
	static <U> Function<U> nullIf(U column1, Object column2) {
		return of ("NULLIF", JDBCType.NULL, column1, column2);
	}
	
	static Function<String> sessionUser() {
		return of("SESSION_USER", JDBCType.VARCHAR);
	}
	
	static Function<String> sessionProperty(Object... columns) {
		return of("SESSIONPROPERTY", JDBCType.VARCHAR, columns);
	}
	
	static Function<String> systemUser() {
		return of("SYSTEM_USER", JDBCType.VARCHAR);
	}
	
	static Function<String> userName() {
		return of("USER_NAME", JDBCType.VARCHAR);
	}
	
	static <T> Function<T> of (String functionName, JDBCType type, Object...params) {
		return Function.of(functionName, type, extractParameters(params));
	}
	
	static Collection<Object> extractParameters(Object... params) {
		Collection<Object> result = new LinkedList<>();
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
