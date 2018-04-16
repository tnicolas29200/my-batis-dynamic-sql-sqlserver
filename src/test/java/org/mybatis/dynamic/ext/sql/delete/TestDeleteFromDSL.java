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
package org.mybatis.dynamic.ext.sql.delete;

import static org.mybatis.dynamic.ext.sql.SqlBuilder.deleteFrom;
import static org.mybatis.dynamic.ext.sql.SqlBuilder.equal;

import java.sql.JDBCType;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

@RunWith(JUnitPlatform.class)
public class TestDeleteFromDSL {
	
	public static final SqlTable table = SqlTable.of("foo");
    public static final SqlColumn<Integer> columnInteger1 = table.column("columnInteger1", JDBCType.INTEGER);
    
	@Test
	public void testGetThis() {
		DeleteFromDSL<?> deleteDSL = deleteFrom(table);
		DeleteFromDSL.DeleteWhereBuilder instance = deleteDSL.new DeleteWhereBuilder(columnInteger1, equal(columnInteger1));
		Assert.assertEquals(instance, instance.getThis());
	}
	
}
