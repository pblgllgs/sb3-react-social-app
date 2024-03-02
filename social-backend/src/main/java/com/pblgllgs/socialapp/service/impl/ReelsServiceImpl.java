package com.pblgllgs.socialapp.service.impl;
/*
 *
 * @author pblgl
 * Created on 02-03-2024
 *
 */

import com.pblgllgs.socialapp.models.Reels;
import com.pblgllgs.socialapp.models.User;
import com.pblgllgs.socialapp.repository.ReelsRepository;
import com.pblgllgs.socialapp.service.ReelsService;
import com.pblgllgs.socialapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReelsServiceImpl implements ReelsService {

    private final ReelsRepository  reelsRepository;
    private final UserService userService;
    @Override
    public Reels createReels(Reels reels, String jwt) {
        User user = userService.getUserFromToken(jwt);
        Reels newReels = Reels.builder()
                .title(reels.getTitle())
                .user(user)
                .video(reels.getVideo())
                .build();
        return reelsRepository.save(newReels);
    }

    @Override
    public List<Reels> findAllReels() {
        return reelsRepository.findAll();
    }

    @Override
    public List<Reels> findAllReelsByUserId(Integer userId) {
        User user= userService.findUserById(userId);
        return reelsRepository.findByUserId(user.getId());
    }
}
