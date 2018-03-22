package com.gmail.lnqhien.todo;

import org.springframework.data.repository.CrudRepository;

import com.gmail.lnqhien.todo.entity.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {

}
