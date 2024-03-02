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

@Entity
@Table(name = "tbl_reels")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String video;
    @ManyToOne
    @JoinColumn(
            name = "user_id",
            foreignKey = @ForeignKey(
                    name = "reel_user_id_fk"
            )
    )
    private User user;

}
