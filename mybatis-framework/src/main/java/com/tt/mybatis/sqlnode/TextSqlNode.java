package com.tt.mybatis.sqlnode;

import com.tt.mybatis.sqlnode.iface.SqlNode;

/**
 * @author lizhuo
 * @Description: TODO
 * @date 2019-11-20 18:02
 */
public class TextSqlNode implements SqlNode {

	private String sqlText;

	public TextSqlNode() {
	}

	public TextSqlNode(String sqlText) {
		this.sqlText = sqlText;
	}

	@Override
	public void apply(DynamicContext context) {

	}

	public boolean isDynamic() {
		if (sqlText.indexOf("${") >= 0) {
			return true;
		}
		return false;
	}

}
