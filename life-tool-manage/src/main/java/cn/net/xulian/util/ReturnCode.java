package cn.net.xulian.util;

public enum ReturnCode {
	SUCCESS, FAILURE, REPEAT, REGEX_CHINESE;

	String value;

	public String getValue() {
		return this.value;
	}
}
