package com.tt.mybatis.io;

import java.io.InputStream;
import java.io.Reader;

/**
 * @author lizhuo
 * @Description: 资源加载类
 * @date 2019-11-20 10:32
 */
public class Resources {

	public static InputStream getResourceAsStream(String location) {
		return Resources.class.getClassLoader().getResourceAsStream(location);
	}

	public static Reader getResourceAsReader(String location) {
		return null;
	}

}
