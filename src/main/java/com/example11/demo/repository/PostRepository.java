package com.example11.demo.repository;


import com.example11.demo.model.enumerations.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post,Long> {
}
