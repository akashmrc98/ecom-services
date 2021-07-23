package com.ecom.sessions.mappers;

import com.ecom.sessions.domains.Session;
import com.ecom.sessions.dtos.SessionDto;

public interface SessionMapper {
	SessionDto sessionStringToSessionDto(String sessionJson);
	Session sessionDtoToAuthentication(SessionDto sessionDto);
}
