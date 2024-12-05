package com.codewithdurgesh.blog.blog_app_apis.controllers;

import com.codewithdurgesh.blog.blog_app_apis.payloads.CommentDto;
import com.codewithdurgesh.blog.blog_app_apis.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("createComment/post/{postId}/user/{userId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable Integer postId, @PathVariable Integer userId){
        CommentDto commentDtoNew = commentService.createComment(commentDto, postId, userId);
        return  new ResponseEntity<CommentDto>(commentDtoNew, HttpStatus.CREATED);
    }
    @DeleteMapping("/deleteComment/{commentId}")
    public void deleteComment(@PathVariable Integer commentId){
        commentService.deleteComment(commentId);
    }
}
