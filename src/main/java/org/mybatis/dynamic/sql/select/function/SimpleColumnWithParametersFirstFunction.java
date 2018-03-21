package org.mybatis.dynamic.sql.select.function;

import java.sql.JDBCType;
import java.util.List;
import java.util.Optional;

import org.mybatis.dynamic.sql.BindableColumn;
import org.mybatis.dynamic.sql.render.TableAliasCalculator;

public class SimpleColumnWithParametersFirstFunction<T, U extends AbstractFunction<T, U>> extends SimpleColumnWithParametersFunction<T, U>  {
    
    protected SimpleColumnWithParametersFirstFunction(BindableColumn<T> column, String functionName, List<Object> parameters) {
		super(column, functionName, parameters);
	}
    
    protected SimpleColumnWithParametersFirstFunction(BindableColumn<T> column, String functionName, List<Object> parameters, JDBCType jdbcType) {
		super(column, functionName, parameters, jdbcType);
	}

	@Override
    public String renderWithTableAlias(TableAliasCalculator tableAliasCalculator) {
		StringBuilder builder = new StringBuilder(functionName + "(");
		int index = 0;
		for (Object parameter: this.parameters) {
			if (index > 0) {
				builder.append(", ");
			}
			index++;
			if (parameter instanceof String) {
				builder.append("'" + parameter + "'");
			} else {
				builder.append(parameter);
			}
		}
		builder.append(", " + column.renderWithTableAlias(tableAliasCalculator));
		builder.append(")");
		return builder.toString();
    }
	
	public static <T, U extends AbstractFunction<T, U>> SimpleColumnWithParametersFirstFunction<T, U> of(BindableColumn<T> column, String functionName, List<Object> parameters) {
        return new SimpleColumnWithParametersFirstFunction<T, U>(column, functionName, parameters);
    }

	public static <T, U extends AbstractFunction<T, U>> SimpleColumnWithParametersFirstFunction<T, U> of(BindableColumn<T> column, String functionName, List<Object> parameters, JDBCType jdbcType) {
        return new SimpleColumnWithParametersFirstFunction<T, U>(column, functionName, parameters, jdbcType);
    }
	
	@Override
	protected SimpleColumnWithParametersFirstFunction<T, U> copy() {
		return new SimpleColumnWithParametersFirstFunction<>(column, functionName, parameters, jdbcType);
	}
	
	@Override
    public Optional<JDBCType> jdbcType() {
		if (this.jdbcType != null) {
			return Optional.of(this.jdbcType);
		}
		return column.jdbcType();
    }
}
