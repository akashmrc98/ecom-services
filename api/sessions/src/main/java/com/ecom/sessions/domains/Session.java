package com.ecom.sessions.domains;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

	@Enumerated
	private UserRole userRole;

	private String currentAccessToken;
	private String refreshToken;

	private  boolean isAccountNonLocked;
	private  boolean isEnabled;
	private  boolean isCredentialsNonExpired;
	private  boolean isAccountNonExpired;
}