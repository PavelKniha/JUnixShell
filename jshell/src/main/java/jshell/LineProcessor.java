package jshell;

public interface LineProcessor<T> {
	
	Command process(T line);
}
