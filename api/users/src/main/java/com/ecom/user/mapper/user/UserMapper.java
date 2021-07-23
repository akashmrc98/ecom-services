package com.ecom.user.mapper.user;

import com.ecom.user.domain.User;
import com.ecom.user.dto.UserDto;

public interface UserMapper {
	User userDtoToUser(UserDto userDto);
}
