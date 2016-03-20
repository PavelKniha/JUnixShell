package jshell;

public final class Shell {
	
	private static final LineInputParser INPUT_PARSER;
	private static final LineProcessorImpl LINE_PROCESSOR;

	static {
		INPUT_PARSER = LineInputParser.getInstance();
		LINE_PROCESSOR = LineProcessorImpl.getInstance();
	}
	
	private Shell() {
		throw new AssertionError();
	}
	
	public static final Line parse(String input){ 
		return INPUT_PARSER.parse(input);
	}
	
	public static final Command process(Line line){
		return LINE_PROCESSOR.process(line); 
	}
	
	public static final void execute(Command command){
		command.execute();
	}
	
	

}
