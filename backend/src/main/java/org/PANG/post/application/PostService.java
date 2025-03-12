package org.PANG.post.application;

import lombok.RequiredArgsConstructor;
import org.PANG.post.application.dto.CreatePostRequestDto;
import org.PANG.post.application.interfaces.PostRepository;
import org.PANG.post.domain.Post;
import org.PANG.user.application.UserService;
import org.PANG.user.domain.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final UserService userService;
    private final PostRepository postRepository;


    public Post createPost(CreatePostRequestDto dto) {
        User user = userService.getUser(dto.userId());
        Post post = Post.createDefaultPost(null, user, dto.title(), dto.contentText(),
            dto.category(), dto.latitude(), dto.longitude(), dto.start_time(), dto.end_time(),
            dto.max_users(), dto.age());
        return postRepository.save(post);
    }

    public Post getPost(Long id) {
        return postRepository.findById(id);
    }
}
