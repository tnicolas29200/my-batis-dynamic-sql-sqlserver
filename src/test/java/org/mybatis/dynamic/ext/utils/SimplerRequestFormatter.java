package org.mybatis.dynamic.ext.utils;

import static org.mybatis.dynamic.ext.sql.SqlBuilder.equal;
import static org.mybatis.dynamic.ext.sql.SqlBuilder.deleteFrom;
import static org.mybatis.dynamic.ext.sql.SqlBuilder.insertInto;
import static org.mybatis.dynamic.sql.SqlBuilder.select;
import static org.mybatis.dynamic.sql.SqlBuilder.equalTo;
import static org.mybatis.dynamic.sql.SqlBuilder.update;
import static org.mybatis.dynamic.sql.SqlBuilder.and;

import java.sql.JDBCType;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mybatis.dynamic.ext.sql.utils.SimpleMyBatis3RendererUtils;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;


@RunWith(JUnitPlatform.class)
public class SimplerRequestFormatter {

	public static final SqlTable table = SqlTable.of("foo");
    public static final SqlColumn<Integer> columnInteger1 = table.column("columnInteger1", JDBCType.INTEGER);
    public static final SqlColumn<Boolean> columnBoolean1 = table.column("columnBoolean1", JDBCType.BOOLEAN);
    public static final SqlColumn<Integer> columnInteger2 = table.column("columnInteger2", JDBCType.INTEGER);
    public static final SqlColumn<Integer> columnInteger3 = table.column("columnInteger3", JDBCType.INTEGER);
    public static final SqlColumn<String> columnString1 = table.column("columnString1", JDBCType.VARCHAR);
    
    @Test
    public void testSelect() {
    	String request = SimpleMyBatis3RendererUtils.select(select(columnInteger1.as("A_COLUMN1")).from(table, "a").where(columnInteger1, equal("paramName1")));
    	Assert.assertEquals("select a.columnInteger1 as A_COLUMN1 from foo a where a.columnInteger1 = #{paramName1}", request);
    	
    	String request2 = SimpleMyBatis3RendererUtils.select(select(columnInteger1.as("A_COLUMN1")).from(table, "a").orderBy(columnInteger1));
    	Assert.assertEquals("select a.columnInteger1 as A_COLUMN1 from foo a order by columnInteger1", request2);
    }
    
    @Test
    public void testDelete() {
    	String request = SimpleMyBatis3RendererUtils.delete(deleteFrom(table).where(columnInteger1, equal("paramName1")));
    	Assert.assertEquals("delete from foo where columnInteger1 = #{paramName1}", request);
    	
    	String request2 = SimpleMyBatis3RendererUtils.delete(deleteFrom(table).where(columnInteger1, equal("paramName1"), and(columnInteger2, equal("paramName2"))));
    	Assert.assertEquals("delete from foo where (columnInteger1 = #{paramName1} and columnInteger2 = #{paramName2})", request2);
    	
    	String request3 = SimpleMyBatis3RendererUtils.delete(deleteFrom(table));
    	Assert.assertEquals("delete from foo", request3);
    }
    
    @Test
    public void testUpdate() {
    	String request = SimpleMyBatis3RendererUtils.update(update(table).set(columnInteger1).equalTo(columnInteger2));
    	Assert.assertEquals("update foo set columnInteger1 = columnInteger2", request);
    	
    	String request2 = SimpleMyBatis3RendererUtils.update(update(table).set(columnInteger1).equalTo(columnInteger2).where(columnInteger1, equal("paramName1")));
    	Assert.assertEquals("update foo set columnInteger1 = columnInteger2 where columnInteger1 = #{paramName1}", request2);
    }
    
    @Test
    public void testInsert() {
    	String request = SimpleMyBatis3RendererUtils.insert(insertInto(table).values(columnInteger1));
    	Assert.assertEquals("insert into foo (columnInteger1)  VALUES (#{columnInteger1})", request);
    	
    	String request2 = SimpleMyBatis3RendererUtils.insert(insertInto(table).values(columnInteger1, columnInteger2));
    	Assert.assertEquals("insert into foo (columnInteger1, columnInteger2)  VALUES (#{columnInteger1}, #{columnInteger2})", request2);
    }
}
