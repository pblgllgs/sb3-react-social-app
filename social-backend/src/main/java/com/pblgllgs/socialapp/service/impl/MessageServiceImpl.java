package com.pblgllgs.socialapp.service.impl;
/*
 *
 * @author pblgl
 * Created on 04-03-2024
 *
 */

import com.pblgllgs.socialapp.models.Chat;
import com.pblgllgs.socialapp.models.Message;
import com.pblgllgs.socialapp.models.User;
import com.pblgllgs.socialapp.repository.ChatRepository;
import com.pblgllgs.socialapp.repository.MessageRepository;
import com.pblgllgs.socialapp.service.ChatService;
import com.pblgllgs.socialapp.service.MessageService;
import com.pblgllgs.socialapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserService userService;
    private final ChatService chatService;
    private final ChatRepository chatRepository;

    @Override
    public Message createMessage(String jwt, Integer chatId, Message message) {
        User user = userService.getUserFromToken(jwt);
        Chat chatFound = chatService.findChatById(chatId);

        Message newMessage = Message.builder()
                .chat(chatFound)
                .user(user)
                .timestamp(LocalDateTime.now())
                .content(message.getContent())
                .image(message.getImage())
                .build();
        chatFound.getMessages().add(newMessage);
        chatRepository.save(chatFound);
        return messageRepository.save(newMessage);
    }

    @Override
    public List<Message> findMessagesByChatId(Integer chatId) {
        Chat chat = chatService.findChatById(chatId);
        return messageRepository.findByChatId(chat.getId());
    }
}
