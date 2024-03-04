package com.pblgllgs.socialapp.controller;

import com.pblgllgs.socialapp.models.Message;
import com.pblgllgs.socialapp.service.MessageService;
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
@RequestMapping("/api/1.0/messages")
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/chat/{chatId}")
    public ResponseEntity<Message> createMessage(
            @RequestHeader("Authorization") String jwt,
            @PathVariable("chatId") Integer chatId,
            @RequestBody Message message
    ) {
        return new ResponseEntity<>(messageService.createMessage(jwt, chatId, message), HttpStatus.CREATED);
    }

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<Message>> findMessagesByChatId(@PathVariable("chatId") Integer chatId){
        return new ResponseEntity<>(messageService.findMessagesByChatId(chatId), HttpStatus.OK);
    }
}
