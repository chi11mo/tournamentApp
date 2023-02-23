package com.chillmo.developer.tournamentApp.user.service;


import com.chillmo.developer.tournamentApp.user.domain.Token;
import com.chillmo.developer.tournamentApp.user.domain.User;
import com.chillmo.developer.tournamentApp.user.repository.TokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
@AllArgsConstructor
public class TokenServiceImpl implements TokenService {
    /**
     * Public field that gives a value for expiring the token.
     */
    @SuppressWarnings("PMD.FinalFieldCouldBeStatic")
    public static final int expiredAtUpdate = 20;
    private final TokenRepository tokenRepository;

    /**
     * Generates a new authentication token for the specified user.
     *
     * @param user the user for whom the token is generated
     * @return the newly generated authentication token
     */
    @Override
    public Token generateToken(final User user) {
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(expiredAtUpdate);
        String tokenContent = UUID.randomUUID().toString();
        while (tokenRepository.findTokenByTokenContent(tokenContent) != null) {
            tokenContent = UUID.randomUUID().toString();
        }
        Token token = new Token(tokenContent, LocalDateTime.now(), expiresAt, null, user);
        return tokenRepository.save(token);
    }

    @Override
    public Token findTokenByContent(final String content) {
        return tokenRepository.findTokenByTokenContent(content);
    }

    @Override
    public void increaseExpiredTime(final Token token) {
        token.setExpiresAt(LocalDateTime.now().plusMinutes(expiredAtUpdate));
        tokenRepository.save(token);
    }


    @Override
    public User getUserByToken(final String tokenContent) {
        return tokenRepository.findTokenByTokenContent(tokenContent).getUser();
    }
}


