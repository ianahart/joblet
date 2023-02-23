package com.authentication.demo.post;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/posts")
public class PostController {

    @GetMapping(path = "/")
    public ResponseEntity<List<String>> getPosts() {
        return ResponseEntity.status(HttpStatus.OK).body(
                List.of("post1", "post2", "post3"));
    }
}
