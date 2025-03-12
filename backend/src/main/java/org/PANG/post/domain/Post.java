package org.PANG.post.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import org.PANG.post.domain.common.Location;
import org.PANG.user.domain.User;

@Builder
@Getter
public class Post {

    private final Long id;
    private final Content content;
    private final PostInfo postInfo;
    private User author;

    public Post(Long id, User author, Content content, PostInfo postInfo) {
        this.id = id;
        this.content = content;
        this.postInfo = postInfo;
        this.author = author;
    }

    public static Post createDefaultPost(Long id, User author, String title, String contentText,
        String category, double latitude, double longitude, LocalDateTime start_time,
        LocalDateTime end_time, int max_users, int age) {
        return new Post(id, author, new Content(title, contentText),
            new PostInfo(category, latitude, longitude, start_time, end_time, max_users, age));
    }

    public String getTitle() {
        return content.getTitle();
    }

    public String getContentText() {
        return content.getContentText();
    }

    public String getCategory() {
        return postInfo.getCategory();
    }

    public Location getLocation() {
        return postInfo.getLocation();
    }

    public LocalDateTime getStart_time() {
        return postInfo.getStart_time();
    }

    public LocalDateTime getEnd_time() {
        return postInfo.getEnd_time();
    }

    public int getMax_users() {
        return postInfo.getMax_users();
    }

    public int getCurrent_users() {
        return postInfo.getCurrent_users().getCount();
    }

    public int getAge() {
        return postInfo.getAge();
    }

    public PostState getPostState() {
        return postInfo.getPostState();
    }
}
