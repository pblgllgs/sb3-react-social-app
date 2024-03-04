package com.pblgllgs.socialapp.service.impl;
/*
 *
 * @author pblgl
 * Created on 04-03-2024
 *
 */

import com.pblgllgs.socialapp.exception.ResourceNotFoundException;
import com.pblgllgs.socialapp.models.Chat;
import com.pblgllgs.socialapp.models.User;
import com.pblgllgs.socialapp.repository.ChatRepository;
import com.pblgllgs.socialapp.service.ChatService;
import com.pblgllgs.socialapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    @Value("${messages.service.chat.not-found}")
    private String messageChatNotFound;

    private final ChatRepository chatRepository;
    private final UserService userService;

    @Override
    public Chat createChat(String jwt, Integer userId) {
        User reqUser = userService.getUserFromToken(jwt);
        User user2 = userService.findUserById(userId);
        Chat isExists = chatRepository.findChatByUsersId(reqUser, user2);
        if (isExists != null) {
            return isExists;
        }
        Chat chat = new Chat();
        chat.getUsers().add(reqUser);
        chat.getUsers().add(user2);
        chat.setTimestamp(LocalDateTime.now());

        return chatRepository.save(chat);
    }

    @Override
    public Chat findChatById(Integer chatId) {
        return chatRepository.findById(chatId).orElseThrow(() -> new ResourceNotFoundException(messageChatNotFound + chatId));
    }

    @Override
    public List<Chat> findUsersChat(String jwt) {
        User user = userService.getUserFromToken(jwt);
        return chatRepository.findByUsersId(user.getId());
    }
}
