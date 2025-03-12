package org.PANG.post.ui;

import lombok.RequiredArgsConstructor;
import org.PANG.post.application.PostService;
import org.PANG.post.application.dto.CreatePostRequestDto;
import org.PANG.post.domain.Post;
import org.PANG.post.ui.dto.GetPostHeaderResponseDto;
import org.PANG.post.ui.dto.GetPostResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity<Long> create(@RequestBody CreatePostRequestDto dto) {
        Post post = postService.createPost(dto);
        return ResponseEntity.ok().body(post.getId());
    }

    @GetMapping("/postHeader/{postId}")
    public ResponseEntity<GetPostHeaderResponseDto> getPostHeader(
        @PathVariable(name = "postId") Long postId) {
        GetPostHeaderResponseDto dto = GetPostHeaderResponseDto.postToDto(
            postService.getPost(postId));

        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<GetPostResponseDto> getPost(
        @PathVariable(name = "postId") Long postId) {
        GetPostResponseDto dto = GetPostResponseDto.postToDto(postService.getPost(postId));

        return ResponseEntity.ok().body(dto);
    }

}
