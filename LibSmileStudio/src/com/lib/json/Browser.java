package com.lib.json;

public enum Browser {

	Mozilla("Mozilla/5.0 (Macintosh; U; Intel Mac OS X; de-de) AppleWebKit/523.10.3 (KHTML, like Gecko) Version/3.0.4 Safari/523.10");

	private String value;

	private Browser(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
