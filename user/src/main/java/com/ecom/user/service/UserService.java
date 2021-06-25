package com.ecom.user.service;

import com.ecom.user.domain.Address;
import com.ecom.user.domain.User;
import com.ecom.user.dto.UserDto;
import com.ecom.user.mapper.user.UserMapper;
import com.ecom.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final UserMapper userMapperImpl;

	public Long saveUser(UserDto userDto)  {
		User user = userMapperImpl.userDtoToUser(userDto);
		userRepository.save(user);
		return userRepository.findByUsername(userDto.getUsername()).getUserId();
	}

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
