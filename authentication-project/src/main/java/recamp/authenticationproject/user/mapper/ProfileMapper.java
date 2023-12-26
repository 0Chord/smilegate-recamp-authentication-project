package recamp.authenticationproject.user.mapper;

import recamp.authenticationproject.user.domain.Member;
import recamp.authenticationproject.user.dto.ProfileResponseDto;

public class ProfileMapper {

    private ProfileMapper() {
        throw new IllegalStateException();
    }

    public static ProfileResponseDto from(Member member) {
        return ProfileResponseDto.builder()
                .email(member.getPersonalInformation().getEmail())
                .phone(member.getPersonalInformation().getPhone())
                .name(member.getPersonalInformation().getName())
                .image(member.getImage())
                .build();
    }
}
