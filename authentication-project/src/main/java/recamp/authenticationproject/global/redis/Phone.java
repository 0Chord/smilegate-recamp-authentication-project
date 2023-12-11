package recamp.authenticationproject.global.redis;

import jakarta.validation.constraints.NotBlank;
import java.util.concurrent.TimeUnit;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import recamp.authenticationproject.global.exception.IllegalCodeException;

@Getter
@RedisHash("phone")
public class Phone {
    private static final String MESSAGE = "잘못된 인증번호입니다. 다시 시도해주세요";
    @Id
    private String number;
    @NotBlank
    private String token;

    @TimeToLive(unit = TimeUnit.MINUTES)
    private Integer expiration;

    @Builder
    public Phone(String number, String token, Integer expiration) {
        this.number = number;
        this.token = token;
        this.expiration = expiration;
    }

    public void compare(String code) {
        if (!token.equals(code)) {
            throw new IllegalCodeException(MESSAGE);
        }
    }

}
