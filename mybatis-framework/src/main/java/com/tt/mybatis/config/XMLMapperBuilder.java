package com.tt.mybatis.config;

import com.tt.mybatis.utils.DocumentUtils;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.InputStream;
import java.util.List;

/**
 * @author lizhuo
 * @Description: 映射配置文件解析
 * @date 2019-11-20 15:03
 */
public class XMLMapperBuilder {

	private Configuration configuration;

	public XMLMapperBuilder() {
	}

	public XMLMapperBuilder(Configuration configuration) {
		this.configuration = configuration;
	}

	public void parse(InputStream inputStream) {
		Document document = DocumentUtils.readDocument(inputStream);
		Element rootElement = document.getRootElement();

		String namespace = rootElement.attributeValue("namespace");
		List<Element> selectElements = rootElement.elements("select");
		for (Element selectElement: selectElements) {
			XMLStatementBuilder xmlStatementBuilder = new XMLStatementBuilder(configuration);
			xmlStatementBuilder.parse(namespace, selectElement);
		}
	}

}
