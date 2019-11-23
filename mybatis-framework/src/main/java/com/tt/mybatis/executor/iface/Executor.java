package com.tt.mybatis.executor.iface;

import com.tt.mybatis.config.Configuration;
import com.tt.mybatis.config.MappedStatement;

import java.util.List;

/**
 * @author lizhuo
 * @Description: TODO
 * @date 2019-11-22 15:08
 */
public interface Executor {

	/**
	 *
	 * @param mappedStatement 获取SQL语句和入参/出参信息
	 * @param configuration 获取数据源连接池信息
	 * @param param 获取入参类型
	 * @param <T>
	 * @return
	 */
	<T> List<T> query(MappedStatement mappedStatement, Configuration configuration, Object param);

}
