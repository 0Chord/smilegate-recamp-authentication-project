package recamp.authenticationproject.global.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import recamp.authenticationproject.global.dto.JwtDto;
import recamp.authenticationproject.global.exception.UnauthorizedAccessException;

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
        return Jwts.builder()
                .setSubject(Long.toString(jwtDto.getUserId()))
                .setAudience(jwtDto.getRole())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET.getBytes())
                .compact();
    }

    public String issueRefreshToken(JwtDto jwtDto) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(Long.toString(jwtDto.getUserId()))
                .setAudience(jwtDto.getRole())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET.getBytes())
                .compact();
    }

    private void validateRole(String bearerToken, String targetRole) {
        try {
            String accessToken = extractToken(bearerToken);
            Jws<Claims> claims = parseClaims(accessToken);
            validateRole(claims, targetRole);
            checkExpiration(claims);
        } catch (Exception e) {
            throw new UnauthorizedAccessException(e);
        }
    }

    private String extractToken(String bearerToken) {
        return bearerToken.split(" ")[1];
    }

    private Jws<Claims> parseClaims(String accessToken) {
        return Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(accessToken);
    }

    private void validateRole(Jws<Claims> claims, String targetRole) {
        String role = claims.getBody().getAudience();
        if (!role.equals(targetRole)) {
            throw new UnauthorizedAccessException("Role 문제");
        }
    }

    private void checkExpiration(Jws<Claims> claims) {
        Date expiration = claims.getBody().getExpiration();
        if (expiration.before(new Date())) {
            throw new UnauthorizedAccessException("시간 만료");
        }
    }

    public String getUserPk(String bearerToken) {
        String token = extractToken(bearerToken);
        try {
            return Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(token).getBody().getSubject();
        } catch (Exception e) {
            throw new UnauthorizedAccessException();
        }
    }

    public void validationUser(String bearerToken) {
        validateRole(bearerToken, "ROLE_USER");
    }

    public void validationAdmin(String bearerToken) {
        validateRole(bearerToken, "ROLE_ADMIN");
    }

}
