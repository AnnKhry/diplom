package com.example11.demo.service;

import com.example11.demo.model.enumerations.Post;
import com.example11.demo.model.enumerations.RouteInfo;
import com.example11.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostService {

@Autowired
    PostRepository postRepository;
    public void save(Post post)
    {
        postRepository.save(post);
    }
    public Post getPost(long id)
    {
        return postRepository.findById(id).get();
    }
    public void delete(long id)
    {
        postRepository.deleteById(id);
    }
}
