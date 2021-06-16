package com.ecom.user.mapper.user;

import com.ecom.user.domain.User;
import com.ecom.user.dto.request.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
	@Override
	public User userDtoToUser(UserDto userDto) {
		User user = new User();
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setGender(userDto.getGender());
		user.setPhone(userDto.getPhone());
		user.setEmail(userDto.getEmail());
		user.setUsername(userDto.getUsername());
		return user;
	}
}
