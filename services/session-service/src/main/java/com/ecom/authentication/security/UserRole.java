package com.ecom.authentication.security;

import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public enum UserRole {
    USER, VENDOR, GUEST, ADMIN
}

