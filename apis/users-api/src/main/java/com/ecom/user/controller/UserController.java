package com.ecom.user.controller;

import com.ecom.user.client.*;
import com.ecom.user.domain.Address;
import com.ecom.user.domain.User;
import com.ecom.user.dto.RegisterDto;
import com.ecom.user.dto.SessionDto;
import com.ecom.user.dto.UserDto;
import com.ecom.user.mapper.session.SessionMapper;
import com.ecom.user.service.AddressService;
import com.ecom.user.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final AddressService addressService;

    private final SessionMapper sessionMapperImpl;

    private final SessionClient sessionClient;
    private final CartClient cartClient;
    private final WishlistClient wishlistClient;
    private final OrderClient orderClient;
    private final ReviewClient reviewClient;

    @PostMapping
    public void saveUser(@RequestBody UserDto userDto) {
        logger.info("save user process initiated.");
        Long userId = userService.saveUser(userDto);
        userDto.setUserId(userId);

        SessionDto sessionDto = sessionMapperImpl.userDtoToSessionDto(userDto);
        sessionClient.createSessionServiceForUser(sessionDto);

        RegisterDto registerDto = new RegisterDto();
        registerDto.setUserId(userId);
        logger.info(registerDto.getUserId().toString());

        cartClient.createCartServiceForUser(registerDto);
        wishlistClient.createWishListServiceForUser(registerDto);
        orderClient.createOrderServiceForUser(registerDto);
        reviewClient.createReviewServiceForUser(registerDto);
        logger.info("save user process completed.");
    }

    @GetMapping
    public ResponseEntity<Iterable<User>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @PostMapping("/{userId}/address")
    public void createAddress(
            @RequestBody Address address,
            @PathVariable("userId") Long userId
    ) {
        Address savedAddress = addressService.createAddress(address);
        userService.saveAddress(savedAddress, userId);
    }

    @GetMapping("/{userId}/address")
    public ResponseEntity<Iterable<Address>> getUserAddress(@PathVariable("userId") Long userId) {
        Iterable<Address> addresses = userService.getUserAddress(userId);
        return new ResponseEntity<>(userService.getUserAddress(userId),HttpStatus.OK);
    }

    @PutMapping("/addresses/{addressId}")
    public ResponseEntity<Address> getAddress(
            @PathVariable("addressId") Long id,
            @RequestBody Address address
    ) {
        Address updatedAddress = addressService.updateAddress(id, address);
        return new ResponseEntity<Address>(updatedAddress, HttpStatus.OK);
    }

    @DeleteMapping("/addresses/{addressId}")
    @ResponseStatus(HttpStatus.OK)
    public void removeAddress(@PathVariable("addressId") Long id) {
        addressService.removeAddress(id);
    }
}
