package com.codewithdurgesh.blog.blog_app_apis.services.impl;

import com.codewithdurgesh.blog.blog_app_apis.config.AppConstants;
import com.codewithdurgesh.blog.blog_app_apis.entities.Role;
import com.codewithdurgesh.blog.blog_app_apis.entities.User;
import com.codewithdurgesh.blog.blog_app_apis.exceptions.ResourceNotFoundException;
import com.codewithdurgesh.blog.blog_app_apis.payloads.UserDto;
import com.codewithdurgesh.blog.blog_app_apis.repositories.RoleRepo;
import com.codewithdurgesh.blog.blog_app_apis.repositories.UserRepo;
import com.codewithdurgesh.blog.blog_app_apis.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepo.findById(AppConstants.NORMAL).get();
        user.getRoles().add(role);
        User save = this.userRepo.save(user);
        UserDto map = this.modelMapper.map(save, UserDto.class);
        map.setId(save.getId());
        return map;

    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.userRepo.save(modelMapper.map(userDto,User.class));
        return this.modelMapper.map(user,UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto,Integer userId) throws ResourceNotFoundException {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","id", userId));

        user.setAbout(userDto.getAbout());
        user.setEmail(userDto.getEmail());
//        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        this.userRepo.save(user);

       return this.modelMapper.map(user,UserDto.class);

    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList = this.userRepo.findAll();
        List<UserDto> list = userList.stream().map(user -> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
//        List<UserDto> list=new ArrayList<>();
//        for (User user:userList) {
//            list.add(this.modelMapper.map(user,UserDto.class));
//
//        }
        return list;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","id",userId));
        return this.modelMapper.map(user,UserDto.class);
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepo.deleteById(userId);


    }
}
