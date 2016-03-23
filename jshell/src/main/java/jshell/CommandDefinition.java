package jshell;

import command.Command;
import command.CommandFactory;

public interface CommandDefinition {
	
	Class<CommandFactory> getCommandClass(String commandName);
	
}
