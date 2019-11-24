package com.tt.mybatis.executor;

import com.tt.mybatis.config.Configuration;
import com.tt.mybatis.config.MappedStatement;
import com.tt.mybatis.executor.iface.Executor;
import com.tt.mybatis.sqlsource.BoundSql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lizhuo
 * @Description: 一级缓存
 * @date 2019-11-22 15:09
 */
public abstract class BaseExecutor implements Executor {


	private Map<String, List<Object>> oneLevelCache = new HashMap<String, List<Object>>();

	@Override
	public <T> List<T> query(MappedStatement mappedStatement, Configuration configuration, Object param) {
		// 调用SQLSource获取BoundSql
		BoundSql boundSql = mappedStatement.getSqlSource().getBoundSql(param);

		// 处理一级缓存
		List<Object> results = oneLevelCache.get(boundSql.getSql());
		if (results != null) {
			return (List<T>) results;
		}

		// 查询数据库
		results = queryDataBase(mappedStatement, configuration, param);
		oneLevelCache.put(boundSql.getSql(), results);
		return (List<T>) results;
	}

	public abstract List<Object> queryDataBase(MappedStatement mappedStatement, Configuration configuration, Object param);

}
