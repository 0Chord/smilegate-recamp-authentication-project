package recamp.authenticationproject.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
public class PersonalInformation {

    @Email
    @Column(unique = true)
    @NotBlank(message = "이메일 입력은 필수입니다")
    private String email;
    @NotBlank(message = "이름 입력은 필수입니다")
    private String name;
    @Column(unique = true)
    @NotBlank(message = "전화번호 입력은 필수입니다")
    private String phone;

    private PersonalInformation(String email, String name, String phone) {
        this.email = email;
        this.name = name;
        this.phone = phone;
    }

    public static PersonalInformation make(String email, String name, String phone) {
        return new PersonalInformation(email, name, phone);
    }
}
