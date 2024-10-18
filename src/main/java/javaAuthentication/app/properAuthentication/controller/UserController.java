package javaAuthentication.app.properAuthentication.controller;

import javaAuthentication.app.properAuthentication.model.User;
import javaAuthentication.app.properAuthentication.services.UserService;
import javaAuthentication.app.properAuthentication.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/ok")
    public String healthCheck() {
        return "Health is ok..";
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            // Check if user already exists
            User existingUser = userService.findByEmail(user.getEmail());
            if (existingUser != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists. Please login.");
            }

            // Encrypt the user's password
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setUserId(System.currentTimeMillis()); // Set userId to current time

            userService.createUser(user);

            // Generate JWT Token after successful registration
//            String token = jwtUtil.generateToken(user.getEmail());

            String token = jwtUtil.generateToken(user.getUserId().toString());

            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully. Token: " + token);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + exception.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User loginRequest) {
        try {
            User user = userService.findByEmail(loginRequest.getEmail());

            // Check if user exists
            if (user == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found. Please check your email or password.");
            }

            // Validate password
            boolean isPasswordMatch = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
            if (!isPasswordMatch) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect password. Please check your email or password.");
            }

            // Generate JWT token
//            String token = jwtUtil.generateToken(user.getEmail());
            String token = jwtUtil.generateToken(user.getUserId().toString());

            return ResponseEntity.status(HttpStatus.OK).body("Login successful. Token: " + token);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + exception.getMessage());
        }
    }
}
