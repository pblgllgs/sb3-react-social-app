package com.pblgllgs.socialapp.controller;
/*
 *
 * @author pblgl
 * Created on 02-03-2024
 *
 */

import com.pblgllgs.socialapp.models.Comment;
import com.pblgllgs.socialapp.models.dto.CommentRequestDto;
import com.pblgllgs.socialapp.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/1.0/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/post/{postId}")
    public ResponseEntity<Comment> saveComment(
            @RequestBody CommentRequestDto commentRequestDto,
            @PathVariable("postId") Integer postId,
            @RequestHeader("Authorization") String jwt
    ) {
        return new ResponseEntity<>(commentService.createComment(commentRequestDto, postId, jwt), HttpStatus.CREATED);
    }

    @PutMapping("/like/post/{postId}")
    public ResponseEntity<Comment> likeComment(
            @PathVariable("postId") Integer postId,
            @RequestHeader("Authorization") String jwt
    ) {
        return new ResponseEntity<>(commentService.likeComment(postId, jwt), HttpStatus.OK);
    }
}
