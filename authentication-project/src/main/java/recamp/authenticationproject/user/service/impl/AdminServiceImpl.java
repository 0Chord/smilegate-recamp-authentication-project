package recamp.authenticationproject.user.service.impl;

import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import recamp.authenticationproject.global.dto.MemberResponseDto;
import recamp.authenticationproject.global.dto.MembersResponseDto;
import recamp.authenticationproject.global.dto.SuspendUserDto;
import recamp.authenticationproject.user.domain.Member;
import recamp.authenticationproject.user.domain.UserStatus;
import recamp.authenticationproject.user.service.AdminService;
import recamp.authenticationproject.user.service.MemberService;

@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final MemberService memberService;

    @Override
    public void updateSuspendAt(SuspendUserDto suspendUserDto) {
        Member member = memberService.findById(suspendUserDto.getUserId());
        member.updateSuspendTime(suspendUserDto.getDay());
    }

    @Override
    public MembersResponseDto findMembers() {
        List<Member> members = memberService.findAll();
        List<MemberResponseDto> memberResponseDtos = members.stream()
                .map(member -> MemberResponseDto.builder()
                        .userId(member.getId())
                        .email(member.getPersonalInformation().getEmail())
                        .name(member.getPersonalInformation().getName())
                        .phone(member.getPersonalInformation().getPhone())
                        .suspendAt(member.getSuspendedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .build())
                .toList();

        return MembersResponseDto.make(memberResponseDtos);
    }

    @Override
    public void deleteMember(Long userId) {
        Member member = memberService.findById(userId);
        member.updateUserStatus(UserStatus.DELETE);
    }
}
