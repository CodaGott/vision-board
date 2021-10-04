package com.visionboard.security.jwt;

import com.visionboard.data.model.User;
import com.visionboard.exceptions.InvalidTokenRequestException;
import com.visionboard.security.cache.LoggedOutJwtTokenCache;
import com.visionboard.security.event.OnUserLogoutSuccessEvent;
import com.visionboard.web.service.UserPrincipal;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
@Slf4j
public class JwtProvider {


    @Autowired
    private LoggedOutJwtTokenCache loggedOutJwtTokenCache;

    public String generateJwtToken(Authentication authentication){
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 3600000);

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuer("StackAbuse")
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, "HelloWorld")
                .compact();
    }

    public String generateTokenFromUser(User user){
        Instant expiryDate = Instant.now().plusMillis(3600000);
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuer("Therapex")
                .setId(user.getUserId())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(expiryDate))
                .signWith(SignatureAlgorithm.HS512, "HelloWorld")
                .compact();
    }
    public Date getTokenExpiryFromJwt(String token){
        Claims claims = Jwts.parser()
                .setSigningKey("HelloWorld")
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }

    public boolean validateJwtToken(String authToken){
        try{
            Jwts.parser().setSigningKey("HelloWorld").parseClaimsJws(authToken);
            validateTokenIsNotForALoggedOutDevice(authToken);
            return true;
        }catch (MalformedJwtException e){
            log.error("Invalid JWT token -> Message: {}", e);
        }catch (ExpiredJwtException e){
            log.error("Expired JWT token -> Message: {}", e);
        }catch (UnsupportedJwtException e){
            log.error("Unsupported Jwt token -> Message: {}", e);
        }catch (IllegalArgumentException e){
            log.error("JWT claims string is empty -> Message: {}", e);
        }
        return false;
    }

    private void validateTokenIsNotForALoggedOutDevice(String authToken){
        OnUserLogoutSuccessEvent previouslyLoggedOutEvent = loggedOutJwtTokenCache.getLogoutEventFromToken(authToken);
        if (previouslyLoggedOutEvent != null){
            String userEmail = previouslyLoggedOutEvent.getUserEmail();
            Date logoutEventDate = previouslyLoggedOutEvent.getEventTime();
            String errorMessage = String.format("Token corresponds to an already logged out user [%s] at [%s]. Please login again", userEmail, logoutEventDate);
            throw new InvalidTokenRequestException("JWT", authToken, errorMessage);
        }
    }

    public long getExpiryDuration(){
        return 3600000;
    }

}
