package com.gmail.lnqhien.todo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Represents and individual task item on someone's ToDo list.
 * Based on TodoMVC features:
 *  1. enter item text
 *  2. mark item as done
 *  3. delete item
 */
@Entity
public class Item {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private boolean done;
	private String text;

	public Item() {
	}
	
	public Item(boolean done, String text) {
		this.done = done;
		this.text = text;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
