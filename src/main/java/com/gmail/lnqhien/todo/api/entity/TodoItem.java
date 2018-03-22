package com.gmail.lnqhien.todo.api.entity;

public class TodoItem {

	private long id;
	private boolean done;
	private String text;

	public TodoItem() {
	}
	
	public TodoItem(long id, boolean done, String text) {
		this.id = id;
		this.done = done;
		this.text = text;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
