package com.pblgllgs.socialapp.service.impl;
/*
 *
 * @author pblgl
 * Created on 29-02-2024
 *
 */

import com.pblgllgs.socialapp.exception.OperationDeniedException;
import com.pblgllgs.socialapp.exception.ResourceNotFoundException;
import com.pblgllgs.socialapp.models.Post;
import com.pblgllgs.socialapp.models.User;
import com.pblgllgs.socialapp.models.dto.PostDefaultDto;
import com.pblgllgs.socialapp.repository.PostRepository;
import com.pblgllgs.socialapp.repository.UserRepository;
import com.pblgllgs.socialapp.service.PostService;
import com.pblgllgs.socialapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    @Value("${messages.service.post.not-found}")
    private String messageUserNotFound;

    @Value("${messages.service.post.operation-denied}")
    private String messageOperationDenied;

    private final PostRepository postRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public Post createPost(PostDefaultDto postDto, Integer userId) {
        User user = userService.findUserById(userId);
        Post newPost = Post.builder()
                .caption(postDto.getCaption())
                .image(postDto.getImage())
                .createdAt(LocalDateTime.now())
                .video(postDto.getVideo())
                .user(user)
                .liked(postDto.getLiked())
                .build();
        log.info("New post: {}", newPost.toString());
        return postRepository.save(newPost);
    }

    @Override
    public void deletePost(Integer postId, Integer userId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException(messageUserNotFound + postId));
        if (!post.getUser().getId().equals(userId)) {
            throw new OperationDeniedException(messageOperationDenied);
        }
        postRepository.deleteById(postId);
    }

    @Override
    public List<Post> findPostByUserId(Integer userId) {
        return postRepository.findByUserId(userId);
    }

    @Override
    public Post findPostById(Integer postId) {
        return postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException(messageUserNotFound + postId));
    }

    @Override
    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post savedPost(Integer postId, Integer userId) {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if (user.getSavedPost().contains(post)) {
            user.getSavedPost().remove(post);
        } else {
            user.getSavedPost().add(post);
        }
        userRepository.save(user);
        return post;

    }

    @Override
    public Post likePost(Integer postId, Integer userId) {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if (post.getLiked().contains(user)) {
            post.getLiked().remove(user);
        } else {
            post.getLiked().add(user);
        }
        return postRepository.save(post);
    }
}
