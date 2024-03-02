package com.pblgllgs.socialapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(
            name = "user_id",
            foreignKey = @ForeignKey(
                    name = "comment_user_id_fk"
            )
    )
    private User user;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tbl_comments_users",
            joinColumns = @JoinColumn(
                    name = "comment_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "comment_id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "user_id")
            )
    )
    private List<User> liked = new ArrayList<>();
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
