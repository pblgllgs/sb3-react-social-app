package com.pblgllgs.socialapp.service.impl;
/*
 *
 * @author pblgl
 * Created on 02-03-2024
 *
 */

import com.pblgllgs.socialapp.exception.ResourceNotFoundException;
import com.pblgllgs.socialapp.models.Comment;
import com.pblgllgs.socialapp.models.Post;
import com.pblgllgs.socialapp.models.User;
import com.pblgllgs.socialapp.repository.CommentRepository;
import com.pblgllgs.socialapp.repository.PostRepository;
import com.pblgllgs.socialapp.service.CommentService;
import com.pblgllgs.socialapp.service.PostService;
import com.pblgllgs.socialapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Value("${messages.service.comment.not-found}")
    private String messageCommentNotFound;

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;
    private final PostRepository postRepository;

    @Override
    public Comment createComment(Comment comment, Integer postId, String jwt) {
        User user = userService.getUserFromToken(jwt);
        Post post = postService.findPostById(postId);
        Comment newComment = Comment.builder()
                .content(comment.getContent())
                .createdAt(LocalDateTime.now())
                .user(user)
                .build();
        Comment savedNewComment = commentRepository.save(newComment);
        post.getComments().add(savedNewComment);
        postRepository.save(post);
        return savedNewComment;
    }

    @Override
    public Comment likeComment(Integer commentId, String jwt) {
        Comment comment = findCommentById(commentId);
        User user = userService.getUserFromToken(jwt);
        if (!comment.getLiked().contains(user)){
            comment.getLiked().add(user);
        }else{
            comment.getLiked().remove(user);
        }
        return commentRepository.save(comment);
    }

    @Override
    public Comment findCommentById(Integer commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException(messageCommentNotFound + commentId));
    }
}
