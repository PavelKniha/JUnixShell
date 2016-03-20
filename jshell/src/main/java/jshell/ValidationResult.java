package jshell;

public final class ValidationResult {
	private final Type type;
	private final String info;

	public static enum Type {
		PASSED, ERROR
	}

	public ValidationResult(Type type) {
		this(type, null);
	}

	public ValidationResult(Type type, String info) {
		this.type = type;
		String resultInfo = info;
		if (info == null) {
			resultInfo = type.equals(Type.ERROR) ? "Unknown validation error"
					: "Validation passed";
		}
		this.info = resultInfo;
	}

	public Type getType() {
		return type;
	}

	public String getInfo() {
		return info;
	}
	
	public boolean passed(){
		return type.equals(Type.PASSED);
	}

}
