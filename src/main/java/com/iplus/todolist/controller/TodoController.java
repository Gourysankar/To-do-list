package com.iplus.todolist.controller;

import com.iplus.todolist.entity.Todo;
import com.iplus.todolist.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@Controller
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public String listTodos(Model model, Principal principal) {
        model.addAttribute("todos", todoService.getTodosByUsername(principal.getName()));
        model.addAttribute("newTodo", new Todo());
        return "todos";
    }

    @PostMapping
    public String addTodo(@ModelAttribute("newTodo") Todo todo, Principal principal) {
        todoService.addTodo(principal.getName(), todo);
        return "redirect:/todos";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id, Principal principal) {
        todoService.deleteTodo(principal.getName(), id);
        return "redirect:/todos";
    }

    @GetMapping("/toggle/{id}")
    public String toggleTodo(@PathVariable Long id, Principal principal) {
        todoService.toggleTodo(principal.getName(), id);
        return "redirect:/todos";
    }
}