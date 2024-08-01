package com.codewithdurgesh.blog.blog_app_apis.services.impl;

import com.codewithdurgesh.blog.blog_app_apis.entities.Category;
import com.codewithdurgesh.blog.blog_app_apis.entities.Post;
import com.codewithdurgesh.blog.blog_app_apis.entities.User;
import com.codewithdurgesh.blog.blog_app_apis.exceptions.ResourceNotFoundException;
import com.codewithdurgesh.blog.blog_app_apis.payloads.CategoryDto;
import com.codewithdurgesh.blog.blog_app_apis.payloads.PostDto;
import com.codewithdurgesh.blog.blog_app_apis.payloads.UserDto;
import com.codewithdurgesh.blog.blog_app_apis.repositories.CategoryRepo;
import com.codewithdurgesh.blog.blog_app_apis.repositories.PostRepository;
import com.codewithdurgesh.blog.blog_app_apis.repositories.UserRepo;
import com.codewithdurgesh.blog.blog_app_apis.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;


    @Override
    public PostDto createPost(PostDto postDto,Integer userId, Integer categoryId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        Post post = this.modelMapper.map(postDto, Post.class);
        post.setAddedDate(new Date());
        post.setImageName("Default.png");
        post.setUser(user);
        post.setCategory(category);



        Post newPost = this.postRepository.save(post);
        PostDto newPostDto = this.modelMapper.map(newPost, PostDto.class);
        newPostDto.setCategoryDto(this.modelMapper.map(newPost.getCategory(), CategoryDto.class));
        newPostDto.setUserDto(this.modelMapper.map(newPost.getUser(), UserDto.class));
        return newPostDto;
    }

    @Override
    public PostDto updatePost(PostDto postDto) {
        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public PostDto getPostById(Integer postId) {
        return null;
    }

    @Override
    public List<PostDto> getAllPost() {
        return null;
    }

    @Override
    public List<PostDto> getAllPostsByCategory(Integer categoryId) {
        return null;
    }

    @Override
    public List<PostDto> getAllPostByUser(Integer userId) {
        return null;
    }

    @Override
    public List<PostDto> searchPostByKeyword(String keyword) {
        return null;
    }
}
