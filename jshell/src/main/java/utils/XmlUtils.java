package utils;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public final class XmlUtils {
	private static final DocumentBuilderFactory FACTORY;
	private static DocumentBuilder BUILDER;
	
	static{
		FACTORY = DocumentBuilderFactory.newInstance();
		BUILDER = getBuilder();
	}
	
	private static final DocumentBuilder getBuilder(){
		if (BUILDER == null) {
			try {
				BUILDER = FACTORY.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				throw new RuntimeException(e);
			}
		}
		return BUILDER;
	}

	public static final Document getDocument(File file) {
		Document doc = null;
		try {
			doc = BUILDER.parse(file);
		} catch (SAXException | IOException e) {
			throw new RuntimeException(e);
		}
		return doc;
	}
	
	
}
