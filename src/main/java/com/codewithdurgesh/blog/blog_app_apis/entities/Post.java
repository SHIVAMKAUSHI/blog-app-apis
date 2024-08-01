package com.codewithdurgesh.blog.blog_app_apis.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "posts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    @Column(name = "post_title",nullable = false,length = 100)
    private String title;
    @Column(length = 1000)
    private String content;

    private String imageName;
    private Date addedDate;
    @ManyToOne
    private User user;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}