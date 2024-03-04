package com.pblgllgs.socialapp.models;
/*
 *
 * @author pblgl
 * Created on 04-03-2024
 *
 */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_chats")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "chat_name")
    private String chatName;

    @Column(name = "chat_image")
    private String chatImage;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tbl_chats_users",
            joinColumns = @JoinColumn(
                    name = "chat_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "chat_user_id")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "user_chat_id")
            )
    )
    private List<User> users = new ArrayList<>();

    private LocalDateTime timestamp;

    @OneToMany(mappedBy = "chat")
    private List<Message> messages = new ArrayList<>();
}
