package recamp.authenticationproject.global.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDto {

    @NotBlank
    @Pattern(regexp = "^0[0-9]{1,3}[0-9]{8}$",message = "전화번호에 하이픈이 없어야 합니다")
    private String phone;
}
