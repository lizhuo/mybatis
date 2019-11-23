package com.tt.mybatis.sqlsession;

import com.tt.mybatis.config.Configuration;
import com.tt.mybatis.config.XMLConfigBuilder;

import java.io.InputStream;
import java.io.Reader;

/**
 * @author lizhuo
 * @Description: 使用构建则模式 创建 SQLSession Factory
 * @date 2019-11-22 10:34
 */
public class SqlSessionFactoryBuilder {

	private Configuration configuration;

	public SqlSessionFactory build(InputStream inputStream) {
		XMLConfigBuilder configBuilder = new XMLConfigBuilder();
		configuration = configBuilder.parse(inputStream);
		return build();
	}

	public SqlSessionFactory build(Reader reader) {
		return null;
	}

	private SqlSessionFactory build() {
		return new DefaultSqlSessionFactory(configuration);
	}

}
