package com.nisa.todoapp;

import org.springframework.stereotype.Service;
import java.util.List;
import com.nisa.todoapp.dto.CreateTodoRequest;
import com.nisa.todoapp.dto.UpdateTodoRequest;
import com.nisa.todoapp.dto.TodoResponse;
import com.nisa.todoapp.exception.TodoNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Service
public class TodoService {

    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public Page<TodoResponse> getAll(Boolean completed, String title, Pageable pageable) {

        Page<Todo> todoPage;

        if (completed != null) {
            todoPage = repository.findByCompleted(completed, pageable);
        } else if (title != null && !title.isBlank()) {
            todoPage = repository.findByTitleContainingIgnoreCase(title, pageable);
        } else {
            todoPage = repository.findAll(pageable);
        }

        return todoPage.map(todo -> new TodoResponse(
                todo.getId(),
                todo.getTitle(),
                todo.isCompleted()
        ));
    }


    public TodoResponse create(CreateTodoRequest request) {

        Todo todo = new Todo();
        todo.setTitle(request.getTitle());
        todo.setCompleted(false);

        Todo saved = repository.save(todo);

        return new TodoResponse(
                saved.getId(),
                saved.getTitle(),
                saved.isCompleted()
        );
    }
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public TodoResponse update(Long id, UpdateTodoRequest request) {

        Todo existing = repository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException("Todo not found"));

        existing.setTitle(request.getTitle());
        existing.setCompleted(request.isCompleted());

        Todo updated = repository.save(existing);

        return new TodoResponse(
                updated.getId(),
                updated.getTitle(),
                updated.isCompleted()
        );
    }

    public TodoResponse getById(Long id) {

        Todo todo = repository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException("Todo not found"));

        return new TodoResponse(
                todo.getId(),
                todo.getTitle(),
                todo.isCompleted()
        );
    }
}


