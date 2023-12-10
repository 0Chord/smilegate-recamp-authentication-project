package recamp.authenticationproject.global.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import recamp.authenticationproject.global.dto.JwtDto;
import recamp.authenticationproject.global.dto.LoginDto;
import recamp.authenticationproject.global.exception.IllegalPasswordException;
import recamp.authenticationproject.global.exception.SuspendedMemberException;
import recamp.authenticationproject.global.service.LoginService;
import recamp.authenticationproject.global.utility.JwtUtils;
import recamp.authenticationproject.user.domain.Member;
import recamp.authenticationproject.user.service.MemberService;

@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginServiceImpl implements LoginService {

    private static final String MESSAGE = "비밀번호가 일치하지 않습니다";
    private static final String EXCEPTION_MESSAGE = "정지된 회원입니다. 이용이 불가합니다.";
    private final BCryptPasswordEncoder encoder;
    private final MemberService memberService;
    private final JwtUtils jwtUtils;

    @Override
    public List<String> login(LoginDto loginDto) {
        Member member = memberService.findMemberByEmail(loginDto.getEmail());
        isBefore(member);
        comparePassword(loginDto, member);
        JwtDto jwtDto = JwtDto.make(member.getId(), member.getRole(), member.getPersonalInformation().getName());
        String accessToken = jwtUtils.issueAccessToken(jwtDto);
        String refreshToken = jwtUtils.issueRefreshToken(jwtDto);
        return List.of(accessToken, refreshToken);
    }

    private void isBefore(Member member) {
        if (member.getSuspendedAt().isAfter(LocalDateTime.now())) {
            throw new SuspendedMemberException(EXCEPTION_MESSAGE);
        }
    }

    private void comparePassword(LoginDto loginDto, Member member) {
        if (!encoder.matches(loginDto.getPassword(), member.getPassword())) {
            throw new IllegalPasswordException(MESSAGE);
        }
    }
}
