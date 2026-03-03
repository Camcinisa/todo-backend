package com.nisa.todoapp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    Page<Todo> findByCompleted(Boolean completed, Pageable pageable);

    Page<Todo> findByTitleContainingIgnoreCase(String title, Pageable pageable);

}