package com.tt.mybatis.sqlnode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lizhuo
 * @Description: 封装了sql信息，可以封装入参信息
 * @date 2019-11-20 17:48
 */
public class DynamicContext {

	private StringBuffer sb;
	private Map<String, Object> bindings = new HashMap<String, Object>();

	public DynamicContext() {
	}

	/**
	 * 将传入的参数 封装到 Map 集合
	 * @param paramter
	 */
	public DynamicContext(Object paramter) {
		this.bindings.put("_parameterObject", paramter);
	}

	public String getSql() {
		return sb.toString();
	}

	public void appendSql(String sql) {
		sb.append(sql).append(" ");
	}

	public Map<String, Object> getBindings() {
		return bindings;
	}

}
