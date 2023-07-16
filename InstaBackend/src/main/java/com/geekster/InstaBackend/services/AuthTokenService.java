package com.geekster.InstaBackend.services;

import com.geekster.InstaBackend.models.AuthenticationToken;
import com.geekster.InstaBackend.models.User;
import com.geekster.InstaBackend.repositories.IAuthTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthTokenService {
    @Autowired
    private IAuthTokenRepo authTokenRepo;

    public void addToken(AuthenticationToken token) {
        authTokenRepo.save(token);
    }

    public AuthenticationToken getTokenByUser(User existingUser) {
        return authTokenRepo.findByUser(existingUser);
    }

    public void removeToken(String tokenValue) {
        AuthenticationToken existingToken = authTokenRepo.findByTokenName(tokenValue);
        authTokenRepo.delete(existingToken);
    }
}
