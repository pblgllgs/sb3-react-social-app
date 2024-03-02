package com.pblgllgs.socialapp.service;

import com.pblgllgs.socialapp.models.Comment;

public interface CommentService {

    Comment createComment(Comment comment, Integer postId, String jwt);
    Comment likeComment(Integer commentId, String jwt);
    Comment findCommentById(Integer commentId);
}
