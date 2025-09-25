package com.munna.springboot.day5.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.munna.springboot.day5.dto.CreateTodoRequest;
import com.munna.springboot.day5.dto.UpdateTodoRequest;
import com.munna.springboot.day5.exception.NotFoundException;
import com.munna.springboot.day5.model.Todo;
import com.munna.springboot.day5.repository.InMemoryTodoRepository;

@Service
public class TodoService {
	private final InMemoryTodoRepository repo;

    public TodoService(InMemoryTodoRepository repo) {
        this.repo = repo;
    }

    public Todo create(CreateTodoRequest req) {
        Todo t = new Todo(null, req.getTitle(), req.getDescription(), false);
        return repo.save(t);
    }

    public List<Todo> list(Boolean completedFilter) {
        List<Todo> all = repo.findAll();
        if (completedFilter == null) return all;
        return all.stream()
                .filter(t -> t.isCompleted() == completedFilter)
                .collect(Collectors.toList());
    }

    public Todo get(Long id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Todo not found with id " + id));
    }

    public Todo update(Long id, UpdateTodoRequest req) {
        Todo existing = get(id);
        if (req.getTitle() != null) existing.setTitle(req.getTitle());
        if (req.getDescription() != null) existing.setDescription(req.getDescription());
        if (req.getCompleted() != null) existing.setCompleted(req.getCompleted());
        repo.save(existing);
        return existing;
    }

    public void delete(Long id) {
        if (!repo.findById(id).isPresent()) {
            throw new NotFoundException("Todo not found with id " + id);
        }
        repo.deleteById(id);
    }

}
