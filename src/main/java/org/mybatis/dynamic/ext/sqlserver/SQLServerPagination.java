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
package org.mybatis.dynamic.ext.sqlserver;

import org.mybatis.dynamic.ext.sql.utils.Pagination;

public class SQLServerPagination implements Pagination {

	protected Integer limit;
	protected Integer offset;
	
	protected SQLServerPagination(Integer limit, Integer offset) {
		this.limit = limit;
		this.offset = offset;
	}
	
	@Override
	public String formatPagination(String query) {
		if (offset == null) {
			return query.replaceFirst("select ", "select top " + this.limit + " ");
		} else {
			return query + " OFFSET " + offset + " ROWS FETCH NEXT " + limit + " ROWS ONLY";
		}
	}
	
	public static Pagination of(int limit) {
		return new SQLServerPagination(limit, null);
	}
	
	public static Pagination of(int limit, int offset) {
		return new SQLServerPagination(limit, offset);
	}
}
