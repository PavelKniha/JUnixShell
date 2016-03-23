package command;

import java.util.List;

public interface CommandFactory {
	
	Command createCommand(List<String> args);

}
