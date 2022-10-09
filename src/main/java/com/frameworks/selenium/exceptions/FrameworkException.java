package com.frameworks.selenium.exceptions;

public class FrameworkException extends RuntimeException {

	private static final long serialVersionUID = 7995785612808502182L;

	public FrameworkException(String message) {
		super(message);
	}

	public FrameworkException(String message, Throwable cause) {
		super(message, cause);
	}
}