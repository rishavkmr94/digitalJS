package com.learn.userauthentication.controllers;

import com.learn.userauthentication.dtos.LoginRequestDto;
import com.learn.userauthentication.dtos.LoginResponseDto;
import com.learn.userauthentication.dtos.SignUpRequestDto;
import com.learn.userauthentication.dtos.SignUpResponseDto;
import com.learn.userauthentication.models.User;
import com.learn.userauthentication.services.IUserService;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class UserAuthController {
    private final IUserService userService;

    @Autowired
    public UserAuthController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> SignUp(@RequestBody SignUpRequestDto signUpRequestDto) throws Exception {
        User user = User.fromSignUpDto(signUpRequestDto);
        SignUpResponseDto signUpResponseDto = userService.signUp(user).toSignUpResponseDto();
        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> Login(@RequestBody LoginRequestDto loginRequestDto) throws Exception {
        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        Pair<User, String> userTokenPair = (userService.login(User.fromLoginRequestDto(loginRequestDto)));
        LoginResponseDto loginResponseDto = userTokenPair.a.toLoginResponseDto();
        headers.add(HttpHeaders.SET_COOKIE, userTokenPair.b);
        return new ResponseEntity<>(loginResponseDto,headers,HttpStatus.OK);
    }
}
