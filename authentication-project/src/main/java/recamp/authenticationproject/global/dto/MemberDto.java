package recamp.authenticationproject.global.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import javax.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@ToString
public class MemberDto {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    @Pattern(regexp = "^0[0-9]{1,3}[0-9]{8}$", message = "전화번호에 하이픈이 없어야 합니다")
    private String phone;
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$", message = "비밀번호는 8~16자리수여야 합니다. 영문 소문자, 숫자, 특수문자를 1개 이상 포함해야 합니다.")
    private String password;
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$", message = "비밀번호는 8~16자리수여야 합니다. 영문 소문자, 숫자, 특수문자를 1개 이상 포함해야 합니다.")
    private String validationPassword;
    @NotBlank
    private String role;
    @NotNull
    private boolean verified;

    public void changeRole() {
        if (email.equals("admin@harmony.com")) {
            role = "ADMIN";
        }
    }
}
