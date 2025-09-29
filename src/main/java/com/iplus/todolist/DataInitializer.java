package com.iplus.todolist;

import com.iplus.todolist.entity.User;
import com.iplus.todolist.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Check if a default user already exists
        if (userRepository.findByUsername("user").isEmpty()) {
            User user = new User();
            user.setUsername("user");
            // IMPORTANT: Always encode passwords before saving!
            user.setPassword(passwordEncoder.encode("password"));
            userRepository.save(user);
            System.out.println(">>> Created default user 'user' with password 'password'");
        }
    }
}