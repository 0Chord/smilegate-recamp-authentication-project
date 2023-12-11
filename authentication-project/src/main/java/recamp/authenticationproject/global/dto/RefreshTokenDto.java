package recamp.authenticationproject.global.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RefreshTokenDto {

    @NotBlank
    private final Long id;
    @NotBlank
    private final String token;
    private final Integer expiration;
    @NotBlank
    private final String role;

    private RefreshTokenDto(Long id, String token, Integer expiration, String role) {
        this.id = id;
        this.token = token;
        this.expiration = expiration;
        this.role = role;
    }

    public static RefreshTokenDto make(Long id, String token, Integer expiration, String role) {
        return new RefreshTokenDto(id, token, expiration, role);
    }
}
