package jshell;

import java.util.List;

public interface Line{

	String LINE_ENDING = System.getProperty("line.separator");

	String getRawContent();
	
	String getLineEnding();

	String getCommandName();
	
	List<String> getArguments();
}
