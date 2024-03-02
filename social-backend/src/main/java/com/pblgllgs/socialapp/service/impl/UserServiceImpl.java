package com.pblgllgs.socialapp.service.impl;
/*
 *
 * @author pblgl
 * Created on 29-02-2024
 *
 */

import com.pblgllgs.socialapp.exception.ResourceNotFoundException;
import com.pblgllgs.socialapp.exception.UserAlreadyExistsException;
import com.pblgllgs.socialapp.models.User;
import com.pblgllgs.socialapp.models.dto.UserDefaultDto;
import com.pblgllgs.socialapp.models.dto.UserUpdateDto;
import com.pblgllgs.socialapp.repository.UserRepository;
import com.pblgllgs.socialapp.security.JwtService;
import com.pblgllgs.socialapp.service.CustomerUserDetailsService;
import com.pblgllgs.socialapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Value("${messages.service.user.already-exists}")
    private String messageEmailAlreadyExists;
    @Value("${messages.service.user.not-found}")
    private String messageUserNotFound;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final CustomerUserDetailsService userDetailsService;

    @Override
    public User registerUser(UserDefaultDto userDto) {
        User userFound = userRepository.findByEmail(userDto.getEmail());
        if (userFound != null) {
            throw new UserAlreadyExistsException(messageEmailAlreadyExists + userDto.getEmail());
        }
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(UserUpdateDto userDto, String jwt) {
        User user = getUserFromToken(jwt);
        User found = userRepository.findById(user.getId())
                .orElseThrow(
                        () -> new ResourceNotFoundException(messageUserNotFound + user.getId()
                        )
                );
        found.setFirstName(userDto.getFirstName());
        found.setLastName(userDto.getLastName());
        found.setEmail(userDto.getEmail());
        found.setPassword(userDto.getPassword());
        return userRepository.save(found);
    }

    @Override
    public void deleteUser(Integer userId) {
        User found = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(messageUserNotFound + userId));
        userRepository.deleteById(found.getId());
    }

    @Override
    public User findUserById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(messageUserNotFound + userId));
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException(messageEmailAlreadyExists + email);
        }
        return user;
    }

    @Override
    public User followerToFollowing(Integer userIdFollowing, String jwt) {
        User reqUser = getUserFromToken(jwt);
        User following = findUserById(userIdFollowing);

        following.getFollowers().add(reqUser.getId());
        reqUser.getFollowings().add(following.getId());

        userRepository.save(reqUser);
        userRepository.save(following);
        return reqUser;
    }

    @Override
    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);
    }

    @Override
    public User getUserFromToken(String jwt) {
        String emailFromToken = jwtService.getEmailFromToken(jwt.substring(7));
        return userRepository.findByEmail(emailFromToken);
    }
}
