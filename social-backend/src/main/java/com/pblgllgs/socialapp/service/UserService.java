package com.pblgllgs.socialapp.service;

import com.pblgllgs.socialapp.models.User;
import com.pblgllgs.socialapp.models.dto.UserDefaultDto;
import com.pblgllgs.socialapp.models.dto.UserUpdateDto;

import java.util.List;

public interface UserService {
    User registerUser(UserDefaultDto userDto);

    List<User> getAllUsers();

    void deleteUser(Integer userId);

    User findUserById(Integer userId);

    User findUserByEmail(String email);

    User followerToFollowing(Integer userIdFollowing, String jwt);

    User updateUser(UserUpdateDto user, String jwt);

    List<User> searchUser(String query);

    User getUserFromToken(String jwt);
}
