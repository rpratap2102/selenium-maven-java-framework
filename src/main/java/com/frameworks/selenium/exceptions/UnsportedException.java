package com.frameworks.selenium.exceptions;

public class UnsportedException extends RuntimeException {

	private static final long serialVersionUID = 8687554839443405232L;

	public UnsportedException(String message) {
		super(message);
	}

	public UnsportedException(String message, Throwable cause) {
		super(message, cause);
	}
}
