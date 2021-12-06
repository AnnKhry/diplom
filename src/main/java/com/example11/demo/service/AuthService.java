package com.example11.demo.service;



import com.example11.demo.model.enumerations.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
//
public interface AuthService {

    User login(String username, String password);

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);


    void delete(String username);

    void update(String username, String usernameOfUser, String nameOfUser,
                String lastnameOfUser, String emailOfUser);

}
