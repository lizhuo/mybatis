package com.tt.mybatis.sqlsource;

import com.tt.mybatis.sqlnode.iface.SqlNode;
import com.tt.mybatis.sqlsource.iface.SqlSource;

/**
 * @author lizhuo
 * @Description: TODO
 * @date 2019-11-21 10:04
 */
public class RawSqlSource implements SqlSource {

	public RawSqlSource() {
	}

	public RawSqlSource(SqlNode rootSqlNode) {
		// TODO 执行的时候再去坐该部分
	}

	@Override
	public BoundSql getBoundSql(Object paramObject) {
		return null;
	}

}
