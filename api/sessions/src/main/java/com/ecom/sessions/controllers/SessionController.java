package com.ecom.sessions.controllers;

import com.ecom.sessions.dtos.AuthDto;
import com.ecom.sessions.dtos.AuthenticationDto;
import com.ecom.sessions.dtos.SessionDto;
import com.ecom.sessions.jwts.JwtResponse;
import com.ecom.sessions.mappers.SessionMapper;
import com.ecom.sessions.services.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sessions")
@CrossOrigin(value = "/**")
public class SessionController {
    private final SessionService sessionService;
    private final SessionMapper sessionMapperImpl;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Boolean createSessionStoreForUser(@RequestBody SessionDto sessionDto) {
        return sessionService.createSessionService(sessionDto);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody AuthenticationDto authenticationDto) {
        return new ResponseEntity<JwtResponse>(sessionService.authenticateUser(authenticationDto), HttpStatus.OK);
    }

    @PostMapping("/authorize")
    public ResponseEntity<Boolean> authorizeUser(@RequestBody AuthDto authDto) {
        return new ResponseEntity<Boolean>(sessionService.isAuthorizedUser(authDto), HttpStatus.OK);
    }

}
