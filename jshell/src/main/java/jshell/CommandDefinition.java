package jshell;

import command.Command;

public interface CommandDefinition {
	
	Class<Command> getCommandClass(String commandName);
	
}
