package com.tt.mybatis.sqlnode;

import com.tt.mybatis.sqlnode.iface.SqlNode;

/**
 * @author lizhuo
 * @Description: 只包含了非${}的sql文本信息
 * @date 2019-11-21 11:42
 */
public class StaticTextSqlNode implements SqlNode {

	private String sqlText;

	public StaticTextSqlNode(String sqlText) {
		this.sqlText = sqlText;
	}

	@Override
	public void apply(DynamicContext context) {
		context.appendSql(sqlText);
	}

}
