package com.tt.mybatis.nodehandler;

import com.tt.mybatis.sqlnode.iface.SqlNode;
import org.dom4j.Element;

import java.util.List;

/**
 * @author lizhuo
 * @Description: IF 标签处理器
 * @date 2019-11-21 11:13
 */
public class IfNodeHandler implements NodeHandler {

	/**
	 * if 标签
	 * @param nodeToHandler
	 * @param content
	 */
	@Override
	public void handleNode(Element nodeToHandler, List<SqlNode> content) {

	}

}
