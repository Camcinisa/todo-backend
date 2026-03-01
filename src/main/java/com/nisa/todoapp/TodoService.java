package com.nisa.todoapp;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TodoService {

    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public List<Todo> getAll() {
        return repository.findAll();
    }

    public Todo save(Todo todo) {
        return repository.save(todo);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Todo update(Long id, Todo updatedTodo) {
        Todo existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        existing.setTitle(updatedTodo.getTitle());
        existing.setCompleted(updatedTodo.isCompleted());

        return repository.save(existing);
    }

    public Todo getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
    }
}


