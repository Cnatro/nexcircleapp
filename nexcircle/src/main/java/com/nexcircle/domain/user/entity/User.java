package com.nexcircle.domain.user.entity;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private UUID id;
    private String username;
    private String email;
    private String password;
    private String fullName;
    private String avatarUrl;
    private String description;
    private Boolean isOnline;
    private LocalDateTime lastActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //Phải tạo factory method cho domain để không phá hỏng kiến trúc clean
    // static, dùng để tạo object mới, dùng để TẠO object mới
    public static User create(String username, String email, String password) {
        return new User(
                UUID.randomUUID(),
                username,
                email,
                password,
                "",
                "",
                "",
                false,
                null,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    //Instance method -> dùng để THAY ĐỔI trạng thái
    public void encodePassword(String encodedPassword) {
        this.password = encodedPassword;
    }
}