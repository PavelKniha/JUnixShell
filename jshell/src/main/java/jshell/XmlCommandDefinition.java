package jshell;

import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public final class XmlCommandDefinition implements CommandDefinition {

	public static final String EMPTY_STRING = "";
	private static final int REQUERD_ATTR_LENGTH = XmlCommandAttribute.values().length;
	
	private final Document document;
	private final NodeList docElements;
	private final File xmlFile;
	
	private static enum XmlCommandAttribute {
		NAME, CLASS
	}

	public XmlCommandDefinition(File file) {
		if (file == null) {
			throw new NullPointerException(
					"Commands file defenition should not be null; file = " + file);
		}
		this.xmlFile = file;
		this.document = XmlUtils.getDocument(file);
		Node node = document.getDocumentElement();
		node.normalize();
		this.docElements = node.getChildNodes();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Class<Command> getCommandClass(String commandName) {
		if (commandName == null || commandName.isEmpty()) {
			throw new IllegalArgumentException(
					"commandName must not be null or empty: commandName = "
							+ commandName);
		}
		Class<Command> clazz = null;
		for (int i = 0; i < docElements.getLength(); i++) {
			Node node = docElements.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				NamedNodeMap attributes = getNodeAttributes(node);
				
				String name = getAttributeValue(attributes, XmlCommandAttribute.NAME);
				if (commandName.equals(name)) {
					String className = getAttributeValue(attributes, XmlCommandAttribute.CLASS);
					
					try {
						clazz = (Class<Command>) Class.forName(className);
					} catch (ClassNotFoundException | ClassCastException e) {
						throw new RuntimeException(e);
					}
				}
			}
		}
		return clazz;
	}
	

	private final String getAttributeValue(NamedNodeMap attributes, XmlCommandAttribute attribute){
		Node nameNode = attributes.getNamedItem(attribute.toString().toLowerCase());
		String name = nameNode != null ? nameNode.getNodeValue() : EMPTY_STRING;
		return name;
	}
	
	private final NamedNodeMap getNodeAttributes(Node node){
		if (node == null) {
			throw new NullPointerException("Node should not be null; node = " + node);
		}
		NamedNodeMap attributes = node.getAttributes();
		int attrLengts = attributes != null ? attributes.getLength() : 0;
		if (attributes == null || attrLengts != REQUERD_ATTR_LENGTH) {
			throw new IllegalArgumentException(
					"Command attribute should not be null, or length should be = "
							+ REQUERD_ATTR_LENGTH + "; attrubutes = "
							+ attributes + " , attributesLength = "
							+ attrLengts);
		}
		return attributes;
	}

	public static final CommandDefinition getInstance(File file) {
		return new XmlCommandDefinition(file);
	}

}