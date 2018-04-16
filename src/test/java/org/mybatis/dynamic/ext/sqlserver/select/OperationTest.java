package org.mybatis.dynamic.ext.sqlserver.select;

import static org.mybatis.dynamic.ext.sql.SqlBuilder.add;
import static org.mybatis.dynamic.ext.sql.SqlBuilder.addConstant;
import static org.mybatis.dynamic.ext.sql.SqlBuilder.divide;
import static org.mybatis.dynamic.ext.sql.SqlBuilder.divideConstant;
import static org.mybatis.dynamic.ext.sql.SqlBuilder.multiply;
import static org.mybatis.dynamic.ext.sql.SqlBuilder.multiplyConstant;
import static org.mybatis.dynamic.ext.sql.SqlBuilder.subtract;
import static org.mybatis.dynamic.ext.sql.SqlBuilder.subtractConstant;
import static org.mybatis.dynamic.sql.SqlBuilder.update;

import java.sql.JDBCType;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;

@RunWith(JUnitPlatform.class)
public class OperationTest {
	public static final SqlTable table = SqlTable.of("foo");
    public static final SqlColumn<String> columnString1 = table.column("columnString1", JDBCType.VARCHAR);
    public static final SqlColumn<String> columnString2 = table.column("columnString2", JDBCType.VARCHAR);
    public static final SqlColumn<String> columnString3 = table.column("columnString3", JDBCType.VARCHAR);
    public static final SqlColumn<Integer> columnInteger1 = table.column("columnInteger1", JDBCType.INTEGER);
    public static final SqlColumn<Integer> columnInteger2 = table.column("columnInteger2", JDBCType.INTEGER);
    public static final SqlColumn<Integer> columnInteger3 = table.column("columnInteger3", JDBCType.INTEGER);
    
    @Test
    public void testUpdate() {
    	test(update(table).set(columnInteger1).equalTo(add(columnInteger1, "test")),
            	"update foo set columnInteger1 = columnInteger1 + #{test}");
    	
    	test(update(table).set(columnInteger1).equalTo(addConstant(columnInteger1, 1)),
            	"update foo set columnInteger1 = columnInteger1 + 1");
    	
    	test(update(table).set(columnInteger1).equalTo(subtract(columnInteger1, "test")),
            	"update foo set columnInteger1 = columnInteger1 - #{test}");
    	
    	test(update(table).set(columnInteger1).equalTo(subtractConstant(columnInteger1, 1)),
            	"update foo set columnInteger1 = columnInteger1 - 1");
    	
    	test(update(table).set(columnInteger1).equalTo(multiply(columnInteger1, "test")),
            	"update foo set columnInteger1 = columnInteger1 * #{test}");
    	
    	test(update(table).set(columnInteger1).equalTo(multiplyConstant(columnInteger1, 1)),
            	"update foo set columnInteger1 = columnInteger1 * 1");
    	
    	test(update(table).set(columnInteger1).equalTo(divide(columnInteger1, "test")),
            	"update foo set columnInteger1 = columnInteger1 / #{test}");
    	
    	test(update(table).set(columnInteger1).equalTo(divideConstant(columnInteger1, 1)),
            	"update foo set columnInteger1 = columnInteger1 / 1");
    }
    
    @SuppressWarnings("rawtypes")
   	protected void test(UpdateDSL selectModel, String sql) {
    	UpdateStatementProvider selectStatement = ((UpdateModel) selectModel.build()).render(RenderingStrategy.MYBATIS3);
       	SoftAssertions.assertSoftly(softly -> softly.assertThat(selectStatement.getUpdateStatement()).isEqualTo(sql));
    }
}
