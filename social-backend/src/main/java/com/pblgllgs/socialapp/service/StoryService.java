package com.pblgllgs.socialapp.service;
/*
 *
 * @author pblgl
 * Created on 02-03-2024
 *
 */

import com.pblgllgs.socialapp.models.Story;

import java.util.List;

public interface StoryService {

    Story createStory(Story story, String jwt);
    List<Story> findStoriesByUserId(Integer userId);
}
