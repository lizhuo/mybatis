package com.tt.mybatis.sqlsource;

import com.tt.mybatis.sqlsource.iface.SqlSource;
import com.tt.mybatis.utils.GenericTokenParser;
import com.tt.mybatis.utils.ParameterMappingTokenHandler;

/**
 * @author lizhuo
 * @Description: 面向对象 OO
 * @date 2019-11-24 20:05
 */
public class SqlSourceParser {

	public SqlSource parse(String sqlText) {
		// 可以获取 ParameterMapping
		ParameterMappingTokenHandler tokenHandler  = new ParameterMappingTokenHandler();
		GenericTokenParser tokenParser = new GenericTokenParser("#{", "}",
				tokenHandler);

		// 此处获取的 SQL 语句 是完全符合 JDBC 执行要求的语句
		sqlText = tokenParser.parse(sqlText);

		// 将解析后的 SQL 语句和参数集合都封装到 StaticSqlSource 中
		return new StaticSqlSource(sqlText, tokenHandler.getParameterMappings());
	}

}
