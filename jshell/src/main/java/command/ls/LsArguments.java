package command.ls;

import java.util.Collections;
import java.util.List;

import arguments.Arguments;

public class LsArguments implements Arguments{
	
	private final List<String> arguments;
	
	public LsArguments(List<String> args) {
		if(args == null || args.isEmpty()) {
			throw new IllegalArgumentException("Args shuld not be null or empty; args = " + args);
		}
		this.arguments = Collections.unmodifiableList(args);
	}
	
	@Override
	public String toString() {
		return arguments.toString();
	}

}
