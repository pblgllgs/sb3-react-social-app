package com.pblgllgs.socialapp.controller;

import com.pblgllgs.socialapp.models.Chat;
import com.pblgllgs.socialapp.models.dto.CreateChatRequestDto;
import com.pblgllgs.socialapp.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 *
 * @author pblgl
 * Created on 04-03-2024
 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/1.0/chats")
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ResponseEntity<Chat> createChat(
            @RequestHeader("Authorization") String jwt,
            @RequestBody CreateChatRequestDto createchatRequestDto) {
        return new ResponseEntity<>(
                chatService.createChat(
                        jwt,
                        createchatRequestDto.userId()),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Chat>> findUsersChat(@RequestHeader("Authorization") String jwt) {
        return new ResponseEntity<>(chatService.findUsersChat(jwt), HttpStatus.OK);
    }
}
