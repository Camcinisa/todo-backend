package com.nisa.todoapp;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title boş olamaz")
    private String title;

    private boolean completed;

    public Todo() {
    }

    public Todo(String title, boolean completed) {
        this.title = title;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}