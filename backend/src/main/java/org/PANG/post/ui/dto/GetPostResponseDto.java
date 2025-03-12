package org.PANG.post.ui.dto;

import java.time.LocalDateTime;
import org.PANG.post.domain.Post;

public record GetPostResponseDto(String title, String location_name, double latitude,
                                 double longitude, String category,
                                 int max_users, int current_users, int age,
                                 LocalDateTime start_time, LocalDateTime end_time,
                                 String contentText) {

    public static GetPostResponseDto postToDto(Post post) {
        return new GetPostResponseDto(post.getContent().getTitle(),
            post.getLocation().getLocation_name(),
            post.getLocation().getLatitude(),
            post.getLocation().getLongitude(), post.getCategory(),
            post.getMax_users(),
            post.getCurrent_users(), post.getAge(),
            post.getStart_time(),
            post.getEnd_time(), post.getContentText()
        );
    }

}
