package com.ecom.authentication.controller;

import com.ecom.authentication.dto.AuthDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import com.ecom.authentication.dto.SessionDto;
import com.ecom.authentication.jwt.JwtResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ecom.authentication.dto.AuthenticationDto;
import com.ecom.authentication.service.SessionService;
import com.ecom.authentication.mapper.session.SessionMapper;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/sessions")
public class SessionController {
    private final SessionService sessionService;
    private final SessionMapper sessionMapperImpl;
    private static final Logger logger = LoggerFactory.getLogger(SessionController.class);

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createSessionStoreForUser(@RequestBody SessionDto sessionDto) {
        logger.info(sessionDto.getUsername());
        sessionService.createSessionService(sessionDto);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody AuthenticationDto authenticationDto) {
        logger.info(authenticationDto.getUsername());
        return new ResponseEntity<JwtResponse>(sessionService.authenticateUser(authenticationDto), HttpStatus.OK);
    }

    @PostMapping("/authorize")
    public ResponseEntity<Boolean> authorizeUser(@RequestBody AuthDto authDto) {
        return new ResponseEntity<Boolean>(sessionService.isAuthorizedUser(authDto), HttpStatus.OK);
    }

}
