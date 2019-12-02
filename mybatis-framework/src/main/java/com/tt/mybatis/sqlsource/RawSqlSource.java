package com.tt.mybatis.sqlsource;

import com.tt.mybatis.sqlnode.DynamicContext;
import com.tt.mybatis.sqlnode.iface.SqlNode;
import com.tt.mybatis.sqlsource.iface.SqlSource;

/**
 * @author lizhuo
 * @Description:
 * @date 2019-11-21 10:04
 */
public class RawSqlSource implements SqlSource {

	private SqlSource sqlSource;

	public RawSqlSource() {
	}

	// 只执行一次解析即可
	public RawSqlSource(SqlNode rootSqlNode) {
		DynamicContext context = new DynamicContext(null);

		// 将SqlNode处理成一条SQL语句
		rootSqlNode.apply(context);

		// 该SQL语句此时还包括 #{} 不包含 ${}  其实此处就是要解析SqlSource
		String sql = context.getSql();

		// 通过 SqlSourceParser 去解析 SqlSource 中的 #{}
		SqlSourceParser sqlSourceParse = new SqlSourceParser();

		// 将解析的结果 最终封装成 StaticSqlSource
		sqlSource = sqlSourceParse.parse(sql);
	}

	@Override
	public BoundSql getBoundSql(Object paramObject) {
		return sqlSource.getBoundSql(paramObject);
	}

}
