package org.mybatis.dynamic.ext.sql.select.function;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.JDBCType;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mybatis.dynamic.sql.BindableColumn;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

@RunWith(JUnitPlatform.class)
public class TestFunctionCopy {
	
	public static final SqlTable table = SqlTable.of("foo");
    public static final SqlColumn<Integer> columnInteger1 = table.column("columnInteger1", JDBCType.INTEGER);
	
	@Test
	public void testCopyAdd() {
		AddWithConstantAccessor<Integer> instance = new AddWithConstantAccessor<>(columnInteger1, 1);
		AddWithConstant<Integer> copy = instance.copy();
		assertEquals(Integer.valueOf(1), copy.value);
		
		AddWithParameterAccessor<Integer> instance2 = new AddWithParameterAccessor<>(columnInteger1, "test");
		AddWithParameter<Integer> copy2 = instance2.copy();
		assertEquals("test", copy2.paramName);
	}
	
	@Test
	public void testCopySubstract() {
		SubstractWithConstantAccessor<Integer> instance = new SubstractWithConstantAccessor<>(columnInteger1, 1);
		SubstractWithConstant<Integer> copy = instance.copy();
		assertEquals(Integer.valueOf(1), copy.value);
		
		SubstractWithParameterAccessor<Integer> instance2 = new SubstractWithParameterAccessor<>(columnInteger1, "test");
		SubstractWithParameter<Integer> copy2 = instance2.copy();
		assertEquals("test", copy2.paramName);
	}
	
	@Test
	public void testCopyMultiply() {
		MultiplyWithConstantAccessor<Integer> instance = new MultiplyWithConstantAccessor<>(columnInteger1, 1);
		MultiplyWithConstant<Integer> copy = instance.copy();
		assertEquals(Integer.valueOf(1), copy.value);
		
		MultiplyWithParameterAccessor<Integer> instance2 = new MultiplyWithParameterAccessor<>(columnInteger1, "test");
		MultiplyWithParameter<Integer> copy2 = instance2.copy();
		assertEquals("test", copy2.paramName);
	}
	
	@Test
	public void testCopyDivideWithConstant() {
		DivideWithConstantAccessor<Integer> instance = new DivideWithConstantAccessor<>(columnInteger1, 1);
		DivideWithConstant<Integer> copy = instance.copy();
		assertEquals(Integer.valueOf(1), copy.value);
		
		DivideWithParameterAccessor<Integer> instance2 = new DivideWithParameterAccessor<>(columnInteger1, "test");
		DivideWithParameter<Integer> copy2 = instance2.copy();
		assertEquals("test", copy2.paramName);
	}
	
	protected class AddWithConstantAccessor<T extends Number> extends AddWithConstant<T> {
		public AddWithConstantAccessor(BindableColumn<T> firstColumn, T value) {
			super(firstColumn, value);
		}
		
		public AddWithConstant<T> copy() {
			return super.copy();
		}
	}
	
	protected class AddWithParameterAccessor<T extends Number> extends AddWithParameter<T> {
		public AddWithParameterAccessor(BindableColumn<T> firstColumn, String value) {
			super(firstColumn, value);
		}
		
		public AddWithParameter<T> copy() {
			return super.copy();
		}
	}
	
	protected class SubstractWithConstantAccessor<T extends Number> extends SubstractWithConstant<T> {
		public SubstractWithConstantAccessor(BindableColumn<T> firstColumn, T value) {
			super(firstColumn, value);
		}
		
		public SubstractWithConstant<T> copy() {
			return super.copy();
		}
	}
	
	protected class SubstractWithParameterAccessor<T extends Number> extends SubstractWithParameter<T> {
		public SubstractWithParameterAccessor(BindableColumn<T> firstColumn, String value) {
			super(firstColumn, value);
		}
		
		public SubstractWithParameter<T> copy() {
			return super.copy();
		}
	}
	
	protected class MultiplyWithConstantAccessor<T extends Number> extends MultiplyWithConstant<T> {
		public MultiplyWithConstantAccessor(BindableColumn<T> firstColumn, T value) {
			super(firstColumn, value);
		}
		
		public MultiplyWithConstant<T> copy() {
			return super.copy();
		}
	}
	
	protected class MultiplyWithParameterAccessor<T extends Number> extends MultiplyWithParameter<T> {
		public MultiplyWithParameterAccessor(BindableColumn<T> firstColumn, String value) {
			super(firstColumn, value);
		}
		
		public MultiplyWithParameter<T> copy() {
			return super.copy();
		}
	}
	
	protected class DivideWithConstantAccessor<T extends Number> extends DivideWithConstant<T> {
		public DivideWithConstantAccessor(BindableColumn<T> firstColumn, T value) {
			super(firstColumn, value);
		}
		
		public DivideWithConstant<T> copy() {
			return super.copy();
		}
	}
	
	protected class DivideWithParameterAccessor<T extends Number> extends DivideWithParameter<T> {
		public DivideWithParameterAccessor(BindableColumn<T> firstColumn, String value) {
			super(firstColumn, value);
		}
		
		public DivideWithParameter<T> copy() {
			return super.copy();
		}
	}
}
