package org.PANG.user.domain;

import java.sql.Date;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.PANG.common.domain.PositiveIntegerCounter;

@Getter
@Builder
@AllArgsConstructor
public class User {

    private Long id;
    private UserInfo userInfo;

    private PositiveIntegerCounter following;
    private PositiveIntegerCounter follower;
    private Float avg_review;

    public User(Long id, UserInfo userInfo) {

        if (userInfo == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.userInfo = userInfo;
        this.follower = new PositiveIntegerCounter();
        this.following = new PositiveIntegerCounter();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public String getEmail() {
        return userInfo.getEmail();
    }

    public String getName() {
        return userInfo.getName();
    }

    public Date getBirth() {
        return userInfo.getBirth();
    }

    public String getProfile_image() {
        return userInfo.getProfile_image();
    }

    public String getResidence() {
        return userInfo.getResidence();
    }

    public int getFollowingCount() {
        return following.getCount();
    }

    public int getFollowerCount() {
        return follower.getCount();
    }
}
