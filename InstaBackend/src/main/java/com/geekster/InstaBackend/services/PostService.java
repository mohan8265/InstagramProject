package com.geekster.InstaBackend.services;

import com.geekster.InstaBackend.models.AuthenticationToken;
import com.geekster.InstaBackend.models.Post;
import com.geekster.InstaBackend.models.User;
import com.geekster.InstaBackend.repositories.IPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private IPostRepo postRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthTokenService authTokenService;

    public String addPost(String email,Post post) {
        User existingUser = userService.getUserByEmail(email);
        if(existingUser == null){
            return "sign up first";
        }
        AuthenticationToken existingToken = authTokenService.getTokenByUser(existingUser);
        if(existingToken == null){
            return "sign in first";
        }

        post.setUser(existingUser);
        postRepo.save(post);
        return "posted";
    }

    public List<Post> getAllPosts(String email) {
        User existingUser = userService.getUserByEmail(email);
        if(existingUser == null){
            throw new IllegalStateException("sign up first");
        }
        AuthenticationToken existingToken = authTokenService.getTokenByUser(existingUser);
        if(existingToken == null){
            throw new IllegalStateException("sign in first");
        }

        return postRepo.findAll();
    }

    public String updatePostById(Integer id, String email,Post post) {
        User existingUser = userService.getUserByEmail(email);
        if(existingUser == null){
            return "sign up first";
        }
        AuthenticationToken existingToken = authTokenService.getTokenByUser(existingUser);
        if(existingToken == null){
            return "sign in first";
        }

        post.setPostId(id);
        post.setUser(existingUser);
        postRepo.save(post);
        return "post updated";
    }

    public String deletePostById(Integer id, String email) {
        User existingUser = userService.getUserByEmail(email);
        if(existingUser == null){
            return "sign up first";
        }
        AuthenticationToken existingToken = authTokenService.getTokenByUser(existingUser);
        if(existingToken == null){
            return "sign in first";
        }

        Post existingPost = postRepo.findById(id).get();
        if(existingPost == null){
            return "invalid post id";
        }

        postRepo.deleteById(id);
        return "deleted successfully";
    }
}
