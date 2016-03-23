package command.ls;

import java.util.List;

import arguments.Arguments;
import command.Command;
import command.CommandFactory;
import context.DefaultExecutionContext;


public class LsFactory implements CommandFactory {

	@Override
	public Command createCommand(List<String> args) {
		Arguments arguments = null;
		arguments = (args == null || args.isEmpty()) ? Arguments.NoArgs
				: new LsArguments(args);
		Command command = new LsCommand(new DefaultExecutionContext(arguments));
		return command;

	}

}
