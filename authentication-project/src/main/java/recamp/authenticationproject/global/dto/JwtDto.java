package recamp.authenticationproject.global.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class JwtDto {
    @NotBlank
    private Long userId;
    @NotBlank
    private String role;

    private JwtDto() {
        throw new IllegalStateException();
    }

    private JwtDto(Long userId, String role) {
        this.userId = userId;
        this.role = role;
    }

    public static JwtDto make(Long userId, String role) {
        return new JwtDto(userId, role);
    }

}
