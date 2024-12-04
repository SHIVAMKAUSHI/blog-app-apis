package com.codewithdurgesh.blog.blog_app_apis.payloads;

import com.codewithdurgesh.blog.blog_app_apis.entities.Post;
import com.codewithdurgesh.blog.blog_app_apis.entities.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class CommentDto {
    private Integer commentId;
    private String comment;
    private PostDto postDto;
    private UserDto userDto;
}
