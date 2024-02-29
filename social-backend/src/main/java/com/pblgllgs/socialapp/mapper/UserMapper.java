package com.pblgllgs.socialapp.mapper;
/*
 *
 * @author pblgl
 * Created on 29-02-2024
 *
 */

import com.pblgllgs.socialapp.models.User;
import com.pblgllgs.socialapp.models.dto.UserDefaultDto;
import com.pblgllgs.socialapp.models.dto.UserUpdateDto;

public class UserMapper {

    public static User userCreateDtoToUser(UserDefaultDto userDto) {
        return User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .gender(userDto.getGender())
                .followers(userDto.getFollowers())
                .followings(userDto.getFollowings())
                .build();
    }

    public static User userUpdateDtoToUser(UserUpdateDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .gender(userDto.getGender())
                .followers(userDto.getFollowers())
                .followings(userDto.getFollowings())
                .build();
    }


    public static UserDefaultDto userToUserDefaultDto(User user){
        return UserDefaultDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .gender(user.getGender())
                .followers(user.getFollowers())
                .followings(user.getFollowings())
                .build();
    }
}
