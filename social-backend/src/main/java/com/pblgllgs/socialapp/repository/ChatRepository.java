package com.pblgllgs.socialapp.repository;

import com.pblgllgs.socialapp.models.Chat;
import com.pblgllgs.socialapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat,Integer> {

    List<Chat> findByUsersId(Integer userId);

    @Query("select c from  Chat c where :user Member of c.users and :reqUser Member of c.users")
    Chat findChatByUsersId(@Param("reqUser") User reqUser,@Param("user") User user);
}
