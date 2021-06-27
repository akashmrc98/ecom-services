package com.ecom.authentication.service;

import com.ecom.authentication.config.PasswordConfig;
import com.ecom.authentication.dto.AuthDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.ecom.authentication.domain.Session;
import com.ecom.authentication.dto.SessionDto;
import com.ecom.authentication.jwt.JwtResponse;
import com.ecom.authentication.jwt.JwtTokenGenerator;
import com.ecom.authentication.dto.AuthenticationDto;
import com.ecom.authentication.mapper.session.SessionMapper;
import com.ecom.authentication.repository.SessionRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SessionService {

    private static final Logger logger = LoggerFactory.getLogger(SessionService.class);

    private final PasswordConfig passwordConfig;

    private final JwtTokenGenerator jwtTokenGenerator;
    private final SessionRepository sessionRepository;

    private final SessionMapper sessionMapperImpl;

    public void createSessionService(SessionDto sessionDto) {
        logger.info("initiating the session for user.");
        logger.info(sessionDto.getUsername());
        sessionDto.setPassword(passwordConfig.encode(sessionDto.getPassword()));
        sessionRepository.save(sessionMapperImpl.sessionDtoToAuthentication(sessionDto));
        logger.info("initiating the session for user completed.");
    }

    public JwtResponse authenticateUser(AuthenticationDto authenticationDto) {

        Session session = sessionRepository.findByUsername(authenticationDto.getUsername());
        boolean isAuthenticated = passwordConfig.match(authenticationDto.getPassword(), session.getPassword());

        logger.info(session.getPassword());
        logger.info(passwordConfig.encode(authenticationDto.getPassword()));

        if (!isAuthenticated)
            return null;

        String refreshToken = jwtTokenGenerator.generateRefreshToken(session.getUsername(), session.getUserRole());
        String accessToken = jwtTokenGenerator.generateAccessToken(session.getUsername(), session.getUserRole());

        session.setRefreshToken(refreshToken);
        session.setCurrentAccessToken(accessToken);
        sessionRepository.save(session);

        return new JwtResponse(session.getUserId(), session.getUsername(), accessToken, refreshToken);
    }

    public Boolean isAuthorizedUser(AuthDto authDto){
        logger.info(authDto.getAccessToken());
        String auth = authDto.getAccessToken();
        auth = auth.replace("[", "");
        auth = auth.replace("]", "");
        logger.info(auth);
        Optional<Session> session = sessionRepository.findByCurrentAccessToken(auth);
        return session.isPresent();
    }

}