package recamp.authenticationproject.global.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

@Getter
@Validated
public class SuspendUserDto {

    @NotNull
    private Long userId;
    @NotNull
    @Min(value = 1)
    private Integer day;
}
