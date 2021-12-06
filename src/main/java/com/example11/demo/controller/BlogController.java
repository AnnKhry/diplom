package com.example11.demo.controller;



import com.example11.demo.model.enumerations.Post;
import com.example11.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private PostRepository postRepository;

@GetMapping("/blog")
    public String blogMain( Model model) {
 Iterable<Post> posts =postRepository.findAll();//вытягиваем записи из таблицы Post
       model.addAttribute("posts", posts);
        return "blog-main";
    }
    @GetMapping("/blog/add")
    public String blogadd( Model model) {

        return "blogadd";
    }

    @GetMapping("/blog/{id}")
    public String blogdetails(@PathVariable(value ="id") long id, Model model) {
Optional<Post> post =postRepository.findById(id);
ArrayList<Post> res=new ArrayList<>();
post.ifPresent(res::add);
model.addAttribute("post",res);
        return "blog_details";

}
}
