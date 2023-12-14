package recamp.authenticationproject.global.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import recamp.authenticationproject.global.dto.JwtDto;
import recamp.authenticationproject.global.dto.LoginDto;
import recamp.authenticationproject.global.dto.RefreshTokenDto;
import recamp.authenticationproject.global.exception.IllegalPasswordException;
import recamp.authenticationproject.global.service.LoginService;
import recamp.authenticationproject.global.service.RefreshTokenService;
import recamp.authenticationproject.global.utility.JwtUtils;
import recamp.authenticationproject.user.domain.Member;
import recamp.authenticationproject.user.service.MemberService;

@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginServiceImpl implements LoginService {

    private static final String MESSAGE = "비밀번호가 일치하지 않습니다";
    private final BCryptPasswordEncoder encoder;
    private final MemberService memberService;
    private final JwtUtils jwtUtils;
    private final RefreshTokenService refreshTokenService;

    @Override
    @Transactional
    public List<String> login(LoginDto loginDto) {
        Member member = memberService.findMemberByEmail(loginDto.getEmail());
        member.checkSuspendTime();
        member.checkVerified();
        comparePassword(loginDto, member);
        JwtDto jwtDto = JwtDto.make(member.getId(), member.convertRole());
        String accessToken = jwtUtils.issueAccessToken(jwtDto);
        String refreshToken = jwtUtils.issueRefreshToken(jwtDto);
        member.updateLastedAccessAt();
        makeRefreshToken(member, refreshToken);
        return List.of(accessToken, refreshToken);
    }

    private void makeRefreshToken(Member member, String refreshToken) {
        RefreshTokenDto refreshTokenDto = RefreshTokenDto.make(member.getId(), refreshToken, 15, member.convertRole());
        refreshTokenService.register(refreshTokenDto);
    }


    private void comparePassword(LoginDto loginDto, Member member) {
        if (!encoder.matches(loginDto.getPassword(), member.getPassword())) {
            throw new IllegalPasswordException(MESSAGE);
        }
    }
}
