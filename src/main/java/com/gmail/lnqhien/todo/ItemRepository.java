package com.gmail.lnqhien.todo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.gmail.lnqhien.todo.entity.Item;

public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {

}
