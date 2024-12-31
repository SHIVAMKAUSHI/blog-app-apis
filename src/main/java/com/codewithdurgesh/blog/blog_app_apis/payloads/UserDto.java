package com.codewithdurgesh.blog.blog_app_apis.payloads;

import com.codewithdurgesh.blog.blog_app_apis.entities.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private int id;
    @NotEmpty
    @Size(min = 3, message = "UserName must be minimum of 3 characters")
    private String name;
    @Email(message = "Email Address is not valid !!!")
    private String email;
    @NotEmpty
    @Size(min = 3,max = 10,message = "password must be minimum of 3 char and maximium of 10 char")
    private String password;
    @NotEmpty
    private String about;
    @NotEmpty
    private Set<Role> roles=new HashSet<>();


}
