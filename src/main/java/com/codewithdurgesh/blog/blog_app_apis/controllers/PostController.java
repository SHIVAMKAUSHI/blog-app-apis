package com.codewithdurgesh.blog.blog_app_apis.controllers;

import com.codewithdurgesh.blog.blog_app_apis.config.AppConstants;
import com.codewithdurgesh.blog.blog_app_apis.payloads.ApiResponse;
import com.codewithdurgesh.blog.blog_app_apis.payloads.FileResponse;
import com.codewithdurgesh.blog.blog_app_apis.payloads.PostDto;
import com.codewithdurgesh.blog.blog_app_apis.payloads.PostResponse;
import com.codewithdurgesh.blog.blog_app_apis.services.FileService;
import com.codewithdurgesh.blog.blog_app_apis.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private FileService fileService;
    @Value("${project.image}")
    private String path;
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
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir
            ){
        PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);

        return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
    }
    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostDto>> search(@PathVariable String keyword){
        List<PostDto> postDtos = this.postService.searchPostByKeyword(keyword);
        return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
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
    @PostMapping("/posts/uploadImage/{postId}")
    public ResponseEntity<PostDto> imageUpload(
            @RequestParam("image") MultipartFile image,

            @PathVariable("postId") Integer postId
    ) throws IOException {
        String fileName = null;
        PostDto postDto = this.postService.getPostById(postId);
        fileName = this.fileService.uploadImage(path, image);
        postDto.setImageName(fileName);
        this.postService.updatePost(postDto,postId);
        return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
    }
    @GetMapping(value = "/posts/downloadImage/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse response
    ) throws IOException
    {
        InputStream resource = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }


}
