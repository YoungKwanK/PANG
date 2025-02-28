package org.PANG.user.domain;

import java.sql.Date;
import lombok.Getter;

@Getter
public class UserInfo {

    private String email;
    private String name;
    private Date birth;
    private String profile_image;
    private String residence;


    public UserInfo(String email, String name, Date birth, String residence) {
        this.email = email;
        this.name = name;
        this.birth = birth;
        this.residence = residence;
    }

    public UserInfo(String email, String name, Date birth, String profile_image, String residence) {

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException();
        }

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }

        if (birth == null) {
            throw new IllegalArgumentException();
        }

        this.email = email;
        this.name = name;
        this.birth = birth;
        this.profile_image = profile_image;
        this.residence = residence;
    }
}
