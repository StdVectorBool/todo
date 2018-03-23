package com.gmail.lnqhien.todo.api.resource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gmail.lnqhien.todo.ItemRepository;
import com.gmail.lnqhien.todo.entity.Item;

@Component
@Path("/todo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class Todo {
	
	@Autowired
	private ItemRepository repository;
	
	/**
	 * Add a new To-Do item
	 */
	@POST
	public Item add(Item item) {
		return repository.save(new Item(item.isDone(), item.getText()));
	}
	
	/**
	 * Get a single To-Do item
	 */
	@GET
	@Path("/{id}")
	public Item get(@PathParam("id") long id) {
		return repository.findById(id)
			.orElseThrow(() -> new NotFoundException());
	}
	
	/**
	 * Delete a single To-Do item
	 */
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") long id) {
		Item item = repository.findById(id)
			.orElseThrow(() -> new NotFoundException());
		repository.delete(item);
	}
	
	/**
	 * Update a single To-Do item
	 */
	@PUT
	@Path("/{id}")
	public Item update(@PathParam("id") long id, Item newItem) {
		Item item = repository.findById(id)
			.orElseThrow(() -> new NotFoundException());
		
		item.setText(newItem.getText());
		item.setDone(newItem.isDone());
		return repository.save(item);
	}
	
	/**
	 * Get all To-Do items, supports optional URL query parameters.
	 * @param page starting page
	 * @param size items per page
	 * @param done only items with this done-ness
	 */
	@GET
	public List<Item> getMany(
		@QueryParam("page") @DefaultValue("0") int page,
		@QueryParam("size") @DefaultValue("5") int size,
		@QueryParam("done") Boolean done
	) {
		size = Math.min(size, 10); // restrict max in case lots of data
		Pageable p = PageRequest.of(page, size);
		
		Iterable<Item> items = (done == null)
			? repository.findAll(p)
			: repository.findByDone(done, p);
		
		return StreamSupport.stream(items.spliterator(), false)
			.collect(Collectors.toList());
	}
	
	/**
	 * @return total items, useful for setting page/size.
	 * TODO support done query
	 */
	@GET
	@Path("/count")
	public long getCount() {
		return repository.count();
	}
}
