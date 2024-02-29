package com.pblgllgs.socialapp.repository;

import com.pblgllgs.socialapp.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {

    List<Post> findByUserId(Integer userId);
}
