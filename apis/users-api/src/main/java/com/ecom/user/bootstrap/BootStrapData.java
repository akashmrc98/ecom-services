package com.ecom.user.bootstrap;

import com.ecom.user.dto.UserDto;
import com.ecom.user.repository.UserRepository;
import com.ecom.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BootStrapData  implements CommandLineRunner {

	private final UserRepository userRepository;
	private final UserService userService;

	public void setupUser(){
		if(userRepository.count() == 0){
			UserDto user = new UserDto();
			user.setUserId(1L);
			user.setFirstName("akash");
			user.setLastName("akash");
			user.setGender("male");
			user.setPhone("9494535327");
			user.setEmail("akashmadduru@gmail.com");
			user.setPassword("akashmrc98");
			user.setUsername("akash");

			userService.saveUser(user);
		}

	}

	@Override
	public void run(String... args) throws Exception {

	}
}
