package com.tt.mybatis.sqlnode;

import com.tt.mybatis.sqlnode.iface.SqlNode;
import com.tt.mybatis.utils.OgnlUtils;

/**
 * @author lizhuo
 * @Description: IF SQL Node
 * @date 2019-11-21 11:57
 */
public class IfSqlNode implements SqlNode {

	private String test;

	private SqlNode rootSqlNode;

	public IfSqlNode() {
	}

	public IfSqlNode(String test, SqlNode rootSqlNode) {
		this.test = test;
		this.rootSqlNode = rootSqlNode;
	}

	@Override
	public void apply(DynamicContext context) {
		// OGNL 表达式
		boolean testValue = OgnlUtils.evaluateBoolean(test, context.getBindings().get("_parameter"));
		if (testValue) {
			this.rootSqlNode.apply(context);
		}
	}

}
