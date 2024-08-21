package com.codewithdurgesh.blog.blog_app_apis.controllers;

import com.codewithdurgesh.blog.blog_app_apis.entities.Post;
import com.codewithdurgesh.blog.blog_app_apis.payloads.ApiResponse;
import com.codewithdurgesh.blog.blog_app_apis.payloads.PostDto;
import com.codewithdurgesh.blog.blog_app_apis.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class PostController {
    @Autowired
    private PostService postService;
    @RequestMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){

        PostDto newPostDto = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(newPostDto, HttpStatus.CREATED);

    }
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategoryId(@PathVariable Integer categoryId){
        List<PostDto> postDtos = this.postService.getAllPostsByCategory(categoryId);
        return new ResponseEntity<>(postDtos,HttpStatus.FOUND);
    }
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUserId(@PathVariable Integer userId){
        List<PostDto> postDtos = this.postService.getAllPostByUser(userId);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        PostDto postDto = this.postService.getPostById(postId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }
    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPosts(){
        List<PostDto> posts = this.postService.getAllPost();
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ApiResponse("post is successfully Deleted",true);
    }
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
        PostDto updatePost = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatePost,HttpStatus.OK);

    }


}
