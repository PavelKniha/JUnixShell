package jshell;

import java.io.File;

import jshell.ValidationResult.Type;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public final class LineInputInterpreter implements InputInterpreter<Line> {

	private static final LineInputInterpreter INSTANCE = new LineInputInterpreter();

	private LineInputInterpreter() {}

	public Command process(final Line line) {
		ValidationResult validateResult = validate(line);
		if (validateResult != null && !validateResult.passed()) {
			return new InvalidCommand(validateResult.getInfo());
		}
		String commandName = getCommandName(line);

		if (commandName.equals(ExitCommand.NAME)){
			return new ExitCommand();
		}
		Command command = null;
		try {
			Class<Command> clazz = Shell.getCommandClass(commandName);
			command = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		return command;
	}

	private String getCommandName(final Line line) {
		return "ls";
	}

	public ValidationResult validate(Line line) {
		return new ValidationResult(Type.ERROR);
	}

	public static LineInputInterpreter getInstance() {
		return INSTANCE;
	}

}
