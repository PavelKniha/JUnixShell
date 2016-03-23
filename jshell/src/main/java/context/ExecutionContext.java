package context;

import jshell.Output;
import arguments.Arguments;

public interface ExecutionContext {
	
	Arguments getArguments();

	Output getOutput();

}
