package com.pblgllgs.socialapp.models.dto;

import org.springframework.web.multipart.MultipartFile;

public record ImageDto(
        MultipartFile file,
        String name
) {
}
