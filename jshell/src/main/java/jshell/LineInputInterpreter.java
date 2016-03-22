package jshell;

import java.io.File;

import jshell.ValidationResult.Type;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import command.Command;
import command.ExitCommand;
import command.InvalidCommand;

public final class LineInputInterpreter implements InputInterpreter<Line> {

	private static final LineInputInterpreter INSTANCE = new LineInputInterpreter();

	private LineInputInterpreter() {}

	public Command process(final Line line) {
		ValidationResult validateResult = validate(line);
		if (validateResult != null && !validateResult.passed()) {
			return new InvalidCommand(validateResult.getInfo());
		}
		String commandName = line.getCommandName();
		if (commandName.equals(ExitCommand.NAME)){
			return new ExitCommand();
		}
// TODO: implement interfaces
//		CommandFactory commandFactory = null;
//		context = new ExecutionContext(new Arguments(line.getArguments()), new StandartOutput());
//		command.execute(context)
		
		Command command = null;
		try {
			Class<Command> clazz = Shell.getCommandClass(commandName);
			command = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		return command;
	}

	public ValidationResult validate(Line line) {
		return new ValidationResult(Type.PASSED);
	}

	public static LineInputInterpreter getInstance() {
		return INSTANCE;
	}

}
