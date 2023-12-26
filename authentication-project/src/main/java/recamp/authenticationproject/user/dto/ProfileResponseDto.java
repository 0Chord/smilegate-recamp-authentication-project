package recamp.authenticationproject.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProfileResponseDto {
    private String email;
    private String name;
    private String phone;
    private String image;

    @Builder
    public ProfileResponseDto(String email, String name, String phone, String image) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.image = image;
    }
}
