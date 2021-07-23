package com.ecom.user.controller;

import lombok.extern.slf4j.Slf4j;
import com.ecom.user.domain.User;
import com.ecom.user.dto.UserDto;
import com.ecom.user.domain.Address;
import lombok.RequiredArgsConstructor;
import com.ecom.user.service.UserService;
import org.springframework.http.HttpStatus;
import com.ecom.user.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin(value = "/**")
public class UserController {
    private final UserService userService;
    private final AddressService addressService;


    @PostMapping
    public void saveUser(@RequestBody UserDto userDto) {
        log.info("save user process initiated.");
        userService.saveUser(userDto);
        log.info("save user process completed.");
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
