package com.codewithdurgesh.blog.blog_app_apis.controllers;

import com.codewithdurgesh.blog.blog_app_apis.payloads.ApiResponse;
import com.codewithdurgesh.blog.blog_app_apis.payloads.CategoryDto;
import com.codewithdurgesh.blog.blog_app_apis.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @PostMapping("/createCategory")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto category = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }
    @PutMapping("/updateCategory/{categoryId}")
    public  ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId){
        CategoryDto categoryDto1 = this.categoryService.updateCategory(categoryDto, categoryId);

        return new ResponseEntity<>(categoryDto1,HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/deleteCategory/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
        this.categoryService.deleteCategory(categoryId);
        ApiResponse apiResponse = new ApiResponse("Category is deleted Successfully..!!!", true);
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.GONE);
    }
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        List<CategoryDto> category = this.categoryService.getCategory();
        return new ResponseEntity<>(category,HttpStatus.OK);

    }
    @GetMapping("/getCategory/{categoryId}")
    public ResponseEntity<CategoryDto> getAllCategories(@PathVariable Integer categoryId) {
        CategoryDto category = this.categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}
