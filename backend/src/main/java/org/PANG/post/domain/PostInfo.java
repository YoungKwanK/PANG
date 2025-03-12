package org.PANG.post.domain;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.PANG.common.domain.PositiveIntegerCounter;
import org.PANG.post.domain.common.Location;

@Getter
@Setter
public class PostInfo {

    private String category;
    private Location location; //경도
    private LocalDateTime start_time;
    private LocalDateTime end_time;
    private int max_users;
    private PositiveIntegerCounter current_users;
    private int age;
    private PostState postState;

    public PostInfo(String category, double latitude, double longitude, LocalDateTime start_time,
        LocalDateTime end_time, int max_users, int age) {
        this.category = category;
        this.location = new Location(latitude, longitude);
        this.start_time = start_time;
        this.end_time = end_time;
        this.max_users = max_users;
        this.current_users = new PositiveIntegerCounter(1);
        this.age = age;
        postState = PostState.CONTINUE;
    }

    public PostInfo(String category, double latitude, double longitude, LocalDateTime start_time,
        LocalDateTime end_time, int max_users, PositiveIntegerCounter current_users, int age,
        PostState postState) {
        this.category = category;
        this.location = new Location(latitude, longitude);
        this.start_time = start_time;
        this.end_time = end_time;
        this.max_users = max_users;
        this.current_users = current_users;
        this.age = age;
        this.postState = postState;
    }

    public void increaseUsers() {
        if (current_users.getCount() >= max_users) {
            throw new IllegalArgumentException("인원 수 초과");
        }
        current_users.increaseCount();
    }

    public void decreaseUsers() {
        current_users.decreaseCount();
    }


}
