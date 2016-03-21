package jshell;

public interface CommandDefinition {
	
	Class<Command> getCommandClass(String commandName);
	
}
