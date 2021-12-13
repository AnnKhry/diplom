package com.example11.demo.repository;


import com.example11.demo.model.enumerations.Order;
import com.example11.demo.model.enumerations.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post,Long> {

    @Query("from Post p order by p.id desc")
    public List<Post> allPosts();
}
