package jshell;


public class SimpleLine implements Line {

	private final String rawContent;
	private final String lineEnding;
	private final String[] tokenedContent;

	public SimpleLine(String content, String[] tokens) {
		this(content, LINE_ENDING, tokens);
	}

	public SimpleLine(String content, String lineEnding, String[] tokens) {
		if (content == null || tokens == null || lineEnding == null) {
			throw new NullPointerException(
					"content, tokens, lineEnding must != null; content:tokens:lineEnding =  "
							+ content + " : " + tokens + " : " + lineEnding);
		}
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
	public String[] getTokenedContent() {
		int tokensLength = this.tokenedContent.length;
		String[] copy = new String[tokensLength];
		System.arraycopy(this.tokenedContent, 0, copy, 0, tokensLength);
		return copy;
	}

	public String getLineEnding() {
		return lineEnding;
	}

}
