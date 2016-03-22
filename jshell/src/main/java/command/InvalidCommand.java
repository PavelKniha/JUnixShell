package command;

import context.DefaultExecutionContext;
import context.ExecutionContext;

public final class InvalidCommand implements Command{
	
	public static final String NAME = "Invalid command";
	private final ExecutionContext executionContext;
	private final String errorInfo;


	public InvalidCommand(String errorInfo) {
		this(new DefaultExecutionContext(), errorInfo);
	}
	
	public InvalidCommand(ExecutionContext context, String errorInfo){
		this.executionContext = context;
		this.errorInfo = errorInfo;
	}
	
	public void execute() {
		System.out.println(errorInfo);
	}
	
	public String getName() {
		return NAME;
	}

}
