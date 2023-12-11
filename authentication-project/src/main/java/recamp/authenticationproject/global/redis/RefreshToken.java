package recamp.authenticationproject.global.redis;

import jakarta.validation.constraints.NotBlank;
import java.util.concurrent.TimeUnit;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import recamp.authenticationproject.global.exception.UnauthorizedAccessException;

@Getter
@ToString
@RedisHash("refreshToken")
public class RefreshToken {

    @Id
    private Long id;

    @NotBlank
    private final String token;

    private final String role;

    @TimeToLive(unit = TimeUnit.DAYS)
    private final Integer expiration;

    @Builder
    public RefreshToken(Long id, String token, String role, Integer expiration) {
        this.id = id;
        this.token = token;
        this.expiration = expiration;
        this.role = role;
    }

    public void compare(String refreshToken) {
        if (!token.equals(refreshToken)) {
            throw new UnauthorizedAccessException();
        }
    }

}
