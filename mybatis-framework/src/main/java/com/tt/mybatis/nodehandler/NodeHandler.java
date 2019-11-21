package com.tt.mybatis.nodehandler;

import com.tt.mybatis.sqlnode.iface.SqlNode;
import org.dom4j.Element;

import java.util.List;

/**
 * @author lizhuo
 * @Description: 标签处理器 接口
 * @date 2019-11-21 11:05
 */
public interface NodeHandler {

	void handleNode(Element nodeToHandle, List<SqlNode> content);

}
