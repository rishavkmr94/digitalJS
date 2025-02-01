package com.learn.userauthentication.models;

import com.learn.userauthentication.dtos.LoginRequestDto;
import com.learn.userauthentication.dtos.LoginResponseDto;
import com.learn.userauthentication.dtos.SignUpRequestDto;
import com.learn.userauthentication.dtos.SignUpResponseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseModel{

    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Roles> roles = new ArrayList<>();

    public static User fromSignUpDto(SignUpRequestDto signUpRequestDto) {
        User user = new User();
        user.setEmail(signUpRequestDto.getEmail());
        user.setPassword(signUpRequestDto.getPassword());
        return user;
    }

    public SignUpResponseDto toSignUpResponseDto() {
        SignUpResponseDto signUpResponseDto = new SignUpResponseDto();
        signUpResponseDto.setEmail(this.getEmail());
        signUpResponseDto.setPassword(this.getPassword());
        signUpResponseDto.setUserId(this.getId());
        return signUpResponseDto;
    }

    public static User fromLoginRequestDto(LoginRequestDto loginRequestDto) {
        User user = new User();
        user.setEmail(loginRequestDto.getEmail());
        user.setPassword(loginRequestDto.getPassword());
        return user;
    }

    public LoginResponseDto toLoginResponseDto() {
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setEmail(this.getEmail());
        loginResponseDto.setUserId(this.getId());
        return loginResponseDto;
    }
}
