package com.tt.mybatis.sqlsession;

/**
 * @author lizhuo
 * @Description: 生产 SQLSession
 * @date 2019-11-22 10:34
 */
public interface SqlSessionFactory {

	SqlSession openSession();

}
