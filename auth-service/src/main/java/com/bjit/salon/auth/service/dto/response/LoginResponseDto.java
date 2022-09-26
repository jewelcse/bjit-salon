package com.bjit.salon.auth.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseDto {

    private String token;
    private String type = "Bearer";
    private String username;
    private String email;
    private List<String> roles;
}
