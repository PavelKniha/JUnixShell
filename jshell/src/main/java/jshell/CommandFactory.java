package jshell;

import command.Command;
import context.ExecutionContext;
import arguments.Arguments;

public interface CommandFactory {
	
	Command createCommand(ExecutionContext context);

}
