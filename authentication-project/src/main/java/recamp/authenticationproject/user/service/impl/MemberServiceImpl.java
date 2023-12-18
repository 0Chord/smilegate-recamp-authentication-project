package recamp.authenticationproject.user.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import recamp.authenticationproject.global.dto.MemberDto;
import recamp.authenticationproject.global.utility.Validator;
import recamp.authenticationproject.user.domain.Member;
import recamp.authenticationproject.user.domain.PersonalInformation;
import recamp.authenticationproject.user.domain.Role;
import recamp.authenticationproject.user.repository.MemberRepository;
import recamp.authenticationproject.user.service.MemberService;

@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;

    private static Role getRole(MemberDto memberDto) {
        if (memberDto.getRole().equals("USER")) {
            return Role.USER;
        }
        return Role.ADMIN;
    }

    @Override
    public void save(MemberDto memberDto) {
        Validator.comparePasswords(memberDto.getPassword(), memberDto.getValidationPassword());
        PersonalInformation information = PersonalInformation.make(memberDto.getEmail(), memberDto.getName(),
                memberDto.getPhone());
        String encodingPassword = encoder.encode(memberDto.getPassword());
        Role role = getRole(memberDto);
        Member member = Member.builder()
                .information(information)
                .password(encodingPassword)
                .role(role)
                .verified(memberDto.isVerified())
                .build();
        memberRepository.save(member);
    }

    @Override
    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Member findMemberByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow();
    }

    @Override
    public boolean existsEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

}
