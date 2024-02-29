package com.pblgllgs.socialapp.models;
/*
 *
 * @author pblgl
 * Created on 29-02-2024
 *
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String caption;
    private String image;
    private String video;
    @ManyToOne
    private User user;
    @OneToMany
    private List<User> liked = new ArrayList<>();
    private LocalDateTime createdAt;
}
