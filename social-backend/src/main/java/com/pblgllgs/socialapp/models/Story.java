package com.pblgllgs.socialapp.models;
/*
 *
 * @author pblgl
 * Created on 02-03-2024
 *
 */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_stories")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(
            name = "user_id",
            foreignKey = @ForeignKey(
                    name = "story_user_id_fk"
            )
    )
    private User user;
    private String image;
    private String captions;
    private LocalDateTime timestamp;
}
