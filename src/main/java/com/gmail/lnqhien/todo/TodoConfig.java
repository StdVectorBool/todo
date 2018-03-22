package com.gmail.lnqhien.todo;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.gmail.lnqhien.todo.api.resource.Todo;

@Component
public class TodoConfig extends ResourceConfig {
	public TodoConfig() {
		register(Todo.class);
	}
}
