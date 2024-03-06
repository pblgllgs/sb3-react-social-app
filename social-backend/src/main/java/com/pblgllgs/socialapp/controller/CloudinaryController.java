package com.pblgllgs.socialapp.controller;

import com.pblgllgs.socialapp.models.Image;
import com.pblgllgs.socialapp.service.impl.CloudinaryService;
import com.pblgllgs.socialapp.service.impl.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/1.0/cloudinary")
@Slf4j
@RequiredArgsConstructor
public class CloudinaryController {

    private final CloudinaryService cloudinaryService;
    private final ImageService imageService;

    @Value("${message.controller.cloudinary.image-not-valid}")
    private String messageImageNotValid;

    @Value("${message.controller.cloudinary.image-upload-success}")
    private String messageImageSuccessfullyUploaded;

    @Value("${message.controller.cloudinary.image-not-exist}")
    private String messageImageNotExists;

    @Value("${message.controller.cloudinary.image-deleted-success}")
    private String messageImageSuccessfullyDeleted;

    @Value("${message.controller.cloudinary.image-delete-error}")
    private String messageImageDontDelete;

    @GetMapping("/list")
    public ResponseEntity<List<Image>> list() {
        List<Image> list = imageService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(
            @RequestParam MultipartFile multipartFile,
            @RequestHeader("Authorization") String jwt
    ) throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if (bi == null) {
            return new ResponseEntity<>(messageImageNotValid, HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile,jwt);
        Image image = new Image((String) result.get("original_filename"),
                (String) result.get("url"),
                (String) result.get("public_id"));
        imageService.save(image);
        log.info(image.getImageUrl());
        return new ResponseEntity<>(messageImageSuccessfullyUploaded, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        Optional<Image> imageOptional = imageService.getOne(id);
        if (imageOptional.isEmpty()) {
            return new ResponseEntity<>(messageImageNotExists, HttpStatus.NOT_FOUND);
        }
        Image image = imageOptional.get();
        String cloudinaryImageId = image.getImageId();
        try {
            cloudinaryService.delete(cloudinaryImageId);
        } catch (IOException e) {
            return new ResponseEntity<>(messageImageDontDelete, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        imageService.delete(id);
        return new ResponseEntity<>(messageImageSuccessfullyDeleted, HttpStatus.OK);
    }

}
