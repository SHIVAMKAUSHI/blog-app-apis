package com.codewithdurgesh.blog.blog_app_apis.services;


import com.codewithdurgesh.blog.blog_app_apis.entities.Post;
import com.codewithdurgesh.blog.blog_app_apis.payloads.PostDto;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface PostService {
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
    public PostDto updatePost(PostDto postDto);
    public void deletePost(Integer postId);
    public PostDto getPostById(Integer postId);
    public List<PostDto> getAllPost();
    public List<PostDto> getAllPostsByCategory(Integer categoryId);
    public List<PostDto> getAllPostByUser(Integer userId);
    public List<PostDto> searchPostByKeyword(String keyword);

}