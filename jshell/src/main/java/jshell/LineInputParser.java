package jshell;


public final class LineInputParser implements InputParser<Line>{
	
	private static final LineInputParser INSTANCE= new LineInputParser();
	
	private LineInputParser(){}

	public Line parse(final String input) {
		String[] tokens = tokenize(input);		
		Line line = new SimpleLine(input, tokens);
		return line;
		
	}
		
	public static LineInputParser getInstance() {
		return INSTANCE;
	}

	private String[] tokenize(final String input) {
		String trimmed = input.trim();
		String[] tokens = trimmed.split("\\s+");
		return tokens;
	}
	

}
