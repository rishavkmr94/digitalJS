package com.learn.userauthentication.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpResponseDto {
    private String email;
    private String password;
    private Long userId;

}
