package com.codewithdurgesh.blog.blog_app_apis.services;

import com.codewithdurgesh.blog.blog_app_apis.exceptions.ResourceNotFoundException;
import com.codewithdurgesh.blog.blog_app_apis.payloads.UserDto;

import java.util.List;

public interface UserService {
    UserDto registerUser(UserDto userDto);
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto , Integer userId) throws ResourceNotFoundException;
    List<UserDto> getAllUsers();
    UserDto getUserById(Integer id);
    void deleteUser(Integer userId);
}
