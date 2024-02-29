package com.pblgllgs.socialapp.models.dto;
/*
 *
 * @author pblgl
 * Created on 29-02-2024
 *
 */

import com.pblgllgs.socialapp.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostUpdateDto {

    private Integer id;
    private String caption;
    private String image;
    private String video;
    private User user;
    private LocalDateTime createdAt;
}
