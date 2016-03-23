package command.ls;

import jshell.Output;
import command.Command;
import context.DefaultExecutionContext;
import context.ExecutionContext;

public class LsCommand implements Command{
	
	private final ExecutionContext context;
	
	public LsCommand(ExecutionContext context) {
		this.context = context;
		// process arguments from context
	}

	@Override
	public String getName() {
		return "ls";
	}

	@Override
	public void execute() {
		Output out = context.getOutput();
		out.write(getName());
		out.write(context.getArguments().toString());		
	}

}
