package com.tt.mybatis.sqlsource;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lizhuo
 * @Description: 封装JDBC能够执行的SQL语句和参数类型
 * @date 2019-11-21 10:18
 */
public class BoundSql {

	// JDBC能够执行的SQL语句
	private String sql;

	// 参数集合
	private List<ParameterMapping> parameterMappings = new ArrayList<ParameterMapping>();

	public BoundSql() {
	}

	public BoundSql(String sql, List<ParameterMapping> parameterMappings) {
		this.sql = sql;
		this.parameterMappings = parameterMappings;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public List<ParameterMapping> getParameterMappings() {
		return parameterMappings;
	}

	public void setParameterMappings(List<ParameterMapping> parameterMappings) {
		this.parameterMappings = parameterMappings;
	}
}
