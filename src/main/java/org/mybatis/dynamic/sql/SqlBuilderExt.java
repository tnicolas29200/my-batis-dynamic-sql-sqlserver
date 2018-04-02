package org.mybatis.dynamic.sql;

import org.mybatis.dynamic.sql.select.SelectModel;
import org.mybatis.dynamic.sql.util.Buildable;
import org.mybatis.dynamic.sql.where.condition.IsEqualTo;
import org.mybatis.dynamic.sql.where.condition.IsEqualToColumn;
import org.mybatis.dynamic.sql.where.condition.IsEqualToParam;
import org.mybatis.dynamic.sql.where.condition.IsEqualToWithSubselect;
import org.mybatis.dynamic.sql.where.condition.IsGreaterThan;
import org.mybatis.dynamic.sql.where.condition.IsGreaterThanColumn;
import org.mybatis.dynamic.sql.where.condition.IsGreaterThanOrEqualTo;
import org.mybatis.dynamic.sql.where.condition.IsGreaterThanOrEqualToColumn;
import org.mybatis.dynamic.sql.where.condition.IsGreaterThanOrEqualToParam;
import org.mybatis.dynamic.sql.where.condition.IsGreaterThanOrEqualToWithSubselect;
import org.mybatis.dynamic.sql.where.condition.IsGreaterThanParam;
import org.mybatis.dynamic.sql.where.condition.IsGreaterThanWithSubselect;
import org.mybatis.dynamic.sql.where.condition.IsLessThan;
import org.mybatis.dynamic.sql.where.condition.IsLessThanColumn;
import org.mybatis.dynamic.sql.where.condition.IsLessThanOrEqualTo;
import org.mybatis.dynamic.sql.where.condition.IsLessThanOrEqualToColumn;
import org.mybatis.dynamic.sql.where.condition.IsLessThanOrEqualToParam;
import org.mybatis.dynamic.sql.where.condition.IsLessThanOrEqualToWithSubselect;
import org.mybatis.dynamic.sql.where.condition.IsLessThanParam;
import org.mybatis.dynamic.sql.where.condition.IsLessThanWithSubselect;
import org.mybatis.dynamic.sql.where.condition.IsLike;
import org.mybatis.dynamic.sql.where.condition.IsLikeToParam;
import org.mybatis.dynamic.sql.where.condition.IsNotEqualTo;
import org.mybatis.dynamic.sql.where.condition.IsNotEqualToColumn;
import org.mybatis.dynamic.sql.where.condition.IsNotEqualToParam;
import org.mybatis.dynamic.sql.where.condition.IsNotEqualToWithSubselect;
import org.mybatis.dynamic.sql.where.condition.IsNotLike;
import org.mybatis.dynamic.sql.where.condition.IsNotLikeToParam;

public interface SqlBuilderExt extends SqlBuilder {
	static <T> IsEqualTo<T> equal() {
        return IsEqualTo.of(() -> null);
    }

	static <T> IsEqualToParam<T> equal(String paramName) {
        return IsEqualToParam.of(paramName);
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
	
	static <T> IsNotEqualTo<T> notEqual() {
        return IsNotEqualTo.of(() -> null);
    }

	static <T> IsNotEqualToParam<T> notEqual(String paramName) {
        return IsNotEqualToParam.of(paramName);
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
}
