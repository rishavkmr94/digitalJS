package com.learn.userauthentication.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {
    private long userId;
    private String email;
}
