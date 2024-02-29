package com.pblgllgs.socialapp.models.dto;
/*
 *
 * @author pblgl
 * Created on 29-02-2024
 *
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;
    private List<Integer> followers = new ArrayList<>();
    private List<Integer> followings = new ArrayList<>();
}
