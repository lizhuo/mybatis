package com.tt.mybatis.sqlsource;

import com.tt.mybatis.sqlnode.DynamicContext;
import com.tt.mybatis.sqlnode.iface.SqlNode;
import com.tt.mybatis.sqlsource.iface.SqlSource;

/**
 * @author lizhuo
 * @Description:
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
		DynamicContext context = new DynamicContext(paramObject);

		// 将SqlNode处理成一条SQL语句
		rootSqlNode.apply(context);

		// 该SQL语句此时还包括 #{} 不包含 ${}  其实此处就是要解析SqlSource
		String sql = context.getSql();

		// 通过 SqlSourceParser 去解析 SqlSource 中的 #{}
		SqlSourceParser sqlSourceParse = new SqlSourceParser();

		// 将解析的结果 最终封装成 StaticSqlSource
		SqlSource sqlSource = sqlSourceParse.parse(sql);

		// 调用 StaticSqlSource 的方法
		return sqlSource.getBoundSql(paramObject);
	}

}
