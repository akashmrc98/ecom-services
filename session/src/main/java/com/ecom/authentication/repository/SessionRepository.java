package com.ecom.authentication.repository;

import com.ecom.authentication.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
	Session findByUsername(String username);
	Session findByCurrentAccessToken(String token);
}
