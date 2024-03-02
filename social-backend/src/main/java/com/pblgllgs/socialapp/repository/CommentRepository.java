package com.pblgllgs.socialapp.repository;

import com.pblgllgs.socialapp.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
