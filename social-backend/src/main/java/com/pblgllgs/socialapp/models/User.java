package com.pblgllgs.socialapp.models;
/*
 *
 * @author pblgl
 * Created on 29-02-2024
 *
 */

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_users",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UK_email",
                        columnNames = "email"
                )
        })
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(nullable = false)
    private String email;
    private String password;
    private String gender;
    private String photo;
    @JsonBackReference
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tbl_users_posts",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id",foreignKey = @ForeignKey(name = "user_post_id")),
            inverseJoinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id",foreignKey = @ForeignKey(name = "post_user_id"))
    )
    private List<Post> savedPost = new ArrayList<>();
    private List<Integer> followers = new ArrayList<>();
    private List<Integer> followings = new ArrayList<>();
}
