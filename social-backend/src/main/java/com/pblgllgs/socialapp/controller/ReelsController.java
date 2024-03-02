package com.pblgllgs.socialapp.controller;
/*
 *
 * @author pblgl
 * Created on 02-03-2024
 *
 */

import com.pblgllgs.socialapp.models.Reels;
import com.pblgllgs.socialapp.service.ReelsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/1.0/reels")
public class ReelsController {

    private final ReelsService reelsService;

    @PostMapping
    public ResponseEntity<Reels> saveReels(@RequestBody Reels reels, @RequestHeader("Authorization") String jwt) {
        return new ResponseEntity<>(reelsService.createReels(reels, jwt), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Reels>> findAllReels() {
        return new ResponseEntity<>(reelsService.findAllReels(), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reels>> findReelsByUserId(
            @PathVariable("userId") Integer userId
    ) {
        return new ResponseEntity<>(reelsService.findAllReelsByUserId(userId), HttpStatus.OK);
    }

}
