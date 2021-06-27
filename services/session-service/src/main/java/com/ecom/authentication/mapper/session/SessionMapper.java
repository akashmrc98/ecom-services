package com.ecom.authentication.mapper.session;

import com.ecom.authentication.domain.Session;
import com.ecom.authentication.dto.SessionDto;

public interface SessionMapper {
	SessionDto sessionStringToSessionDto(String sessionJson);
	Session sessionDtoToAuthentication(SessionDto sessionDto);
}
