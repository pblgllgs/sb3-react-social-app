package com.pblgllgs.socialapp.service.impl;
/*
 *
 * @author pblgl
 * Created on 06-03-2024
 *
 */

import com.pblgllgs.socialapp.models.Image;
import com.pblgllgs.socialapp.models.User;
import com.pblgllgs.socialapp.models.dto.ImageDto;
import com.pblgllgs.socialapp.repository.ImageRepository;
import com.pblgllgs.socialapp.repository.UserRepository;
import com.pblgllgs.socialapp.service.CloudinaryService;
import com.pblgllgs.socialapp.service.ImageService;
import com.pblgllgs.socialapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final CloudinaryService cloudinaryService;
    private final ImageRepository imageRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<Map> uploadImage(ImageDto imageDto, String jwt) {
        User user = userService.getUserFromToken(jwt);
        try {
            if (imageDto.name().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (imageDto.file().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            Image image = new Image();
            image.setName(imageDto.name());
            image.setUrl(cloudinaryService.uploadFile(imageDto.file(), "social_app"));
            String[] split = image.getUrl().split("/");
            String imageIdentifier = split[split.length - 1];
            image.setImageIdentifier(imageIdentifier);
            image.setUser(user);
            if (image.getUrl() == null) {
                return ResponseEntity.badRequest().build();
            }
            Image newImage = imageRepository.save(image);
            user.setImage(newImage);
            userRepository.save(user);
            return ResponseEntity.ok().body(
                    Map.of(
                            "nameImage", image.getName(),
                            "url", image.getUrl(),
                            "imageIdentifier", image.getImageIdentifier(),
                            "userId", image.getUser().getId(),
                            "UUID", image.getId()
                    ));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResponseEntity<Map> updateImage(ImageDto imageDto, String jwt) {
        User user = userService.getUserFromToken(jwt);
        try {
            if (imageDto.name().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (imageDto.file().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (user.getImage().getId() != null) {
                try {
                    cloudinaryService.deleteImage(user.getImage().getImageIdentifier());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            Image imageToUpdate = user.getImage();
            imageToUpdate.setName(imageDto.name());
            imageToUpdate.setUrl(cloudinaryService.uploadFile(imageDto.file(), "social_app"));
            imageToUpdate.setUser(user);
            String[] split = imageToUpdate.getUrl().split("/");
            String imageIdentifier = split[split.length - 1];
            imageToUpdate.setImageIdentifier(imageIdentifier);
            if (imageToUpdate.getUrl() == null) {
                return ResponseEntity.badRequest().build();
            }
            Image imageUpdated = imageRepository.save(imageToUpdate);
            user.setImage(imageUpdated);
            userRepository.save(user);
            return ResponseEntity.ok().body(
                    Map.of(
                            "nameImage", imageUpdated.getName(),
                            "url", imageUpdated.getUrl(),
                            "imageIdentifier", imageUpdated.getImageIdentifier(),
                            "userId", user.getId(),
                            "UUID", imageUpdated.getId()
                    ));

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
