package com.pblgllgs.socialapp.service;

import com.pblgllgs.socialapp.models.Reels;

import java.util.List;

public interface ReelsService {

    Reels createReels(Reels reels, String jwt);
    List<Reels> findAllReels();
    List<Reels> findAllReelsByUserId(Integer userId);
}
