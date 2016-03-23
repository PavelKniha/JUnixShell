package jshell;

public class ConsoleOutput implements Output{

	@Override
	public void write(String result) {
		System.out.println(result);
	}
	

}
