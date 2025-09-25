package com.munna.springboot.day5.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.munna.springboot.day5.model.Todo;


@Repository
public class InMemoryTodoRepository {
	private final ConcurrentHashMap<Long, Todo> store = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    public Todo save(Todo todo) {
        if (todo.getId() == null) {
            long id = idGenerator.incrementAndGet();
            todo.setId(id);
        }
        store.put(todo.getId(), todo);
        return todo;
    }

    public Optional<Todo> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Todo> findAll() {
        return new ArrayList<>(store.values());
    }

    public void deleteById(Long id) {
        store.remove(id);
    }

    public void clear() {
        store.clear();
    }
}
