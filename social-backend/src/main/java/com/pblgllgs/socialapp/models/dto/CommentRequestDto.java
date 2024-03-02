package com.pblgllgs.socialapp.models.dto;
/*
 *
 * @author pblgl
 * Created on 02-03-2024
 *
 */

import com.pblgllgs.socialapp.models.User;

import java.time.LocalDateTime;
import java.util.List;

public record CommentRequestDto(
        String content,
        User user,
        List<User> liked,
        LocalDateTime createdAt
) {
}
