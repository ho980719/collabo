package com.ho.collabo.common.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Slf4j
public class JwtService {
    private static String JWT_SECRET_KEY = "collabo";

    // 토큰 유효시간 30분
    private static long EXPIRES_DATE = 30 * 60 * 1000L;

    public static String createToken(Long id) {
        log.info("생성...");
        return JWT.create()
                .withIssuer("collabo")
                .withClaim("memberId", id)
                .withExpiresAt(Instant.ofEpochSecond(EXPIRES_DATE))
                .sign(Algorithm.HMAC256(JWT_SECRET_KEY));
    }

    public void verifyToken() {

    }
}
