package com.ecom.authentication.mapper.session;

import com.ecom.authentication.domain.Session;
import com.ecom.authentication.dto.SessionDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class SessionMapperImpl implements  SessionMapper{
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
		authentication.setUserRole(sessionDto.getUserRole());

		authentication.setAccountNonExpired(true);
		authentication.setEnabled(true);
		authentication.setAccountNonLocked(true);
		authentication.setCredentialsNonExpired(true);

		return authentication;
	}
}
