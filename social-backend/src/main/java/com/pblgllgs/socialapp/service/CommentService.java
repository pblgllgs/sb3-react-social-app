package com.pblgllgs.socialapp.service;

import com.pblgllgs.socialapp.models.Comment;
import com.pblgllgs.socialapp.models.dto.CommentRequestDto;

public interface CommentService {

    Comment createComment(CommentRequestDto commentRequestDto, Integer postId, String jwt);
    Comment likeComment(Integer commentId, String jwt);
    Comment findCommentById(Integer commentId);
}
