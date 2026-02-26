package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/demo")
    public String demo() {
        return "iam good how are you";
    }

    @GetMapping("/users")
    public List<Users> getAllUsers() {
        return this.userRepository.findAll();
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Users user) {
        Users u = this.userRepository.findByEmail(user.getEmail());
        if (u != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }
        this.userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }
}
