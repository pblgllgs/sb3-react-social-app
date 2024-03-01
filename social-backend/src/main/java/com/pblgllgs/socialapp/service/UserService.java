package com.pblgllgs.socialapp.service;

import com.pblgllgs.socialapp.models.User;
import com.pblgllgs.socialapp.models.dto.AuthResponseDto;
import com.pblgllgs.socialapp.models.dto.UserDefaultDto;
import com.pblgllgs.socialapp.models.dto.UserUpdateDto;

import java.util.List;

public interface UserService {

    User registerUser(UserDefaultDto userDto);

    List<User> getAllUsers();

    void deleteUser(Integer userId);

    User findUserById(Integer userId);

    User findUserByEmail(String email);

    User followerToFollowing(Integer userIdFollow, Integer userIdFollowing);

    User updateUser(UserUpdateDto user, Integer userId);

    List<User> searchUser(String query);
}
