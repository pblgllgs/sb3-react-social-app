package com.pblgllgs.socialapp.service;

import com.pblgllgs.socialapp.models.Message;

import java.util.List;

public interface MessageService {

    Message createMessage(String jwt, Integer chatId, Message message);

    List<Message> findMessagesByChatId(Integer chatId);
}
