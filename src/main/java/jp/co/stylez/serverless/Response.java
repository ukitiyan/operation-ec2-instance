package jp.co.stylez.serverless;

import java.util.Map;

public class Response {

	private final String message;
	private final Map<String, String> input;

	public Response(String message, Map<String, String> input) {
		this.message = message;
		this.input = input;
	}

	public String getMessage() {
		return this.message;
	}

	public Map<String, String> getInput() {
		return this.input;
	}
}
