package com.tt.mybatis.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

/**
 * @author lizhuo
 * @Description: XML Parse
 * @date 2019-11-20 10:38
 */
public class DocumentUtils {

	public static Document readDocument(InputStream inputStream) {
		try {
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(inputStream);
			return document;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}

}
