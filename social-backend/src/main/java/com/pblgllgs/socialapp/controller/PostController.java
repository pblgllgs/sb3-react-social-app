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

    @Value("${messages.controller.post.deleted-success}")
    private String messageDeletedSuccess;
    private final PostService postService;

    @PostMapping
    public ResponseEntity<Post> createPost(
            @RequestBody PostDefaultDto postDefaultDto,
            @RequestHeader("Authorization") String jwt
    ) {
        return new ResponseEntity<>(postService.createPost(postDefaultDto, jwt), HttpStatus.CREATED);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse> deletePost(
            @PathVariable("postId") Integer postId,
            @RequestHeader("Authorization") String jwt
    ) {
        postService.deletePost(postId, jwt);
        return new ResponseEntity<>(new ApiResponse(messageDeletedSuccess, true), HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> findPostById(@PathVariable("postId") Integer postId) {
        return new ResponseEntity<>(postService.findPostById(postId), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/all")
    public ResponseEntity<List<Post>> findById(@PathVariable("userId") Integer userId) {
        return new ResponseEntity<>(postService.findPostByUserId(userId), HttpStatus.OK);
    }

    @PutMapping("/liked/{postId}")
    public ResponseEntity<Post> likedPostHandler(
            @PathVariable("postId") Integer postId,
            @RequestHeader("Authorization") String jwt
    ) {
        return new ResponseEntity<>(postService.likePost(postId, jwt), HttpStatus.OK);
    }

    @PutMapping("/saved/{postId}")
    public ResponseEntity<Post> savedPostHandler(
            @PathVariable("postId") Integer postId,
            @RequestHeader("Authorization") String jwt
    ) {
        return new ResponseEntity<>(postService.savedPost(postId, jwt), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Post>> findAll() {
        return new ResponseEntity<>(postService.findAllPosts(), HttpStatus.OK);
    }
}
