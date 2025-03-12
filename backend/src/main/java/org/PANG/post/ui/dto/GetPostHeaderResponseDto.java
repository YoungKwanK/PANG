package org.PANG.post.ui.dto;

import java.time.LocalDateTime;
import org.PANG.post.domain.Post;

public record GetPostHeaderResponseDto(String title, LocalDateTime start_time,
                                       LocalDateTime end_time, int max_users, int current_users) {

    public static GetPostHeaderResponseDto postToDto(Post post) {
        return new GetPostHeaderResponseDto(post.getTitle(),
            post.getStart_time(),
            post.getEnd_time(), post.getMax_users(),
            post.getCurrent_users());
    }
}
