package com.ecom.sessions.mappers;

import com.ecom.sessions.domains.Session;
import com.ecom.sessions.dtos.SessionDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class SessionMapperImpl implements SessionMapper{
	@SneakyThrows
	@Override
	public SessionDto sessionStringToSessionDto(String sessionJson) {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(sessionJson, SessionDto.class);
	}

	public Session sessionDtoToAuthentication(SessionDto sessionDto){
		Session authentication = new Session();
		authentication.setUserId(sessionDto.getUserId());
		authentication.setPassword(sessionDto.getPassword());
		authentication.setUsername(sessionDto.getUsername());

		authentication.setAccountNonExpired(true);
		authentication.setEnabled(true);
		authentication.setAccountNonLocked(true);
		authentication.setCredentialsNonExpired(true);

		return authentication;
	}
}
