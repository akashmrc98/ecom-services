package com.ecom.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SessionDto implements Serializable {
	private Long userId;
	private String username;
	private String password;
	private String userRole;
}
