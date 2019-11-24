package com.tt.mybatis.sqlsource;

import com.tt.mybatis.sqlsource.iface.SqlSource;

import java.util.List;

/**
 * @author lizhuo
 * @Description:
 * @date 2019-11-24 20:12
 */
public class StaticSqlSource implements SqlSource {

	private String sqlText;

	private List<ParameterMapping> parameterMappings;

	public StaticSqlSource() {
	}

	public StaticSqlSource(String sqlText, List<ParameterMapping> parameterMappings) {
		this.sqlText = sqlText;
		this.parameterMappings = parameterMappings;
	}

	@Override
	public BoundSql getBoundSql(Object paramObject) {
		return new BoundSql(sqlText, parameterMappings);
	}

}
