package com.pblgllgs.socialapp.repository;

import com.pblgllgs.socialapp.models.Reels;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReelsRepository extends JpaRepository<Reels,Integer> {

    List<Reels> findByUserId(Integer userId);
}
