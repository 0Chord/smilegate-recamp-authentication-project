package recamp.authenticationproject.global.redis;

import java.util.concurrent.TimeUnit;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@RedisHash("phone")
public class Phone {

    @Id
    private String number;
    private String token;

    @TimeToLive(unit = TimeUnit.MINUTES)
    private Integer expiration;

    @Builder
    public Phone(String number, String token, Integer expiration) {
        this.number = number;
        this.token = token;
        this.expiration = expiration;
    }
}
