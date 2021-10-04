package com.visionboard.web.service;

import com.visionboard.data.model.RefreshToken;
import com.visionboard.data.repository.RefreshTokenRepository;
import com.visionboard.exceptions.TokenRefreshException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository repository;

    public Optional<RefreshToken> findByToken(String refreshToken){
        return repository.findByToken(refreshToken);
    }

    public RefreshToken save(RefreshToken refreshToken){
        return repository.save(refreshToken);
    }

    public RefreshToken creatRefreshToken(){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setExpiryDate(Instant.now().plusMillis(3_600_000));
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setRefreshCount(0L);
        return refreshToken;
    }

    public void verifyExpiration(RefreshToken token){
        if (token.getExpiryDate().compareTo(Instant.now()) < 0){
            throw new TokenRefreshException(token.getToken(), "Expired Session. Please issue a new request");
        }
    }

    public void deleteById(String id){
        repository.deleteById(id);
    }

    public void increaseCount(RefreshToken refreshToken){
        refreshToken.incrementRefreshCount();
        save(refreshToken);
    }
}
