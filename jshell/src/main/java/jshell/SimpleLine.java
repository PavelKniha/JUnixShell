package jshell;


public class SimpleLine implements Line {

	private final String rawContent;
	private final String lineEnding;
	private final String[] tokenedContent;

	public SimpleLine(String content) {
		this(content, LINE_ENDING);
	}

	public SimpleLine(String content, String lineEnding) {
		if (content == null || lineEnding == null) {
			throw new NullPointerException(
					"content, lineEnding must != null; content:lineEnding =  "
							+ content + " : " + lineEnding);
		}
		String[] tokens = tokenize(content);
		int tokensLength = tokens.length;
		if (tokensLength <= 0) {
			throw new IllegalArgumentException(
					"Line must have minimum 1 token; tokenLength = "
							+ tokensLength);
		}
		this.rawContent = content;
		this.lineEnding = lineEnding;
		this.tokenedContent = tokens;
	}

	@Override
	public String getRawContent() {
		return this.rawContent;
	}

	@Override
	public String getLineEnding() {
		return lineEnding;
	}

	public String[] getTokenedContent() {
		int tokensLength = this.tokenedContent.length;
		String[] copy = new String[tokensLength];
		System.arraycopy(this.tokenedContent, 0, copy, 0, tokensLength);
		return copy;
	}
	
	private String[] tokenize(final String input) {
		String trimmed = input.trim();
		String[] tokens = trimmed.split("\\s+");
		return tokens;
	}
	
	

}
