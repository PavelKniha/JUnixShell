package context;

import jshell.ConsoleOutput;
import jshell.Output;
import arguments.Arguments;


public class DefaultExecutionContext implements ExecutionContext {
	
	private final Arguments arguments;
	private final Output output;

	public DefaultExecutionContext(Arguments arguments) {
		this.arguments = arguments;
		this.output = new ConsoleOutput();
	}

	public DefaultExecutionContext() {
		this(Arguments.NoArgs);
	}

	@Override
	public Arguments getArguments() {
		return this.arguments;
	}

	@Override
	public Output getOutput() {
		return this.output;
	}
	

}
