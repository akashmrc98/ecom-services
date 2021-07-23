package com.ecom.user.service;

import com.ecom.user.client.SessionClient;
import com.ecom.user.domain.Address;
import com.ecom.user.domain.User;
import com.ecom.user.dto.SessionDto;
import com.ecom.user.dto.UserDto;
import com.ecom.user.mapper.session.SessionMapper;
import com.ecom.user.mapper.user.UserMapper;
import com.ecom.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapperImpl;
	private final SessionMapper sessionMapperImpl;
	private final SessionClient sessionClient;

	@Transactional
	public void saveUser(UserDto userDto)  {
	    try {
			User user = userMapperImpl.userDtoToUser(userDto);
			userRepository.save(user);
			Long userId = userRepository.findByUsername(userDto.getUsername()).getUserId();
			userDto.setUserId(userId);
			SessionDto sessionDto = sessionMapperImpl.userDtoToSessionDto(userDto);
			Boolean isRegistered = sessionClient.registerSessionService(sessionDto);
			log.info(isRegistered.toString());
		} catch (Exception e){
	    	log.error(e.getMessage() + e.getCause());
		}
	}

	@Transactional
	public void saveAddress(Address address, Long userId){
		User user = userRepository.findById(userId).get();
		List<Address> addressList = user.getAddressList();
		addressList.add(address);
		user.setAddressList(addressList);
		userRepository.save(user);
	}

	public Iterable<Address> getUserAddress(Long userId){
		return userRepository.findByUserId(userId).getAddressList();
	}

	public Iterable<User> getUsers(){
		return userRepository.findAll();
	}
}
