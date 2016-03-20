package jshell;

public interface Line{

	String LINE_ENDING = System.getProperty("line.separator");

	String getRawContent();
	
	String[] getTokenedContent();
}
