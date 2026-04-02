package com.nexcircle.presentation.controller;

import com.nexcircle.application.user.dto.UserFilter;
import com.nexcircle.application.user.dto.UserLogin;
import com.nexcircle.application.user.dto.UserRegister;
import com.nexcircle.application.user.dto.UserResponse;
import com.nexcircle.application.user.usecase.GetCurrentUserUseCase;
import com.nexcircle.application.user.usecase.GetUserUseCase;
import com.nexcircle.application.user.usecase.LoginUserUseCase;
import com.nexcircle.application.user.usecase.RegisterUserUseCase;
import com.nexcircle.infrastructure.security.jwt.JwtTokenProvider;
import com.nexcircle.shared.dto.ApiResponse;
import com.nexcircle.shared.enums.MessageCode;
import com.nexcircle.shared.utils.ResponseFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/users")
@Slf4j
public class UserController {
    RegisterUserUseCase userUseCase;
    LoginUserUseCase loginUserUseCase;
    GetCurrentUserUseCase getCurrentUserUseCase;
    GetUserUseCase getUserUseCase;
    JwtTokenProvider jwtTokenProvider;


    @PostMapping("/register")
    public ApiResponse<UserResponse> registerUser(@RequestBody UserRegister register){
        log.debug("In register");

        UserResponse response  = this.userUseCase.registerUser(register);
        return ResponseFactory.success(
                MessageCode.USER_CREATED_SUCCESS,
                response
        );
    }

    @PostMapping("/login")
    public ApiResponse<Map<String,String>> loginUser(@RequestBody UserLogin userLogin){
        log.debug("In login user");
        if(!this.loginUserUseCase.isLogin(userLogin)){
            return ResponseFactory.error(
                    MessageCode.USER_NOT_FOUND
            );
        }

        String token = this.jwtTokenProvider.generateToken(userLogin.getUsername());
        return ResponseFactory.success(
                MessageCode.USER_LOGIN_SUCCESS,
                Map.of("accessToken", token)
        );
    }

    @GetMapping("/me")
    public ApiResponse<UserResponse> getCurrentUser(){
        log.debug("In current user");

        UserResponse response = this.getCurrentUserUseCase.getCurrentUser();
        return ResponseFactory.success(
                MessageCode.GET_USER_SUCCESS,
                response
        );
    }

    @GetMapping
    public  ApiResponse<Map<String, Object>> getNearByUsers(@ModelAttribute UserFilter filter){
        log.debug("in load user nearby users");
        Page<UserResponse> responses = this.getUserUseCase.getNearbyUsers(filter);

        return ResponseFactory.success(
          MessageCode.SUCCESS,
          Map.of(
                  "data", responses.getContent(),
                  "total", responses.getTotalElements()
                  )
        );
    }
}
