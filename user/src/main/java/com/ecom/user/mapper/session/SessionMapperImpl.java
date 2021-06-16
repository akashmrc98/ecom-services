package com.ecom.user.mapper.session;

import com.ecom.user.dto.request.SessionDto;
import com.ecom.user.dto.request.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class SessionMapperImpl implements SessionMapper {
	@SneakyThrows
	@Override
	public String sessionDtoToJson(SessionDto sessionDto) {
		ObjectMapper objectMapper = new ObjectMapper();
		System.out.println(objectMapper.writeValueAsString(sessionDto));
		return objectMapper.writeValueAsString(sessionDto);
	}

	public SessionDto userDtoToSessionDto(UserDto userDto){
		SessionDto sessionDto = new SessionDto();
		sessionDto.setUserId(userDto.getUserId());
		sessionDto.setUsername(userDto.getUsername());
		sessionDto.setPassword(userDto.getPassword());
		sessionDto.setUserRole("basic");
		return sessionDto;
	}

}
