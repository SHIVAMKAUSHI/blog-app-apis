package com.codewithdurgesh.blog.blog_app_apis.controllers;

import com.codewithdurgesh.blog.blog_app_apis.payloads.ApiResponse;
import com.codewithdurgesh.blog.blog_app_apis.payloads.UserDto;
import com.codewithdurgesh.blog.blog_app_apis.services.UserService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@Validated
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id){
        return new ResponseEntity<>(this.userService.getUserById(id), HttpStatus.FOUND);

    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return new ResponseEntity<>(this.userService.getAllUsers(),HttpStatus.OK);
    }
    @PostMapping("/createUser")
    public  ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        return new ResponseEntity<>(this.userService.createUser(userDto),HttpStatus.CREATED);
    }
    @PutMapping("/updateUser/{id}")
    public  ResponseEntity<UserDto> updateUser(@Valid @PathVariable Integer id,@RequestBody UserDto userDto){
        return new ResponseEntity<>(this.userService.updateUser(userDto,id),HttpStatus.OK);

    }
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id){
        this.userService.deleteUser(id);
        return new ResponseEntity(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
    }
}
