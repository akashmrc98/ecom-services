package com.ecom.sessions.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class AuthDto {
    private String accessToken;
}
