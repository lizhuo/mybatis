package com.tt.mybatis.config;

import com.tt.mybatis.io.Resources;
import com.tt.mybatis.utils.DocumentUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @author lizhuo
 * @Description: 全局XML解析
 * @date 2019-11-20 10:48
 */
public class XMLConfigBuilder {

	private Configuration configuration;

	public XMLConfigBuilder() {
		configuration = new Configuration();
	}

	/**
	 * 从根标签开始解析配置文件
	 * @param inputStream
	 * @return
	 */
	public Configuration parse(InputStream inputStream) {
		Document document = DocumentUtils.readDocument(inputStream);
		Element rootElement = document.getRootElement();
		parseConfiguration(rootElement);
		return configuration;
	}

	/**
	 *
	 * @param rootElement <configuration>
	 */
	private void parseConfiguration(Element rootElement) {
		Element environmentsElement = rootElement.element("environments");
		parseEnvironmentsElement(environmentsElement);

		Element mappersElement = rootElement.element("mappers");
		parseMapperElement(mappersElement);
	}

	private void parseMapperElement(Element mappersElement) {
		List<Element> elements = mappersElement.elements("mapper");
		for (Element mapperElement : elements) {
			parseMapper(mapperElement);
		}
	}

	private void parseMapper(Element mapperElement) {
		String resource = mapperElement.attributeValue("resource");
		InputStream inputStream = Resources.getResourceAsStream(resource);
		XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(this.configuration);
		xmlMapperBuilder.parse(inputStream);
	}

	/**
	 *
	 * @param environmentsElement <environments>
	 */
	private void parseEnvironmentsElement(Element environmentsElement) {
		String defaultId = environmentsElement.attributeValue("default");
		if (defaultId == null || defaultId.equals("")) {
			return;
		}
		List<Element> elements = environmentsElement.elements("environment");
		for (Element envElement : elements) {
			String id = envElement.attributeValue("id");
			if (defaultId.equals(id)) {
				parseDataSource(envElement.element("dataSource"));
			}
		}
	}

	/**
	 *
	 * @param dbElement <dataSource>
	 */
	private void parseDataSource(Element dbElement) {
		String dbType = dbElement.attributeValue("type");
		if ("DBCP".equals(dbType)) {
			BasicDataSource dataSource = new BasicDataSource();

			Properties properties = new Properties();
			List<Element> propertyElements = dbElement.elements();
			for (Element prop : propertyElements) {
				String name = prop.attributeValue("name");
				String value = prop.attributeValue("value");
				properties.put(name, value);
			}

			dataSource.setDriverClassName(properties.getProperty("driver"));
			dataSource.setUrl(properties.getProperty("url"));
			dataSource.setUsername(properties.getProperty("username"));
			dataSource.setPassword(properties.getProperty("password"));

			configuration.setDataSource(dataSource);
		}
	}

}
