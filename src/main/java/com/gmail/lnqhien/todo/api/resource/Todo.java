package com.gmail.lnqhien.todo.api.resource;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.gmail.lnqhien.todo.api.entity.TodoItem;

@Component
@Path("/todo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Todo {
	
	@POST
	public TodoItem add(TodoItem item) {
		item.setId(1);
		return item;
	}
	
	@GET
	@Path("/{id}")
	public TodoItem get(@PathParam("id") long id) {
		return new TodoItem(id, false, "todo 1");
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") long id) {
	}
	
	@PUT
	@Path("/{id}")
	public TodoItem update(@PathParam("id") long id, TodoItem item) {
		item.setId(id);
		return item;
	}
	
	@GET
	public List<TodoItem> getMany() {
		return Arrays.asList(
			new TodoItem(1, false, "todo 1"),
			new TodoItem(2, false, "todo 2")
		);
	}
}
