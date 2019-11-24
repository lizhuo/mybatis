package com.tt.mybatis.sqlsession;

import com.tt.mybatis.config.Configuration;
import com.tt.mybatis.config.MappedStatement;
import com.tt.mybatis.executor.CachingExecutor;
import com.tt.mybatis.executor.SimpleExecutor;
import com.tt.mybatis.executor.iface.Executor;

import java.util.List;

/**
 * @author lizhuo
 * @Description: Default sql session
 * @date 2019-11-22 11:00
 */
public class DefaultSqlSession implements SqlSession {

	private Configuration configuration;

	public DefaultSqlSession(Configuration configuration) {
		this.configuration = configuration;
	}

	@Override
	public <T> T selectOne(String statementId, Object param) {
		List<Object> list = this.selectList(statementId, param);
		if (list != null && list.size() == 1) {
			return (T) list.get(0);
		}
		return null;
	}

	@Override
	public <T> List<T> selectList(String statementId, Object param) {
		MappedStatement mappedStatement = this.configuration.getMappedStatement(statementId);
		Executor executor = new CachingExecutor(new SimpleExecutor());
		return executor.query(mappedStatement, configuration, param);
	}

}
