package com.vnpt.sinhvienso.controller.v1;

import com.vnpt.sinhvienso.document.Post;
import com.vnpt.sinhvienso.dto.response.ApiResponsePosts;
import com.vnpt.sinhvienso.dto.response.ApiResponseTest;
import com.vnpt.sinhvienso.service.PostsService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    @Autowired
    PostsService postsService;

    @GetMapping("")
    public ApiResponsePosts<Post> getAllPosts(){
       var data = postsService.getAllPosts();

       return ApiResponsePosts.<Post>builder()
               .data(data)
               .build();
    }
//
//    @GetMapping("")
//    public ResponseEntity<?> getAllPosts(HttpServletRequest request,
//                                        @RequestParam(required = true) String key,
//                                         Pageable pageable){
//
//        var getAllPost = postsService.getAllPosts();
//        return ResponseEntity.status(HttpStatus.OK).body(getAllPost);
//    }
}
