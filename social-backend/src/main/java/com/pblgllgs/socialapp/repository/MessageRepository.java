package com.pblgllgs.socialapp.repository;
/*
 *
 * @author pblgl
 * Created on 04-03-2024
 *
 */

import com.pblgllgs.socialapp.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {
    List<Message> findByChatId(Integer chatId);
}
