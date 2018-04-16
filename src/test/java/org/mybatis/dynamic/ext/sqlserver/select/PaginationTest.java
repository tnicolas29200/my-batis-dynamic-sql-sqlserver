/**
 *    Copyright 2018 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.dynamic.ext.sqlserver.select;

import static org.mybatis.dynamic.sql.SqlBuilder.select;

import java.sql.JDBCType;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mybatis.dynamic.ext.sql.utils.SimpleMyBatis3RendererUtils;
import org.mybatis.dynamic.ext.sqlserver.SQLServerPagination;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;


@RunWith(JUnitPlatform.class)
public class PaginationTest {

	public static final SqlTable table = SqlTable.of("foo");
    public static final SqlColumn<Integer> columnInteger1 = table.column("columnInteger1", JDBCType.INTEGER);
    
    @Test
    public void testPagination() {
    	String sqlTest = SimpleMyBatis3RendererUtils.select(select(columnInteger1.as("A_COLUMN1")).from(table, "a"), SQLServerPagination.of(10));
        Assert.assertEquals("select top 10 a.columnInteger1 as A_COLUMN1 from foo a", sqlTest);
        
        String sqlTest2 = SimpleMyBatis3RendererUtils.select(select(columnInteger1.as("A_COLUMN1")).from(table, "a").orderBy(columnInteger1), SQLServerPagination.of(10, 20));
        Assert.assertEquals("select a.columnInteger1 as A_COLUMN1 from foo a order by columnInteger1 OFFSET 20 ROWS FETCH NEXT 10 ROWS ONLY", sqlTest2);
    }
}
