package command;

import context.DefaultExecutionContext;
import context.ExecutionContext;

public final class ExitCommand implements Command{
	
	public static final String NAME = "exit";
	private final ExecutionContext context;
	
	public ExitCommand() {
		this(new DefaultExecutionContext());
	}
	public ExitCommand(ExecutionContext context) {
		this.context = context;
	}
	
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void execute() {
		System.exit(1);		
	}

}
