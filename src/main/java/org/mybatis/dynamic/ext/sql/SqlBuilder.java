package org.mybatis.dynamic.ext.sql;

import org.mybatis.dynamic.ext.sql.delete.DeleteFromDSL;
import org.mybatis.dynamic.ext.sql.delete.DeleteFromModel;
import org.mybatis.dynamic.ext.sql.insert.InsertIntoDSL;
import org.mybatis.dynamic.ext.sql.select.function.AddWithConstant;
import org.mybatis.dynamic.ext.sql.select.function.AddWithParameter;
import org.mybatis.dynamic.ext.sql.select.function.DivideWithConstant;
import org.mybatis.dynamic.ext.sql.select.function.DivideWithParameter;
import org.mybatis.dynamic.ext.sql.select.function.MultiplyWithConstant;
import org.mybatis.dynamic.ext.sql.select.function.MultiplyWithParameter;
import org.mybatis.dynamic.ext.sql.select.function.SubstractWithConstant;
import org.mybatis.dynamic.ext.sql.select.function.SubstractWithParameter;
import org.mybatis.dynamic.ext.sql.where.condition.IsEqualToConstant;
import org.mybatis.dynamic.ext.sql.where.condition.IsEqualToParam;
import org.mybatis.dynamic.ext.sql.where.condition.IsGreaterThanConstant;
import org.mybatis.dynamic.ext.sql.where.condition.IsGreaterThanOrEqualToConstant;
import org.mybatis.dynamic.ext.sql.where.condition.IsGreaterThanOrEqualToParam;
import org.mybatis.dynamic.ext.sql.where.condition.IsGreaterThanParam;
import org.mybatis.dynamic.ext.sql.where.condition.IsInConstant;
import org.mybatis.dynamic.ext.sql.where.condition.IsInParam;
import org.mybatis.dynamic.ext.sql.where.condition.IsLessThanConstant;
import org.mybatis.dynamic.ext.sql.where.condition.IsLessThanOrEqualToConstant;
import org.mybatis.dynamic.ext.sql.where.condition.IsLessThanOrEqualToParam;
import org.mybatis.dynamic.ext.sql.where.condition.IsLessThanParam;
import org.mybatis.dynamic.ext.sql.where.condition.IsLikeToConstant;
import org.mybatis.dynamic.ext.sql.where.condition.IsLikeToParam;
import org.mybatis.dynamic.ext.sql.where.condition.IsNotEqualToConstant;
import org.mybatis.dynamic.ext.sql.where.condition.IsNotEqualToParam;
import org.mybatis.dynamic.ext.sql.where.condition.IsNotInConstant;
import org.mybatis.dynamic.ext.sql.where.condition.IsNotInParam;
import org.mybatis.dynamic.ext.sql.where.condition.IsNotLikeToConstant;
import org.mybatis.dynamic.ext.sql.where.condition.IsNotLikeToParam;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.BindableColumn;
import org.mybatis.dynamic.sql.SqlTable;
import org.mybatis.dynamic.sql.select.SelectModel;
import org.mybatis.dynamic.sql.util.Buildable;
import org.mybatis.dynamic.sql.where.condition.IsEqualTo;
import org.mybatis.dynamic.sql.where.condition.IsEqualToColumn;
import org.mybatis.dynamic.sql.where.condition.IsEqualToWithSubselect;
import org.mybatis.dynamic.sql.where.condition.IsGreaterThan;
import org.mybatis.dynamic.sql.where.condition.IsGreaterThanColumn;
import org.mybatis.dynamic.sql.where.condition.IsGreaterThanOrEqualTo;
import org.mybatis.dynamic.sql.where.condition.IsGreaterThanOrEqualToColumn;
import org.mybatis.dynamic.sql.where.condition.IsGreaterThanOrEqualToWithSubselect;
import org.mybatis.dynamic.sql.where.condition.IsGreaterThanWithSubselect;
import org.mybatis.dynamic.sql.where.condition.IsLessThan;
import org.mybatis.dynamic.sql.where.condition.IsLessThanColumn;
import org.mybatis.dynamic.sql.where.condition.IsLessThanOrEqualTo;
import org.mybatis.dynamic.sql.where.condition.IsLessThanOrEqualToColumn;
import org.mybatis.dynamic.sql.where.condition.IsLessThanOrEqualToWithSubselect;
import org.mybatis.dynamic.sql.where.condition.IsLessThanWithSubselect;
import org.mybatis.dynamic.sql.where.condition.IsLike;
import org.mybatis.dynamic.sql.where.condition.IsNotEqualTo;
import org.mybatis.dynamic.sql.where.condition.IsNotEqualToColumn;
import org.mybatis.dynamic.sql.where.condition.IsNotEqualToWithSubselect;
import org.mybatis.dynamic.sql.where.condition.IsNotLike;


public interface SqlBuilder extends org.mybatis.dynamic.sql.SqlBuilder {
	static InsertIntoDSL.InsertColumnGatherer insertInto(SqlTable table) {
		return InsertIntoDSL.insertInto(table);
	}

	static DeleteFromDSL<DeleteFromModel> deleteFrom(SqlTable table) {
		return DeleteFromDSL.deleteFrom(table);
	}
	
	static <T> IsEqualTo<T> equal() {
        return IsEqualTo.of(() -> null);
    }

	static <T> IsEqualToParam<T> equal(String paramName) {
        return IsEqualToParam.of(paramName);
    }
	
	static <T> IsEqualToConstant<T> equalConstant(T value) {
        return IsEqualToConstant.of(value);
    }
	
	static <T> IsEqualToWithSubselect<T> equal(Buildable<SelectModel> selectModelBuilder) {
        return IsEqualToWithSubselect.of(selectModelBuilder);
    }
    
    static <T> IsEqualToColumn<T> equal(BasicColumn column) {
        return IsEqualToColumn.of(column);
    }
	
    static <T> IsGreaterThan<T> greater() {
        return IsGreaterThan.of(() -> null);
    }
    
	static <T> IsGreaterThanParam<T> greater(String paramName) {
        return IsGreaterThanParam.of(paramName);
    }
	
	static <T> IsGreaterThanConstant<T> greaterConstant(T value) {
        return IsGreaterThanConstant.of(value);
    }
	
	static <T> IsGreaterThanWithSubselect<T> greater(Buildable<SelectModel> selectModelBuilder) {
        return IsGreaterThanWithSubselect.of(selectModelBuilder);
    }
    
    static <T> IsGreaterThanColumn<T> greater(BasicColumn column) {
        return IsGreaterThanColumn.of(column);
    }
	
    static <T> IsGreaterThanOrEqualTo<T> greaterOrEqual() {
        return IsGreaterThanOrEqualTo.of(() -> null);
    }
    
	static <T> IsGreaterThanOrEqualToParam<T> greaterOrEqual(String paramName) {
        return IsGreaterThanOrEqualToParam.of(paramName);
    }
	
	static <T> IsGreaterThanOrEqualToConstant<T> greaterOrEqualConstant(T value) {
        return IsGreaterThanOrEqualToConstant.of(value);
    }
	    
    static <T> IsGreaterThanOrEqualToWithSubselect<T> greaterOrEqual(Buildable<SelectModel> selectModelBuilder) {
        return IsGreaterThanOrEqualToWithSubselect.of(selectModelBuilder);
    }
    
    static <T> IsGreaterThanOrEqualToColumn<T> greaterOrEqual(BasicColumn column) {
        return IsGreaterThanOrEqualToColumn.of(column);
    }
	    
    static <T> IsLessThan<T> less() {
        return IsLessThan.of(() -> null);
    }
    
	static <T> IsLessThanParam<T> less(String paramName) {
        return IsLessThanParam.of(paramName);
    }
	
	static <T> IsLessThanConstant<T> lessConstant(T value) {
        return IsLessThanConstant.of(value);
    }
	
	static <T> IsLessThanWithSubselect<T> less(Buildable<SelectModel> selectModelBuilder) {
        return IsLessThanWithSubselect.of(selectModelBuilder);
    }
    
    static <T> IsLessThanColumn<T> less(BasicColumn column) {
        return IsLessThanColumn.of(column);
    }
	
    static <T> IsLessThanOrEqualTo<T> lessOrEqual() {
        return IsLessThanOrEqualTo.of(() -> null);
    }
    
	static <T> IsLessThanOrEqualToParam<T> lessOrEqual(String paramName) {
        return IsLessThanOrEqualToParam.of(paramName);
    }
	
	static <T> IsLessThanOrEqualToConstant<T> lessOrEqualConstant(T value) {
        return IsLessThanOrEqualToConstant.of(value);
    }
	
	static <T> IsLessThanOrEqualToWithSubselect<T> lessOrEqual(Buildable<SelectModel> selectModelBuilder) {
        return IsLessThanOrEqualToWithSubselect.of(selectModelBuilder);
    }
    
    static <T> IsLessThanOrEqualToColumn<T> lessOrEqual(BasicColumn column) {
        return IsLessThanOrEqualToColumn.of(column);
    }
	
    static <T> IsLike<T> like() {
        return IsLike.of(() -> null);
    }
    
	static <T> IsLikeToParam<T> like(String paramName) {
        return IsLikeToParam.of(paramName);
    }

	static IsLikeToConstant likeConstant(String value) {
        return IsLikeToConstant.of(value);
    }
	
	static <T> IsNotEqualTo<T> notEqual() {
        return IsNotEqualTo.of(() -> null);
    }

	static <T> IsNotEqualToParam<T> notEqual(String paramName) {
        return IsNotEqualToParam.of(paramName);
    }
	
	static <T> IsNotEqualToConstant<T> notEqualConstant(T value) {
        return IsNotEqualToConstant.of(value);
    }
	
	static <T> IsNotEqualToWithSubselect<T> notEqual(Buildable<SelectModel> selectModelBuilder) {
        return IsNotEqualToWithSubselect.of(selectModelBuilder);
    }
    
    static <T> IsNotEqualToColumn<T> notEqual(BasicColumn column) {
        return IsNotEqualToColumn.of(column);
    }
    
    static <T> IsNotLike<T> notLike() {
        return IsNotLike.of(() -> null);
    }
    
	static <T> IsNotLikeToParam<T> notLike(String paramName) {
        return IsNotLikeToParam.of(paramName);
    }
	
	static IsNotLikeToConstant notLikeConstant(String value) {
        return IsNotLikeToConstant.of(value);
    }
	
    static <T extends Number> AddWithParameter<T> add(BindableColumn<T> firstColumn, String paramName) {
        return AddWithParameter.of(firstColumn, paramName);
    }
    
    static <T extends Number> AddWithConstant<T> addConstant(BindableColumn<T> firstColumn, T value) {
        return AddWithConstant.of(firstColumn, value);
    }
    
    static <T extends Number> DivideWithParameter<T> divide(BindableColumn<T> firstColumn, String paramName) {
        return DivideWithParameter.of(firstColumn, paramName);
    }
    
    static <T extends Number> DivideWithConstant<T> divideConstant(BindableColumn<T> firstColumn, T value) {
        return DivideWithConstant.of(firstColumn, value);
    }
    
    static <T extends Number> MultiplyWithParameter<T> multiply(BindableColumn<T> firstColumn, String paramName) {
        return MultiplyWithParameter.of(firstColumn, paramName);
    }
    
    static <T extends Number> MultiplyWithConstant<T> multiplyConstant(BindableColumn<T> firstColumn, T value) {
        return MultiplyWithConstant.of(firstColumn, value);
    }
    
    static <T extends Number> SubstractWithParameter<T> subtract(BindableColumn<T> firstColumn, String paramName) {
        return SubstractWithParameter.of(firstColumn, paramName);
    }
    
    static <T extends Number> SubstractWithConstant<T> subtractConstant(BindableColumn<T> firstColumn, T value) {
        return SubstractWithConstant.of(firstColumn, value);
    }
    
    static <T> IsInParam<T> in(String paramName) {
        return IsInParam.of(paramName);
    }
    
    static <T> IsInConstant<T> inConstant(String value, Class<T> type) {
        return IsInConstant.of(value);
    }
    
    static <T> IsNotInParam<T> notIn(String paramName) {
        return IsNotInParam.of(paramName);
    }
    
    static <T> IsNotInConstant<T> notInConstant(String value, Class<T> type) {
        return IsNotInConstant.of(value);
    }
}
