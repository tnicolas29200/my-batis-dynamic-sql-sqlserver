package org.mybatis.dynamic.sql.select.function;

import java.sql.JDBCType;
import java.util.List;
import java.util.Optional;

import org.mybatis.dynamic.sql.BindableColumn;
import org.mybatis.dynamic.sql.render.TableAliasCalculator;

public class SimpleColumnWithParametersFunction<T, U extends AbstractFunction<T, U>> extends AbstractFunction<T, SimpleColumnWithParametersFunction<T,U>> {
    
	private String functionName;
	private JDBCType jdbcType;
	private List<Object> parameters;
	
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
			builder.append(parameter);
		}
		builder.append(")");
		return builder.toString();
    }
	
	public static <T, U extends AbstractFunction<T, U>> SimpleColumnWithParametersFunction<T, U> of(BindableColumn<T> column, String functionName, List<Object> parameters) {
        return new SimpleColumnWithParametersFunction<T, U>(column, functionName, parameters);
    }

	public static <T, U extends AbstractFunction<T, U>> SimpleColumnWithParametersFunction<T, U> of(BindableColumn<T> column, String functionName, List<Object> parameters, JDBCType jdbcType) {
        return new SimpleColumnWithParametersFunction<T, U>(column, functionName, parameters, jdbcType);
    }
	
	@Override
	protected SimpleColumnWithParametersFunction<T, U> copy() {
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
