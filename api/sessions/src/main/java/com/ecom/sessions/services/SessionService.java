package com.ecom.sessions.services;

import com.ecom.sessions.config.PasswordConfig;
import com.ecom.sessions.domains.Session;
import com.ecom.sessions.dtos.AuthDto;
import com.ecom.sessions.dtos.AuthenticationDto;
import com.ecom.sessions.dtos.SessionDto;
import com.ecom.sessions.jwts.JwtResponse;
import com.ecom.sessions.jwts.JwtTokenGenerator;
import com.ecom.sessions.mappers.SessionMapper;
import com.ecom.sessions.repos.SessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SessionService {

    private final PasswordConfig passwordConfig;

    private final JwtTokenGenerator jwtTokenGenerator;
    private final SessionRepository sessionRepository;

    private final SessionMapper sessionMapperImpl;

    public Boolean createSessionService(SessionDto sessionDto) {
        try {
            sessionDto.setPassword(passwordConfig.encode(sessionDto.getPassword()));
            sessionDto.setUserRole("USER");
            sessionRepository.save(sessionMapperImpl.sessionDtoToAuthentication(sessionDto));
            return true;
        } catch (Exception e){
            return false;
        }

    }

    public JwtResponse authenticateUser(AuthenticationDto authenticationDto) {

        Session session = sessionRepository.findByUsername(authenticationDto.getUsername());
        boolean isAuthenticated = passwordConfig.match(authenticationDto.getPassword(), session.getPassword());

        if (!isAuthenticated)
            return null;

        String refreshToken = jwtTokenGenerator.generateRefreshToken(session.getUsername(), "USER");
        String accessToken = jwtTokenGenerator.generateAccessToken(session.getUsername(), "USER");

        session.setRefreshToken(refreshToken);
        session.setCurrentAccessToken(accessToken);
        sessionRepository.save(session);

        return new JwtResponse(session.getUserId(), session.getUsername(), accessToken, refreshToken);
    }

    public Boolean isAuthorizedUser(AuthDto authDto){
        String auth = authDto.getAccessToken();
        auth = auth.replace("[", "");
        auth = auth.replace("]", "");
        Optional<Session> session = sessionRepository.findByCurrentAccessToken(auth);
        return session.isPresent();
    }

}