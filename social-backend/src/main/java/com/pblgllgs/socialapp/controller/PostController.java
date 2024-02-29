package com.pblgllgs.socialapp.controller;
/*
 *
 * @author pblgl
 * Created on 29-02-2024
 *
 */

import com.pblgllgs.socialapp.models.Post;
import com.pblgllgs.socialapp.models.dto.ApiResponse;
import com.pblgllgs.socialapp.models.dto.PostDefaultDto;
import com.pblgllgs.socialapp.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/1.0/posts")
@RequiredArgsConstructor
public class PostController {

    @Value("${messages.controller.post.deleted.success}")
    private String messageDeletedSuccess;
    private final PostService postService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<Post> createPost(
            @RequestBody PostDefaultDto postDefaultDto,
            @PathVariable("userId") Integer userId
    ) {
        return new ResponseEntity<>(postService.createPost(postDefaultDto, userId), HttpStatus.CREATED);
    }

    @DeleteMapping("/{postId}/user/{userId}")
    public ResponseEntity<ApiResponse> delete(
            @PathVariable("postId") Integer postId,
            @PathVariable("userId") Integer userId
    ) {
        postService.deletePost(postId, userId);
        return new ResponseEntity<>(new ApiResponse(messageDeletedSuccess, true), HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> findPostById(@PathVariable("postId") Integer postId) {
        return new ResponseEntity<>(postService.findPostById(postId), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/all")
    public ResponseEntity<List<Post>> findById(@PathVariable("userId") Integer userId) {
        return new ResponseEntity<>(postService.findPostByUserId(userId),HttpStatus.OK);
    }

    @PutMapping("/liked/{postId}/user/{userId}")
    public ResponseEntity<Post> likedPostHandler(
            @PathVariable("postId") Integer postId,
            @PathVariable("userId") Integer userId
    ) {
        return new ResponseEntity<>(postService.likePost(postId,userId), HttpStatus.OK);
    }

    @PutMapping("/saved/{postId}/user/{userId}")
    public ResponseEntity<Post> savedPostHandler(
            @PathVariable("postId") Integer postId,
            @PathVariable("userId") Integer userId
    ) {
        return new ResponseEntity<>(postService.savedPost(postId,userId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Post>> findAll(){
        return new ResponseEntity<>(postService.findAllPosts(),HttpStatus.OK);
    }
}
