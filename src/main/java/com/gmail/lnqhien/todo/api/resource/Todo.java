package com.gmail.lnqhien.todo.api.resource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gmail.lnqhien.todo.ItemRepository;
import com.gmail.lnqhien.todo.entity.Item;

@Component
@Path("/todo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Todo {
	
	@Autowired
	private ItemRepository repository;
	
	@POST
	public Item add(Item item) {
		return repository.save(new Item(item.isDone(), item.getText()));
	}
	
	@GET
	@Path("/{id}")
	public Item get(@PathParam("id") long id) {
		return repository.findById(id)
			.orElseThrow(() -> new NotFoundException());
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") long id) {
		Item item = repository.findById(id)
			.orElseThrow(() -> new NotFoundException());
		repository.delete(item);
	}
	
	@PUT
	@Path("/{id}")
	public Item update(@PathParam("id") long id, Item newItem) {
		Item item = repository.findById(id)
			.orElseThrow(() -> new NotFoundException());
		
		item.setText(newItem.getText());
		item.setDone(newItem.isDone());
		return repository.save(item);
	}
	
	@GET
	public List<Item> getMany() {
		Iterable<Item> items = repository.findAll();
		return StreamSupport.stream(items.spliterator(), false)
			.collect(Collectors.toList());
	}
}
