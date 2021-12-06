package com.example11.demo.repository;


import com.example11.demo.model.enumerations.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Object> {
    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
//   String  findByEEmail(String email);


    @Modifying
    @Query("update User u set u.username = ?1, u.email = ?2, u.lastname = ?3, u.name = ?4 where u.username = ?5")
    void setUserInfoById(String username, String email, String lastname, String name);



    @Query("from User WHERE email=:email and lastname=:lastname and name=:name")
    List<User> findUsers(@Param("email") String email, @Param("lastname") String lastname,
                               @Param("name") String name);

    Optional<User> findById(Long userId);

//    Optional<User> findByEmail(String userEmail);
}
