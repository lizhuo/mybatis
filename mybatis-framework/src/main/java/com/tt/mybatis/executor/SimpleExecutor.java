package com.tt.mybatis.executor;

import com.tt.mybatis.config.Configuration;
import com.tt.mybatis.config.MappedStatement;
import com.tt.mybatis.sqlsource.BoundSql;
import com.tt.mybatis.sqlsource.ParameterMapping;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lizhuo
 * @Description: 简单执行 JDBC 代码
 * @date 2019-11-23 14:25
 */
public class SimpleExecutor extends BaseExecutor {

	// TODO 待实现  StatementHandler ParameterHandler ResultSetHandler
	@Override
	public List<Object> queryDataBase(MappedStatement mappedStatement, Configuration configuration,
	                                  Object param, BoundSql boundSql) {
		List<Object> results = new ArrayList<Object>();
		try {
			// 获取连接
			Connection connection = getConnection(configuration);

			// 获取SQL语句
			String sql = boundSql.getSql();
			ResultSet resultSet = null;

			// 判断创建哪种 Statement
			if ("prepared".equals(mappedStatement.getStatementType())) {
				// 创建 Statement
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				// 设置参数
				parameterize(preparedStatement, mappedStatement, boundSql, param);
				// 执行 Statement
				resultSet = preparedStatement.executeQuery();
			} else if ("callable".equals(mappedStatement.getStatementType())) {
				// 创建 Statement
				// 设置参数
				// 执行 Statement
			}

			// 处理结果集
			if (resultSet != null) {
				handleResultSet(mappedStatement, resultSet, results);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	// TODO ResultHandler
	private void handleResultSet(MappedStatement mappedStatement, ResultSet resultSet, List<Object> results)
			throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {
		Class<?> resultTypeClass = mappedStatement.getResultTypeClass();
		while (resultSet.next()) {
			Object result = resultTypeClass.newInstance();
			// 列的名称要和属性一致
			ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();
			for (int i = 0; i < columnCount; i++) {
				String columnName = metaData.getColumnName(i + 1);
				Field field = resultTypeClass.getDeclaredField(columnName);
				field.setAccessible(true);
				field.set(result, resultSet.getObject(i));
			}
			results.add(result);
		}
	}

	// TODO ParameterHandler
	private void parameterize(PreparedStatement preparedStatement, MappedStatement mappedStatement,
	                          BoundSql boundSql, Object param)
			throws SQLException, NoSuchFieldException, IllegalAccessException {
		// 先判断入参类型  TODO StatementHandler
		Class<?> parameterTypeClass = mappedStatement.getParameterTypeClass();
		if (parameterTypeClass == Integer.class) {
			preparedStatement.setObject(1, Integer.parseInt(param.toString()));
		} else if (parameterTypeClass == String.class) {
			preparedStatement.setObject(1, param.toString());
		} else if (parameterTypeClass == Map.class) {

		} else { // 自定义对象类型 -- 此种类型才关注入参
			List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
			for (int i = 0; i < parameterMappings.size(); i++) {
				// 获取#{}中的属性 名称
				String name = parameterMappings.get(i).getName();

				// 根据属性名称，获取入参对象中对应的属性的值
				// 要求#{}中的属性名称和入参对象中的属性名称一致
				Field field = parameterTypeClass.getDeclaredField(name);
				field.setAccessible(true);
				Object value = field.get(param);

				preparedStatement.setObject(i + 1, value);
			}
		}
	}

	private Connection getConnection(Configuration configuration) {
		Connection connection = null;
		try {
			DataSource dataSource = configuration.getDataSource();
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

}
