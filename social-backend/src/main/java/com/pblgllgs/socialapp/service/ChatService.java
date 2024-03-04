package com.pblgllgs.socialapp.service;

import com.pblgllgs.socialapp.models.Chat;

import java.util.List;

public interface ChatService {

    Chat createChat(String jwt, Integer userId);

    Chat findChatById(Integer chatId);

    List<Chat> findUsersChat(String jwt);
}
