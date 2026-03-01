package com.nisa.todoapp;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Todo> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Todo create(@Valid @RequestBody Todo todo) {
        return service.save(todo);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }


    @PutMapping("/{id}")
    public Todo update(@PathVariable Long id, @RequestBody Todo todo) {
        return service.update(id, todo);
    }

    @GetMapping("/{id}")
    public Todo getById(@PathVariable Long id) {
        return service.getById(id);
    }
}