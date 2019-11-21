package com.tt.mybatis.sqlsource;

import com.tt.mybatis.sqlnode.iface.SqlNode;
import com.tt.mybatis.sqlsource.iface.SqlSource;

/**
 * @author lizhuo
 * @Description: TODO
 * @date 2019-11-21 10:04
 */
public class DynamicSqlSource implements SqlSource {

	private SqlNode rootSqlNode;

	public DynamicSqlSource() {
	}

	public DynamicSqlSource(SqlNode sqlNode) {
		this.rootSqlNode = sqlNode;
	}

	@Override
	public BoundSql getBoundSql(Object paramObject) {
		// TODO 执行的时候再去坐该部分
		// TODO 其实此处就是要解析SqlSource
		return null;
	}

}
