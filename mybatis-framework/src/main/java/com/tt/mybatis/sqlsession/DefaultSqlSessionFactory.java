package com.tt.mybatis.sqlsession;

import com.tt.mybatis.config.Configuration;

/**
 * @author lizhuo
 * @Description: Default
 * @date 2019-11-22 10:52
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

	private Configuration configuration;

	public DefaultSqlSessionFactory(Configuration configuration) {
		this.configuration = configuration;
	}

	@Override
	public SqlSession openSession() {
		return new DefaultSqlSession(configuration);
	}

}
