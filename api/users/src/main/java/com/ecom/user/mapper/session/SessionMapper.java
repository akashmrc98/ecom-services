package com.ecom.user.mapper.session;

import com.ecom.user.dto.SessionDto;
import com.ecom.user.dto.UserDto;

public interface SessionMapper {
	String sessionDtoToJson(SessionDto sessionDto);
	SessionDto userDtoToSessionDto(UserDto userDto);
}
