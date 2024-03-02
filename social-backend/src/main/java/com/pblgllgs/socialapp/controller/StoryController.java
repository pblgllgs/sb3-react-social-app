package com.pblgllgs.socialapp.controller;
/*
 *
 * @author pblgl
 * Created on 02-03-2024
 *
 */

import com.pblgllgs.socialapp.models.Story;
import com.pblgllgs.socialapp.service.StoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/1.0/stories")
public class StoryController {

    private final StoryService storyService;

    @PostMapping
    public ResponseEntity<Story> saveStory(@RequestBody Story story, @RequestHeader("Authorization") String jwt){
        return new ResponseEntity<>(storyService.createStory(story,jwt), HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Story>> findStoriesByUserId(@PathVariable("userId") Integer userId){
        return new ResponseEntity<>(storyService.findStoriesByUserId(userId), HttpStatus.OK);
    }
}
