package com.example11.demo.controller;



import com.example11.demo.model.enumerations.Post;
import com.example11.demo.model.enumerations.RouteInfo;
import com.example11.demo.model.enumerations.Rule;
import com.example11.demo.model.enumerations.Stock;
import com.example11.demo.repository.PostRepository;
import com.example11.demo.repository.RuleRepository;
import com.example11.demo.repository.StockRepository;
import com.example11.demo.service.PostService;
import com.example11.demo.service.impl.OrderServiceImpl;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private StockRepository stockRepository;
@GetMapping("/blog")
    public String blogMain( Model model) {
 Iterable<Post> posts =postRepository.allPosts();//вытягиваем записи из таблицы Post
       model.addAttribute("posts", posts);

       Iterable<Stock> stock =stockRepository.findAll();
    model.addAttribute("stock", stock);
        return "blog-main";
    }
    @GetMapping("/blog/add")
    public String blogadd( Model model) {

        return "blogadd";
    }

    @GetMapping("/blog/{id}")
    public String blogdetails(@PathVariable(value ="id") long id, Model model) {
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blog_details";


    }

@Autowired
PostService postService;
    @RequestMapping("/editPost/{id}")
    public ModelAndView editPost(@PathVariable(name="id") long id)
    {
        ModelAndView mav= new ModelAndView("admin_news");

       Post post=postService.getPost(id);
        mav.addObject("post", post);
        return mav;
    }


    @RequestMapping(value = "/savepost", method = RequestMethod.POST)
    public String savePost(@ModelAttribute("post") Post post) {
        postService.save(post);

        return "redirect:/blog";
    }


    @RequestMapping("/deletePost/{id}")
    public String deletePost(@PathVariable(name="id") long id, Model model) {

        postService.delete(id);
        return "redirect:/blog";
    }





    @Autowired
        private RuleRepository ruleRepository;

        @GetMapping("/rule")
        public String ruleMain(Model model) {
            Iterable<Rule> rule =ruleRepository.findAll();//вытягиваем записи из таблицы Post
            model.addAttribute("rule", rule);
            return "rule-main";
        }








}
