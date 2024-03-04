package com.pblgllgs.socialapp.models;
/*
 *
 * @author pblgl
 * Created on 04-03-2024
 *
 */

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;
    private String image;

    @ManyToOne
    private User user;

    @ManyToOne
    @JsonBackReference
    private Chat chat;

    private LocalDateTime timestamp;

}
