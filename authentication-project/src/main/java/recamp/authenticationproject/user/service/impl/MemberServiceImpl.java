package recamp.authenticationproject.user.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import recamp.authenticationproject.global.dto.MemberDto;
import recamp.authenticationproject.global.exception.IllegalPasswordException;
import recamp.authenticationproject.user.domain.Member;
import recamp.authenticationproject.user.domain.PersonalInformation;
import recamp.authenticationproject.user.domain.Role;
import recamp.authenticationproject.user.repository.MemberRepository;
import recamp.authenticationproject.user.service.MemberService;

@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private static final String message = "비밀번호가 일치하지 않습니다. 다시 시도 부탁드립니다";
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
        comparePasswords(memberDto.getPassword(), memberDto.getValidationPassword());
        PersonalInformation information = PersonalInformation.make(memberDto.getEmail(), memberDto.getName(),
                memberDto.getPhone());
        String encodingPassword = encoder.encode(memberDto.getPassword());
        Role role = getRole(memberDto);
        Member member = Member.builder()
                .information(information)
                .password(encodingPassword)
                .role(role).build();
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

    private void comparePasswords(String password, String validationPassword) {
        if (!password.equals(validationPassword)) {
            throw new IllegalPasswordException(message);
        }
    }
}
