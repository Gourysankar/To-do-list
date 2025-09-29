package com.iplus.todolist.repository;

import com.iplus.todolist.entity.Todo;
import com.iplus.todolist.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUser(User user);
}