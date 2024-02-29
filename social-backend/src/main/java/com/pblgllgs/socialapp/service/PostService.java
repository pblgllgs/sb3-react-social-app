package com.pblgllgs.socialapp.service;

import com.pblgllgs.socialapp.models.Post;
import com.pblgllgs.socialapp.models.dto.PostDefaultDto;

import java.util.List;

public interface PostService {

    Post createPost(PostDefaultDto postDto, Integer userId);
    void deletePost(Integer postId,Integer userId);
    List<Post> findPostByUserId(Integer userId);
    Post findPostById(Integer postId);
    List<Post> findAllPosts();
    Post savedPost(Integer postId,Integer userId);
    Post likePost(Integer postId,Integer userId);
}
