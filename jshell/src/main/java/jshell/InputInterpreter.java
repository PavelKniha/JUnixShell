package jshell;

import command.Command;

public interface InputInterpreter<T> {
	
	Command process(T line);
}
