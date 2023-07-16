package com.geekster.InstaBackend.controllers;

import com.geekster.InstaBackend.models.Post;
import com.geekster.InstaBackend.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/posting")
    public String addPost(@RequestParam String email, @RequestBody Post post){
        return postService.addPost(email,post);
    }
    @GetMapping("/checkPosts")
    public List<Post> getAllPosts(@RequestParam String email){
        return postService.getAllPosts(email);
    }
    @PostMapping("/update/{id}")
    public String updatePostById(@PathVariable Integer id, @RequestParam String email, @RequestBody Post post){
        return postService.updatePostById(id,email,post);
    }
    @DeleteMapping("/delete/{id}")
    public String deletePostById(@PathVariable Integer id, @RequestParam String email){
        return postService.deletePostById(id,email);
    }
}
