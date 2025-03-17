package tech.wvs.movieflix.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tech.wvs.movieflix.entity.User;

import java.time.Instant;

@Component
public class TokenService {

    @Value("${movieflix.security.jwt.secret}")
    private String secret;

    public String generateToken(User user) {

        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("userId", user.getId())
                .withClaim("name", user.getName())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .withIssuedAt(Instant.now())
                .withIssuer("MovieFlix API")
                .sign(algorithm);
    }
}
