package com.codewithdurgesh.blog.blog_app_apis.services;

import com.codewithdurgesh.blog.blog_app_apis.entities.Category;
import com.codewithdurgesh.blog.blog_app_apis.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {
    public CategoryDto createCategory(CategoryDto categoryDto);
    public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
    public CategoryDto getCategoryById(Integer categoryId);
    public List<CategoryDto> getCategory();
    public void deleteCategory(Integer categoryId);
}
