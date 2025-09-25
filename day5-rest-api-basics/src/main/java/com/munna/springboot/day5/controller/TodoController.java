package com.munna.springboot.day5.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.munna.springboot.day5.dto.CreateTodoRequest;
import com.munna.springboot.day5.dto.UpdateTodoRequest;
import com.munna.springboot.day5.model.Todo;
import com.munna.springboot.day5.service.TodoService;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
	private final TodoService service;
    public TodoController(TodoService service) {
    	this.service = service; 
    	}

    // List with optional filter ?completed=true
    @GetMapping
    public List<Todo> list(@RequestParam(required = false) Boolean completed) {
        return service.list(completed);
    }

    // Get by id
    @GetMapping("/{id}")
    public Todo getById(@PathVariable Long id) {
        return service.get(id);
    }

    // Create
    @PostMapping
    public ResponseEntity<Todo> create(@Valid @RequestBody CreateTodoRequest req) {
        Todo created = service.create(req);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    // Update (partial allowed)
    @PutMapping("/{id}")
    public Todo update(@PathVariable Long id, @RequestBody UpdateTodoRequest req) {
        return service.update(id, req);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
