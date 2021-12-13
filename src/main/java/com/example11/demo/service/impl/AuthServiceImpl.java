package com.example11.demo.service.impl;


import com.example11.demo.model.enumerations.User;
import com.example11.demo.model.exceptions.EmailAlreadyExistsException;
import com.example11.demo.model.exceptions.InvalidArgumentsException;
import com.example11.demo.model.exceptions.InvalidUserCredentialsException;
import com.example11.demo.model.exceptions.UsernameAlreadyExistsException;
import com.example11.demo.repository.UserRepository;
import com.example11.demo.service.AuthService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public void delete(String username) {
        User user =  this.findByUsername(username).orElseThrow();

        this.userRepository.delete(user);
    }

    @Override
    public void update(String username, String usernameOfUser, String nameOfUser, String lastnameOfUser, String emailOfUser,String phoneOfUser) {
        User user =  this.findByUsername(username).orElseThrow();


        user.setUsername(usernameOfUser);
        user.setName(nameOfUser);
        user.setLastname(lastnameOfUser);
        user.setEmail(emailOfUser);
        user.setPhone(phoneOfUser);

         this.userRepository.save(user);

    }

    @Override
    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }
//    @Override
//    public User findByUsernamem(String username) {
//        return this.userRepository.findByUsernamem(username);
//    }

    @Override
    public Optional<User> findById(long id) {
        return this.userRepository.findById(id);
    }


    @Override
    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
}
