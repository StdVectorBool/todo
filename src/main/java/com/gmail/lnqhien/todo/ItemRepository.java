package com.gmail.lnqhien.todo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gmail.lnqhien.todo.entity.Item;

public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {
	
	/**
	 * Find Items by un/done status.
	 * @param done
	 * @param pageable
	 * @return a page of items
	 */
	Page<Item> findByDone(boolean done, Pageable pageable);
	
}
