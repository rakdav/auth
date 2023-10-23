package com.example.auth.repository;

import com.example.auth.entity.Comment;
import com.example.auth.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long>
{
    List<Comment> findAllByPost(Post post);
    Comment findByIdAndUserId(Long commentId,Long userId);
}