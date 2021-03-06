package com.tt.mybatis.config;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lizhuo
 * @Description: 封装全局配置文件和映射配置文件中的信息
 * @date 2019-11-20 10:54
 */
public class Configuration {

	private DataSource dataSource;
	private Map<String, MappedStatement> mappedStatements = new HashMap<String, MappedStatement>();

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void addMappedStatement(String statementId, MappedStatement mappedStatement) {
		this.mappedStatements.put(statementId, mappedStatement);
	}

	public MappedStatement getMappedStatement(String statementId) {
		return mappedStatements.get(statementId);
	}
}
