package jshell;

public interface InputInterpreter<T> {
	
	Command process(T line);
}
