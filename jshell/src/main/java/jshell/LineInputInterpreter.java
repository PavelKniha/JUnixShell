package jshell;

import java.io.File;

import javax.management.RuntimeErrorException;

import jshell.ValidationResult.Type;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import command.Command;
import command.CommandFactory;
import command.CommandsName;
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
		
		CommandFactory factory = null;
		try {
			Class<CommandFactory> clazz = Shell.getCommandFactoryClass(commandName);
			factory = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException | NullPointerException e) {
			throw new RuntimeException(e);
		}
		Command command = factory.createCommand(line.getArguments());
		return command;
	}

	public ValidationResult validate(Line line) {
		if(!commandExist(line)){
			return new ValidationResult(Type.ERROR, "Command " + line.getCommandName() + " did not exist");
		}
		return new ValidationResult(Type.PASSED);
	}

	private boolean commandExist(Line line) {
		boolean exist = false;
		String lowerCommandName = line.getCommandName().toLowerCase();
		for (CommandsName name : CommandsName.values()){
			String lowerEnumName = name.toString().toLowerCase();
			if(lowerEnumName.equals(lowerCommandName)){
				return true;				
			}
		}
		return exist;
	}

	
	public static LineInputInterpreter getInstance() {
		return INSTANCE;
	}

}
