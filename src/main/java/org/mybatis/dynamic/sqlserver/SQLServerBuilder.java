package org.mybatis.dynamic.sqlserver;

import java.util.Arrays;

import org.mybatis.dynamic.sql.BindableColumn;
import org.mybatis.dynamic.sqlserver.select.function.string.Ascii;
import org.mybatis.dynamic.sqlserver.select.function.string.Char;
import org.mybatis.dynamic.sqlserver.select.function.string.CharIndex;
import org.mybatis.dynamic.sqlserver.select.function.string.Concat;

public interface SQLServerBuilder {
	static Ascii ascii(BindableColumn<String> column) {
        return Ascii.of(column);
    }
	
	static Char character(BindableColumn<Integer> column) {
		return Char.of(column);
	}
	
	static CharIndex charindex(BindableColumn<String> column, String substring) {
		return CharIndex.of(column, substring);
	}
	
	static CharIndex charindex(BindableColumn<String> column, String substring, Integer startPosition) {
		return CharIndex.of(column, substring, startPosition);
	}
	
	@SafeVarargs
	static Concat concat(BindableColumn<String> firstColumn, BindableColumn<String> secondColumn, BindableColumn<String>... subsequentColumns) {
		return Concat.of(firstColumn, secondColumn,  Arrays.asList(subsequentColumns));
	}
}
