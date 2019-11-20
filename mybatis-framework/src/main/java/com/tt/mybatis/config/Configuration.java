package com.tt.mybatis.config;

import javax.sql.DataSource;

/**
 * @author lizhuo
 * @Description: 封装全局配置文件和映射配置文件中的信息
 * @date 2019-11-20 10:54
 */
public class Configuration {

	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
