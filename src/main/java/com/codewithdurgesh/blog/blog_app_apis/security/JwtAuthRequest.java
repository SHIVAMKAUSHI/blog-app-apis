package com.codewithdurgesh.blog.blog_app_apis.security;

import lombok.Data;

@Data
public class JwtAuthRequest {
    private String username;
    private String password;
}
