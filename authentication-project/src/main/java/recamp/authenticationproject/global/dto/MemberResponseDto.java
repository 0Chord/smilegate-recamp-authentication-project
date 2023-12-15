package recamp.authenticationproject.global.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponseDto {

    private Long userId;
    private String email;
    private String name;
    private String phone;

    private MemberResponseDto() {
        throw new IllegalStateException();
    }

    @Builder
    public MemberResponseDto(Long userId, String email, String name, String phone) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.phone = phone;
    }
}
