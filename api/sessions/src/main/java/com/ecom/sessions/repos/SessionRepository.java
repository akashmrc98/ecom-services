package com.ecom.sessions.repos;

import com.ecom.sessions.domains.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
	Session findByUsername(String username);
	Optional<Session> findByCurrentAccessToken(String token);
}
