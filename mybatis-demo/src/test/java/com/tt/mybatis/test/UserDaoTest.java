package com.tt.mybatis.test;

import com.tt.mybatis.config.Configuration;
import com.tt.mybatis.config.XMLConfigBuilder;
import com.tt.mybatis.io.Resources;
import org.junit.Test;

import java.io.InputStream;

/**
 * @author lizhuo
 * @Description: Test Case
 * @date 2019-11-20 10:28
 */
public class UserDaoTest {

	@Test
	public void testInitConfiguration() {
		// 1. 指定全局配置文件路径
		String location = "mybatis-config.xml";

		// 2. 加载配置文件成InputStream
		InputStream inputStream = Resources.getResourceAsStream(location);

		// 3. 根据InputStream获取Document对象
		XMLConfigBuilder configBuilder = new XMLConfigBuilder();

		// 4. 需要专门对mybatis标签进行解析
		Configuration configuration = configBuilder.parse(inputStream);
		System.out.println(configuration);
	}

}