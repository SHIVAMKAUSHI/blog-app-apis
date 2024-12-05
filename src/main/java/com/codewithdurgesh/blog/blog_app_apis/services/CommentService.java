package com.codewithdurgesh.blog.blog_app_apis.services;

import com.codewithdurgesh.blog.blog_app_apis.payloads.CommentDto;
import jakarta.persistence.criteria.CriteriaBuilder;

public interface CommentService {
    public CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId);
    public void deleteComment(Integer commentId);
}
