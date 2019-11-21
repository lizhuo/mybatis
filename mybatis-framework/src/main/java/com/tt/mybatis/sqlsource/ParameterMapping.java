package com.tt.mybatis.sqlsource;

/**
 * @author lizhuo
 * @Description: SQL语句的 参数名称 & 类型
 * @date 2019-11-21 10:21
 */
public class ParameterMapping {

	private String name;

	private Class<?> type;

	public ParameterMapping() {
	}

	public ParameterMapping(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

}
