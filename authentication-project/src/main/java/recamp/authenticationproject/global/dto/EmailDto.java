package recamp.authenticationproject.global.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class EmailDto {

    @Email
    @NotBlank
    private String email;
}
