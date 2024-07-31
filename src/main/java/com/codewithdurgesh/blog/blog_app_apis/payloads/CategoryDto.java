package com.codewithdurgesh.blog.blog_app_apis.payloads;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private Integer categoryId;
    @NotEmpty
    @Size(min = 3,max = 30,message = "Title Size should not be less then 3 and gereater than 30 characters")
    private String categoryTitle;
    @NotEmpty
    @Size(min = 10,max = 500,message = "Title Size should not be less then 10 and gereater than 500 characters")
    private String categoryDescription;
}
