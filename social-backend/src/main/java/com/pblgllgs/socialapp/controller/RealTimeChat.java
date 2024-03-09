package com.pblgllgs.socialapp.controller;

/*
 *
 * @author pblgl
 * Created on 08-03-2024
 *
 */

import com.pblgllgs.socialapp.models.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class RealTimeChat {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public RealTimeChat(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/chat/{groupId}")
    public Message sendToUser(
            @Payload Message message,
            @DestinationVariable String groupId
    ) {
        simpMessagingTemplate.convertAndSendToUser(groupId, "/private", message);
        return message;
    }
}
