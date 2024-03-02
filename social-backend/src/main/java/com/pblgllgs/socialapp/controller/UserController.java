package com.pblgllgs.socialapp.controller;

import com.pblgllgs.socialapp.models.User;
import com.pblgllgs.socialapp.models.dto.ApiResponse;
import com.pblgllgs.socialapp.models.dto.UserDefaultDto;
import com.pblgllgs.socialapp.models.dto.UserUpdateDto;
import com.pblgllgs.socialapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 *
 * @author pblgl
 * Created on 29-02-2024
 *
 */
@RestController
@RequestMapping("/api/1.0/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Value("${messages.controller.user.deleted-success}")
    private String messageDeletedSuccess;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int id) {
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDefaultDto userDto) {
        return new ResponseEntity<>(userService.registerUser(userDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(
            @RequestBody UserUpdateDto userDto,
            @RequestHeader("Authorization") String  jwt
    ) {
        return new ResponseEntity<>(userService.updateUser(userDto, jwt), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(new ApiResponse(messageDeletedSuccess,true), HttpStatus.OK);
    }


    @GetMapping("/search")
    public ResponseEntity<List<User>> findById(@RequestParam("query") String query) {
        return new ResponseEntity<>(userService.searchUser(query), HttpStatus.OK);
    }

    @PutMapping("/follow/{userId}")
    public ResponseEntity<User> followUserHandler(
            @RequestHeader("Authorization") String  jwt,
            @PathVariable("userId") Integer userId) {
        return new ResponseEntity<>(userService.followerToFollowing(userId,jwt), HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getUserFromToken(@RequestHeader("Authorization") String  jwt){
        return new ResponseEntity<>(userService.getUserFromToken(jwt), HttpStatus.OK);
    }
}
