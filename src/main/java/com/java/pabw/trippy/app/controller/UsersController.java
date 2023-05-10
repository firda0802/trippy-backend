package com.java.pabw.trippy.app.controller;
import org.springframework.web.bind.annotation.*;
import com.java.pabw.trippy.app.Repository.UserRepository;
import com.java.pabw.trippy.app.models.Users;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Get all users (admin only)
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    // Get user by id (admin only)
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Users getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    // Add new user (admin only)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Users addUser(@RequestBody Users userObj) {
        // Encode password
        userObj.setPassword(passwordEncoder.encode(userObj.getPassword()));

        return userRepository.save(userObj);
    }

    // Delete user (admin only)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable Long id) {
        Users userObj = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        userRepository.delete(userObj);
    }
}
