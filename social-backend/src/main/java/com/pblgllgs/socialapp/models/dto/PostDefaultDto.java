package com.pblgllgs.socialapp.models.dto;
/*
 *
 * @author pblgl
 * Created on 29-02-2024
 *
 */

import com.pblgllgs.socialapp.models.Comment;
import com.pblgllgs.socialapp.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDefaultDto {

    private String caption;
    private String image;
    private String video;
    private User user;
    private List<User> liked = new ArrayList<>();
    private LocalDateTime createdAt;
    private List<Comment> comments = new ArrayList<>();
}
