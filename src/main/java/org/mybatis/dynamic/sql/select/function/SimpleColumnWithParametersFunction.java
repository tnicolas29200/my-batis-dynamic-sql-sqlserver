package org.mybatis.dynamic.sql.select.function;

import java.sql.JDBCType;
import java.util.List;
import java.util.Optional;

import org.mybatis.dynamic.sql.BindableColumn;
import org.mybatis.dynamic.sql.render.TableAliasCalculator;

public class SimpleColumnWithParametersFunction<T> extends AbstractFunction<T> {
    
	protected String functionName;
	protected JDBCType jdbcType;
	protected List<Object> parameters;
	
    protected SimpleColumnWithParametersFunction(BindableColumn<T> column, String functionName, List<Object> parameters) {
		super(column);
		this.parameters = parameters;
		this.functionName = functionName;
	}
    
    protected SimpleColumnWithParametersFunction(BindableColumn<T> column, String functionName, List<Object> parameters, JDBCType jdbcType) {
		this(column, functionName, parameters);
		this.jdbcType = jdbcType;
	}

	@Override
    public String renderWithTableAlias(TableAliasCalculator tableAliasCalculator) {
		StringBuilder builder = new StringBuilder(functionName + "(" + column.renderWithTableAlias(tableAliasCalculator));
		for (Object parameter: this.parameters) {
			builder.append(", ");
			if (parameter instanceof String) {
				builder.append("'" + parameter + "'");
			} else {
				builder.append(parameter);
			}
		}
		builder.append(")");
		return builder.toString();
    }
	
	public static <T> SimpleColumnWithParametersFunction<T> of(BindableColumn<T> column, String functionName, List<Object> parameters) {
        return new SimpleColumnWithParametersFunction<T>(column, functionName, parameters);
    }

	public static <T> SimpleColumnWithParametersFunction<T> of(BindableColumn<T> column, String functionName, List<Object> parameters, JDBCType jdbcType) {
        return new SimpleColumnWithParametersFunction<T>(column, functionName, parameters, jdbcType);
    }
	
	@Override
	protected SimpleColumnWithParametersFunction<T> copy() {
		return new SimpleColumnWithParametersFunction<>(column, functionName, parameters, jdbcType);
	}
	
	@Override
    public Optional<JDBCType> jdbcType() {
		if (this.jdbcType != null) {
			return Optional.of(this.jdbcType);
		}
		return column.jdbcType();
    }
}
