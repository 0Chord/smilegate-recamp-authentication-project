package recamp.authenticationproject.global.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import recamp.authenticationproject.global.dto.MemberResponseDto;
import recamp.authenticationproject.global.dto.MembersResponseDto;
import recamp.authenticationproject.global.service.ConvertService;
import recamp.authenticationproject.user.domain.Member;
import recamp.authenticationproject.user.service.MemberService;

@RequiredArgsConstructor
public class ConvertServiceImpl implements ConvertService {
    private final MemberService memberService;
    @Override
    public MembersResponseDto membersResponseDto() {
        List<Member> members = memberService.findAll();

        List<MemberResponseDto> memberResponseDtos = members.stream()
                .map(member -> MemberResponseDto.builder()
                        .userId(member.getId())
                        .email(member.getPersonalInformation().getEmail())
                        .name(member.getPersonalInformation().getName())
                        .phone(member.getPersonalInformation().getPhone())
                        .build())
                .toList();

        return MembersResponseDto.make(memberResponseDtos);
    }
}
