package com.gmail.lnqhien.todo.entity;

/**
 * Used to return a JSON representation of HTTP error code.
 */
public class Error {
	public Error(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	int status;
	String message;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
