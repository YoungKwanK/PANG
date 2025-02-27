package org.PANG.domain;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "birth", nullable = false)
    private Date birth;

    @Column(name = "profile_iamge")
    private String profile_iamge;

    @Column(name = "residence", nullable = false)
    private String residence;

    @Column(name = "following")
    private Integer following;

    @Column(name = "follower")
    private Integer follower;

    @Column(name = "avg_review")
    private Float avg_review;


    @Builder
    public User(String email, String nickname, Date birth, String residence) {
        this.email = email;
        this.nickname = nickname;
        this.birth = birth;
        this.residence = residence;
    }

    //사용자 이름 변경
    public User update(String nickname) {
        this.nickname = nickname;
        return this;
    }
}