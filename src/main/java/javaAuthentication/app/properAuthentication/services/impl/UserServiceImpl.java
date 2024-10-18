package javaAuthentication.app.properAuthentication.services.impl;

import javaAuthentication.app.properAuthentication.model.User;
import javaAuthentication.app.properAuthentication.repository.UserRepository;
import javaAuthentication.app.properAuthentication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        try {
           return userRepository.save(user); // Save the user to the database
        } catch (Exception e) {
            throw new RuntimeException("User already exists with this email."); // Handle unique constraint violation
        }
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);  // Find user by email
    }
}
