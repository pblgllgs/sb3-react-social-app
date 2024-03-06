package com.pblgllgs.socialapp.controller;

import com.pblgllgs.socialapp.models.User;
import com.pblgllgs.socialapp.models.dto.ImageDto;
import com.pblgllgs.socialapp.service.ImageService;
import com.pblgllgs.socialapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/1.0/cloudinary")
@Slf4j
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<Map> upload(ImageDto imageModel, @RequestHeader("Authorization") String jwt) {
        try {
            return imageService.uploadImage(imageModel,jwt);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
