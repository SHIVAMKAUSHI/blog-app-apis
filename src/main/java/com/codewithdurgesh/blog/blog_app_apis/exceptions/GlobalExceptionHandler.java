package com.codewithdurgesh.blog.blog_app_apis.exceptions;

import com.codewithdurgesh.blog.blog_app_apis.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> methodArgumentNotValidException(MethodArgumentNotValidException ex){
//        ApiResponse apiResponse = new ApiResponse(ex.getMessage(), false);
//        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
        Map<String,String> map = new HashMap<>();
        ex.getAllErrors().forEach((error)->{
            String defaultMessage = error.getDefaultMessage();
            String field = ((FieldError) error).getField();
            map.put(field,defaultMessage);
        });
        return  new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> apiException(ApiException ex){
        ApiResponse apiResponse = new ApiResponse(ex.getMessage(), false);
        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }

}

