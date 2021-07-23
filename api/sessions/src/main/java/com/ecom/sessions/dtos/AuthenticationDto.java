package com.ecom.sessions.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthenticationDto {
    private String username;
    private String password;
}