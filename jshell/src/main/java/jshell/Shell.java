package jshell;

import java.io.File;

import command.Command;
import command.CommandFactory;

public final class Shell {
	
	private static final InputInterpreter<Line> INPUT_INTERPRETER;
	private static final CommandDefinition COMMAND_DEFINITION;
	
	static {
		INPUT_INTERPRETER = LineInputInterpreter.getInstance();
		COMMAND_DEFINITION = XmlCommandDefinition.getInstance(new File("commands.xml"));
	}
	
	private Shell() {
		
		throw new AssertionError();
	}
	
	public static final Command process(Line line){
		return INPUT_INTERPRETER.process(line); 
	}
	
	public static final void execute(Command command){
		command.execute();
	}
	
	public static final Class<CommandFactory> getCommandFactoryClass(String commandName){
		return COMMAND_DEFINITION.getCommandClass(commandName);
	}
	
	

}
