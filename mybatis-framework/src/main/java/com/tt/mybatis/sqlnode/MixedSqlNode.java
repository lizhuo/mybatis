package com.tt.mybatis.sqlnode;

import com.tt.mybatis.sqlnode.iface.SqlNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lizhuo
 * @Description: 封装了所有解析出来的sqlNode节点信息，方便统一处理
 * @date 2019-11-21 10:49
 */
public class MixedSqlNode implements SqlNode {

	private List<SqlNode> sqlNodes;

	public MixedSqlNode() {
	}

	public MixedSqlNode(List<SqlNode> sqlNodes) {
		this.sqlNodes = sqlNodes;
	}

	@Override
	public void apply(DynamicContext context) {
		for (SqlNode sqlNode : sqlNodes) {
			sqlNode.apply(context);
		}

	}

}
