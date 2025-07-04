package com.vnpt.sinhvienso.service;

import com.vnpt.sinhvienso.document.Post;
import com.vnpt.sinhvienso.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostsService {
    @Autowired
    PostRepository postRepository;

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }
}
