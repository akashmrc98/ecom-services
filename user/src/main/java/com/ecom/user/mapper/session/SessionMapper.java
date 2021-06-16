package com.ecom.user.mapper.session;

import com.ecom.user.dto.request.SessionDto;
import com.ecom.user.dto.request.UserDto;

public interface SessionMapper {
	String sessionDtoToJson(SessionDto sessionDto);
	SessionDto userDtoToSessionDto(UserDto userDto);
}
