package com.example11.demo.service;


import com.example11.demo.model.enumerations.Role;
import com.example11.demo.model.enumerations.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword,
                  String name, String lastname, String email,String phone, Role role);
}
