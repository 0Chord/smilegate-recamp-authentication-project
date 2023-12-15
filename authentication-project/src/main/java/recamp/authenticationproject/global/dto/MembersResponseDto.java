package recamp.authenticationproject.global.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class MembersResponseDto {

    private List<MemberResponseDto> members;

    private MembersResponseDto(List<MemberResponseDto> members) {
        this.members = members;
    }

    public static MembersResponseDto make(List<MemberResponseDto> members) {
        return new MembersResponseDto(members);
    }
}
