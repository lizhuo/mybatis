package com.tt.mybatis.sqlsource.iface;

import com.tt.mybatis.sqlsource.BoundSql;

/**
 * @author lizhuo
 * @Description: TODO
 * @date 2019-11-20 17:09
 */
public interface SqlSource {

	/**
	 * 根据入参获取JDBC可以执行的SQL语句
	 *
	 * 执行阶段才会调用该方法
	 *
	 * @param paramObject
	 * @return
	 */
	BoundSql getBoundSql(Object paramObject);

}
