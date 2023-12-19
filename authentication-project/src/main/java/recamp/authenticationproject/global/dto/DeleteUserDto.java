package recamp.authenticationproject.global.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DeleteUserDto {

    @NotNull
    private Long userId;
}
