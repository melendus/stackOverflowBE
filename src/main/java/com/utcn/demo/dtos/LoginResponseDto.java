package com.utcn.demo.dtos;

import com.utcn.demo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class LoginResponseDto {
    private String token;
    private Optional<User> user;
}
