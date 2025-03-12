package org.PANG.post.application.dto;

import java.time.LocalDateTime;

public record CreatePostRequestDto(Long userId, String title, String contentText, String category,
                                   double latitude, double longitude, LocalDateTime start_time,
                                   LocalDateTime end_time, int max_users, int age
) {

}
