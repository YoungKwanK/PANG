package org.PANG.user.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.PANG.common.domain.PositiveIntegerCounter;
import org.PANG.user.domain.User;
import org.PANG.user.domain.UserInfo;

@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "birth", nullable = false)
    private Date birth;

    @Column(name = "profile_image")
    private String profile_image;

    @Column(name = "residence", nullable = false)
    private String residence;

    @Column(name = "following")
    private Integer following;

    @Column(name = "follower")
    private Integer follower;

    @Column(name = "avg_review")
    private Float avg_review;


    public UserEntity(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.birth = user.getBirth();
        this.profile_image = user.getProfile_image();
        this.residence = user.getResidence();
        this.following = user.getFollowingCount();
        this.follower = user.getFollowerCount();
        this.avg_review = user.getAvg_review();
    }

    public User toUser() {
        return User.builder()
            .id(id)
            .userInfo(new UserInfo(email, name, birth, profile_image, residence))
            .following(new PositiveIntegerCounter(following))
            .follower(new PositiveIntegerCounter(follower))
            .build();
    }
}