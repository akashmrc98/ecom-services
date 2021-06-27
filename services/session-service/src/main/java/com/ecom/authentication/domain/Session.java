package com.ecom.authentication.domain;

import lombok.Data;
import javax.persistence.*;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Session {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long authorizationId;

	@Column(unique = true)
	private Long userId;

	@Column(unique = true)
	private String username;
	@Column(unique = true)
	private String password;

	private String userRole;
	private String currentAccessToken;
	private String refreshToken;

	private  boolean isAccountNonLocked;
	private  boolean isEnabled;
	private  boolean isCredentialsNonExpired;
	private  boolean isAccountNonExpired;
}