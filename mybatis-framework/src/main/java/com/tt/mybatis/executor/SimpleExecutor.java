package com.tt.mybatis.executor;

import com.tt.mybatis.config.Configuration;
import com.tt.mybatis.config.MappedStatement;

import java.util.List;

/**
 * @author lizhuo
 * @Description: TODO
 * @date 2019-11-23 14:25
 */
public class SimpleExecutor extends BaseExecutor {

	@Override
	public <T> List<T> query(MappedStatement mappedStatement, Configuration configuration, Object param) {
		return null;
	}

}
