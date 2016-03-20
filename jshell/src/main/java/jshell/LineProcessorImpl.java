package jshell;

import java.io.File;

import jshell.ValidationResult.Type;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public final class LineProcessorImpl implements LineProcessor<Line> {

	private static final LineProcessorImpl INSTANCE = new LineProcessorImpl();
	private final Document document;
	private final NodeList nodeList;

	private LineProcessorImpl() {
		this.document = XmlUtils.parse(new File("commands.xml"));
		Node node = document.getDocumentElement();
		node.normalize();
		this.nodeList = node.getChildNodes();
	}

	public Command process(final Line line) {
		ValidationResult validateResult = validate(line);
		if (validateResult != null && !validateResult.passed()) {
			return new InvalidCommand(validateResult.getInfo());
		}
		String commandName = getCommandName(line);

		if (commandName.equals(ExitCommand.NAME)) {
			return new ExitCommand();
		}
		Command command = null;
		try {
			Class<Command> clazz = getCommandClass(commandName);
			command = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		return command;
	}

	@SuppressWarnings("unchecked")
	private Class<Command> getCommandClass(final String commandName)
			throws ClassNotFoundException {
		if (commandName == null || commandName.isEmpty()) {
			throw new IllegalArgumentException(
					"commandName must not be null or empty: commandName = "
							+ commandName);
		}
		Class<Command> clazz = null;
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				NamedNodeMap attributes = node.getAttributes();
				String name = attributes.getNamedItem("name").getNodeValue();
				if (commandName.equals(name)) {
					String className = attributes.getNamedItem("class")
							.getNodeValue();
					clazz = (Class<Command>) Class.forName(className);
				}
			}
		}
		return clazz;
	}

	private String getCommandName(final Line line) {
		String[] tokens = line.getTokenedContent();
		return tokens[0];
	}

	private ValidationResult validate(Line line) {
		return new ValidationResult(Type.ERROR);
	}

	public static LineProcessorImpl getInstance() {
		return INSTANCE;
	}

}
