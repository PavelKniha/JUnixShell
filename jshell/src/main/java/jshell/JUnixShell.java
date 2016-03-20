package jshell;

import java.util.Scanner;

public final class JUnixShell {

	public static void main(String[] args) {
		while (true) {
			proccessCommandLine();
		}
	}

	private static void proccessCommandLine() {
		String input = readCommandLine();
		if (input == null || input.isEmpty()) {
			return;
		}
		Line line = Shell.parse(input);
		Command command = Shell.process(line);
		Shell.execute(command);
	}

	private static String readCommandLine() {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		return input;
	}

	private JUnixShell() {
		throw new AssertionError();
	}
}
