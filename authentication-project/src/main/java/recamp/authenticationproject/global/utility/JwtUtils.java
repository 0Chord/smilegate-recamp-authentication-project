package recamp.authenticationproject.global.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Base64;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import recamp.authenticationproject.global.dto.JwtDto;

@Component
public final class JwtUtils {

    @Value("${jwt.token.accesstoken.expiration}")
    private Long ACCESS_TOKEN_EXPIRATION;
    @Value("${jwt.token.refreshtoken.expiration}")
    private Long REFRESH_TOKEN_EXPIRATION;
    @Value("${jwt.token.value}")
    private String SECRET;


    public String issueAccessToken(JwtDto jwtDto) {
        Date now = new Date();
        SECRET = Base64.getEncoder().encodeToString(SECRET.getBytes());
        return Jwts.builder()
                .setSubject(jwtDto.getName())
                .setSubject(jwtDto.getRole())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public String issueRefreshToken(JwtDto jwtDto) {
        Date now = new Date();
        SECRET = Base64.getEncoder().encodeToString(SECRET.getBytes());
        return Jwts.builder()
                .setSubject(jwtDto.getName())
                .setSubject(jwtDto.getRole())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }
}
