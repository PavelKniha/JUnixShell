package jshell;

public interface InputParser<T> {
	
	T parse (String input);
}
