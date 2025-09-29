package com.iplus.todolist.service;

import com.iplus.todolist.entity.Todo;
import com.iplus.todolist.entity.User;
import com.iplus.todolist.repository.TodoRepository;
import com.iplus.todolist.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    public List<Todo> getTodosByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        return todoRepository.findByUser(user);
    }

    public void addTodo(String username, Todo todo) {
        User user = userRepository.findByUsername(username).orElseThrow();
        todo.setUser(user);
        todoRepository.save(todo);
    }

    public void deleteTodo(String username, Long id) {
        User user = userRepository.findByUsername(username).orElseThrow();
        todoRepository.findById(id).ifPresent(todo -> {
            if (todo.getUser().equals(user)) {
                todoRepository.deleteById(id);
            }
        });
    }

    public void toggleTodo(String username, Long id) {
        User user = userRepository.findByUsername(username).orElseThrow();
        todoRepository.findById(id).ifPresent(todo -> {
            if (todo.getUser().equals(user)) {
                todo.setCompleted(!todo.isCompleted());
                todoRepository.save(todo);
            }
        });
    }
}