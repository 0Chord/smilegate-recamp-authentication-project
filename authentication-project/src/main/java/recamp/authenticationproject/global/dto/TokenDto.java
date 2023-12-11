package recamp.authenticationproject.global.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class TokenDto {
    @NotBlank
    private String token;
}
