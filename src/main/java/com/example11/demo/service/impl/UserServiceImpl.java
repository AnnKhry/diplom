package com.example11.demo.service.impl;

import com.example11.demo.model.enumerations.Role;
import com.example11.demo.model.enumerations.User;
import com.example11.demo.model.exceptions.*;
import com.example11.demo.repository.UserRepository;
import com.example11.demo.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException(s));
    }


    @Override
    public User register(String username, String password, String repeatPassword, String name, String lastname, String email, Role role) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        if(!email.contains("@"))
            throw new InvalidEmailException("Your email need to be like example@email.com");
        if(this.userRepository.findByEmail(email).isPresent())
            throw new EmailAlreadyExistsException(email);
        User user = new User(username,passwordEncoder.encode(password),name,lastname,email,role);
        return userRepository.save(user);

    }
}
