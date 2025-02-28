package org.PANG.user.application.dto;

import java.sql.Date;

public record CreateUserRequestDto(String email, Date birth, String residence, String name) {

}
