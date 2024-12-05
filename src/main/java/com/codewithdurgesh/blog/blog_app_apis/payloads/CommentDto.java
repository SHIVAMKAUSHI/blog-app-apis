package com.codewithdurgesh.blog.blog_app_apis.payloads;

import com.codewithdurgesh.blog.blog_app_apis.entities.Post;
import com.codewithdurgesh.blog.blog_app_apis.entities.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentDto {

    private Integer commentId;
    private String comment;


}
