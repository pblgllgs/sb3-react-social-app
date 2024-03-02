package com.pblgllgs.socialapp.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
 *
 * @author pblgl
 * Created on 02-03-2024
 *
 */
@Entity
@Table(name = "tbl_comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = User.class)
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tbl_comments_users",
            joinColumns = @JoinColumn(name = "comment_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id")
    )
    private List<User> liked = new ArrayList<>();

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
