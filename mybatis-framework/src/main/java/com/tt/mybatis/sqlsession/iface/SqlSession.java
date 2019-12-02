package com.tt.mybatis.sqlsession.iface;

import java.util.List;

/**
 * @author lizhuo
 * @Description: 表示一次SQL会话
 * @date 2019-11-22 10:56
 */
public interface SqlSession {

	<T> T selectOne(String statementId, Object param);

	<T> List<T> selectList(String statementId, Object param);

}
