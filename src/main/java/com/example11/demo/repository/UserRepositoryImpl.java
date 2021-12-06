package com.example11.demo.repository;

import com.example11.demo.model.enumerations.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserRepositoryImpl {
    private UserRepository userRepository;

    public Optional<User> loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
