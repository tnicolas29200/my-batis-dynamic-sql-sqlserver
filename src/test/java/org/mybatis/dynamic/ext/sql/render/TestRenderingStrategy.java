package org.mybatis.dynamic.ext.sql.render;

import java.sql.JDBCType;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mybatis.dynamic.ext.render.SimpleMyBatis3RenderingStrategy;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

@RunWith(JUnitPlatform.class)
public class TestRenderingStrategy {

	public static final SqlTable table = SqlTable.of("foo");
    public static final SqlColumn<Integer> columnInteger1 = table.column("columnInteger1", JDBCType.INTEGER);
    
    @Test
    public void testGetFormattedJdbcPlaceHolder() {
    	String result = new SimpleMyBatis3RenderingStrategy().getFormattedJdbcPlaceholder(columnInteger1, "", "");
    	Assert.assertEquals("#{columnInteger1}", result);
    	
    	result = new SimpleMyBatis3RenderingStrategy().getFormattedJdbcPlaceholder(columnInteger1.as("column1"), "", "");
    	Assert.assertEquals("#{column1}", result);
    }
}
