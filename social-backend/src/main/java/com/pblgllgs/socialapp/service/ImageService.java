package com.pblgllgs.socialapp.service;
/*
 *
 * @author pblgl
 * Created on 06-03-2024
 *
 */

import com.pblgllgs.socialapp.models.dto.ImageDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ImageService {
    ResponseEntity<Map> uploadImage(ImageDto imageModel,String jwt);
}
