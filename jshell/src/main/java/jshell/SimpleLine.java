package jshell;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class SimpleLine implements Line {
	
	private static final int MIN_CONTENT_LENGTH = 1;

	private final String rawContent;
	private final String lineEnding;
	private final String[] tokenedContent;
	private final List<String> arguments;


	public SimpleLine(String content) {
		this(content, LINE_ENDING);
	}

	public SimpleLine(String content, String lineEnding) {
		if (content == null || lineEnding == null) {
			throw new NullPointerException(
					"content, lineEnding must != null; content:lineEnding =  "
							+ content + " : " + lineEnding);
		}

		this.tokenedContent = tokenize(content);
		this.arguments = getArgumentsInternal();
		this.rawContent = content;
		this.lineEnding = lineEnding;
	}

	@Override
	public String getRawContent() {
		return this.rawContent;
	}

	@Override
	public String getLineEnding() {
		return this.lineEnding;
	}
	
	@Override
	public String getCommandName() {
		return this.tokenedContent[0];
	}
	
	@Override
	public List<String> getArguments() {
		return this.arguments;
	}
	
	private String[] tokenize(final String input) {
		String trimmed = input.trim();
		String[] tokens = trimmed.split("\\s+");
		int tokensLength = tokens.length;
		if (tokensLength < MIN_CONTENT_LENGTH) {
			throw new IllegalArgumentException(
					"Line must have minimum 1 token - command; tokenLength = "
							+ tokensLength);
		}
		return tokens;
	}
	
	@SuppressWarnings("unchecked")
	private List<String> getArgumentsInternal(){
		String[] args = null;
		if (this.tokenedContent.length > MIN_CONTENT_LENGTH) {
			System.arraycopy(this.tokenedContent, 1, args, 0, this.tokenedContent.length - 1);
		}
		return args == null ? Collections.EMPTY_LIST : Collections.unmodifiableList(Arrays.asList(args));
	}

	
	
	

}
