package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Todo;
import com.example.demo.repository.TodoRepository;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo updateTodoById(Long id, Todo updatedTodo) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if (optionalTodo.isPresent()) {
            Todo todo = optionalTodo.get();
            todo.setTitle(updatedTodo.getTitle());
            todo.setDescription(updatedTodo.getDescription()); // Update description
            todo.setCompleted(updatedTodo.isCompleted());
            return todoRepository.save(todo);
        }
        return null; // Handle not found case
    }

    public void deleteTodoById(Long id) {
        todoRepository.deleteById(id);
    }
}
