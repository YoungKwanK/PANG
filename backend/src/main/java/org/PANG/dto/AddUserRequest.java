package org.PANG.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
public class AddUserRequest {
    private String email;
    private Date birth;
    private String residence;
    private String nickname;
}
