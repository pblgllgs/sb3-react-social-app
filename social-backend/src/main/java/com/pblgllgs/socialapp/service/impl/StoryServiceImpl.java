package com.pblgllgs.socialapp.service.impl;
/*
 *
 * @author pblgl
 * Created on 02-03-2024
 *
 */

import com.pblgllgs.socialapp.models.Story;
import com.pblgllgs.socialapp.models.User;
import com.pblgllgs.socialapp.repository.StoryRepository;
import com.pblgllgs.socialapp.service.StoryService;
import com.pblgllgs.socialapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoryServiceImpl implements StoryService {

    private final StoryRepository storyRepository;
    private final UserService userService;
    @Override
    public Story createStory(Story story, String jwt) {
        User user = userService.getUserFromToken(jwt);
        Story newStory = Story.builder()
                .image(story.getImage())
                .captions(story.getCaptions())
                .timestamp(LocalDateTime.now())
                .user(user)
                .build();
        return storyRepository.save(newStory);
    }

    @Override
    public List<Story> findStoriesByUserId(Integer userId) {
        User user = userService.findUserById(userId);
        return storyRepository.findByUserId(user.getId());
    }
}
