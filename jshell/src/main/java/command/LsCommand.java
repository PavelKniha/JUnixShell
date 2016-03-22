package jshell;

public class LsCommand implements Command{
	
	private final ExecutionContext context;
	
	public LsCommand() {
		this(new DefaultExecutionContext());
	}
	public LsCommand(ExecutionContext context) {
		this.context = context;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void execute() {
		System.out.println("hello ls");
		
	}

}
