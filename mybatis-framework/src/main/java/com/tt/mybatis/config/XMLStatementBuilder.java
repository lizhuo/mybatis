package com.tt.mybatis.config;

import com.tt.mybatis.nodehandler.IfNodeHandler;
import com.tt.mybatis.nodehandler.NodeHandler;
import com.tt.mybatis.sqlnode.MixedSqlNode;
import com.tt.mybatis.sqlnode.StaticTextSqlNode;
import com.tt.mybatis.sqlnode.TextSqlNode;
import com.tt.mybatis.sqlnode.iface.SqlNode;
import com.tt.mybatis.sqlsource.DynamicSqlSource;
import com.tt.mybatis.sqlsource.RawSqlSource;
import com.tt.mybatis.sqlsource.iface.SqlSource;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lizhuo
 * @Description: 用来解析映射文件中的 select/insert等CRUD标签
 * @date 2019-11-20 15:29
 */
public class XMLStatementBuilder {

	private Configuration configuration;
	private Map<String, NodeHandler> nodeHandlerMap = new HashMap<String, NodeHandler>();
	private boolean isDynamic;

	public XMLStatementBuilder() {
	}

	public XMLStatementBuilder(Configuration configuration) {
		this.configuration = configuration;

		initNodeHandler();
	}

	private void initNodeHandler() {
		this.nodeHandlerMap.put("if", new IfNodeHandler());
		//this.nodeHandlerMap.put("where", new WhereNodeHandler());
	}

	public void parse(String namespace, Element stateElement) {
		String id = stateElement.attributeValue("id");
		if (id == null || id.equals("")) {
			return;
		}
		String parameterType = stateElement.attributeValue("parameterType");
		Class<?> parameterClazz = resolveType(parameterType);

		String resultType = stateElement.attributeValue("resultType");
		Class<?> resultClazz = resolveType(resultType);

		String statementType = stateElement.attributeValue("statementType");
		statementType = statementType == null || statementType.equals("") ? "prepared" : statementType;

		SqlSource sqlSource = createSqlSource(stateElement);

		// TODO 建议使用构建者模式 优化
		MappedStatement mappedStatement = new MappedStatement(id, parameterClazz, resultClazz, statementType, sqlSource);
		this.configuration.addMappedStatement(id, mappedStatement);
	}

	private SqlSource createSqlSource(Element stateElement) {
		// 解析动态标签
		MixedSqlNode rootSqlNode = parseDynamicTags(stateElement);
		SqlSource sqlSource = null;
		if (isDynamic) {
			sqlSource = new DynamicSqlSource(rootSqlNode);
		} else {
			sqlSource = new RawSqlSource(rootSqlNode);
		}

		return sqlSource;
	}

	private MixedSqlNode parseDynamicTags(Element stateElement) {
		List<Node> nodes = stateElement.elements();
		List<SqlNode> contents = new ArrayList<SqlNode>();
		for (Node node : nodes) {
			if (node instanceof Text) {
				String sqlText = node.getText().trim();
				TextSqlNode textSqlNode = new TextSqlNode(sqlText);
				if (textSqlNode.isDynamic()) {
					isDynamic = true;
					contents.add(textSqlNode);
				} else {
					contents.add(new StaticTextSqlNode(sqlText));
				}
			} else if (node instanceof Element) {
				// 此处通过NodeHandler去处理不同的标签
				Element nodeToHandle = (Element) node;
				String name = nodeToHandle.getName();
				// 通过标签名称查找对应的标签处理器
				NodeHandler nodeHandler = this.nodeHandlerMap.get(name);
				nodeHandler.handleNode(nodeToHandle, contents);
				isDynamic = true;
			}
		}
		return null;
	}

	private Class<?> resolveType(String type) {
		try {
			return Class.forName(type);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
