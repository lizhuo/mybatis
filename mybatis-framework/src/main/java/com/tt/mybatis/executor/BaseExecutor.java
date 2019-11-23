package com.tt.mybatis.executor;

import com.tt.mybatis.config.Configuration;
import com.tt.mybatis.config.MappedStatement;
import com.tt.mybatis.executor.iface.Executor;

import java.util.List;

/**
 * @author lizhuo
 * @Description: 一级缓存
 * @date 2019-11-22 15:09
 */
public class BaseExecutor implements Executor {

	private Executor delegate;

	public BaseExecutor() {
	}

	public BaseExecutor(Executor delegate) {
		this.delegate = delegate;
	}


	@Override
	public <T> List<T> query(MappedStatement mappedStatement, Configuration configuration, Object param) {
		// 处理二级缓存

		return this.delegate.query(mappedStatement, configuration, param);
	}

}
