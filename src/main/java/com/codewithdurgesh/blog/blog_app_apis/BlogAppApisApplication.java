package com.codewithdurgesh.blog.blog_app_apis;

import com.codewithdurgesh.blog.blog_app_apis.config.AppConstants;
import com.codewithdurgesh.blog.blog_app_apis.entities.Role;
import com.codewithdurgesh.blog.blog_app_apis.repositories.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class BlogAppApisApplication implements CommandLineRunner {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("xyz"));
		Role role_admin = new Role(AppConstants.ADMIN,"ROLE_ADMIN");
		Role role_normal = new Role(AppConstants.NORMAL, "ROLE_NORMAL");
		List<Role> roles = List.of(role_admin,role_normal);
		roles.forEach(r-> System.out.println(r.getName()));
		roleRepo.saveAll(roles);
	}
}
