package jshell;

import java.util.NoSuchElementException;
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
		Command command = Shell.process(new SimpleLine(input));
		Shell.execute(command);
	}

	private static String readCommandLine() {
		Scanner sc = new Scanner(System.in);
		String input = null;
		try {
			input = sc.nextLine();
		} catch (NoSuchElementException | IllegalStateException e) {
			throw new RuntimeException(e);
		}
		return input;
	}

	private JUnixShell() {
		throw new AssertionError();
	}
}
