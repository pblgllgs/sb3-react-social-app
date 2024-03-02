package com.pblgllgs.socialapp.service;

import com.pblgllgs.socialapp.models.Post;
import com.pblgllgs.socialapp.models.dto.PostDefaultDto;

import java.util.List;

public interface PostService {

    Post createPost(PostDefaultDto postDto, String jwt);
    void deletePost(Integer postId,String jwt);
    List<Post> findPostByUserId(Integer userId);
    Post findPostById(Integer postId);
    List<Post> findAllPosts();
    Post savedPost(Integer postId,String jwt);
    Post likePost(Integer postId,String jwt);
}
