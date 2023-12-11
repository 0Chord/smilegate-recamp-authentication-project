package recamp.authenticationproject.global.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import recamp.authenticationproject.global.dto.CodeDto;
import recamp.authenticationproject.global.dto.JwtDto;
import recamp.authenticationproject.global.dto.MessageDto;
import recamp.authenticationproject.global.dto.RefreshTokenDto;
import recamp.authenticationproject.global.redis.Phone;
import recamp.authenticationproject.global.redis.RefreshToken;
import recamp.authenticationproject.global.service.IdentityVerificationService;
import recamp.authenticationproject.global.service.MessageService;
import recamp.authenticationproject.global.service.PhoneService;
import recamp.authenticationproject.global.service.RefreshTokenService;
import recamp.authenticationproject.global.utility.JwtUtils;
import recamp.authenticationproject.global.utility.ValueGenerator;
import recamp.authenticationproject.user.domain.Member;
import recamp.authenticationproject.user.service.MemberService;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IdentityVerificationServiceImpl implements IdentityVerificationService {
    private final MessageService messageService;
    private final PhoneService phoneService;

    private final RefreshTokenService refreshTokenService;
    private final JwtUtils jwtUtils;
    private final MemberService memberService;

    @Override
    @Transactional
    public void sendPhoneValidation(MessageDto messageDto) {
        String code = ValueGenerator.messageCertification();
        messageService.send(messageDto.getPhone(), code);
        phoneService.save(messageDto.getPhone(), code);
    }

    @Override
    public void codeValidation(CodeDto codeDto) {
        Phone phone = phoneService.findById(codeDto.getNumber());
        phone.compare(codeDto.getCode());
    }


    @Override
    @Transactional
    public String refreshTokenValidation(String token) {
        Long userId = Long.parseLong(jwtUtils.getUserPk(token));
        Member member = memberService.findById(userId);
        RefreshTokenDto refreshTokenDto = RefreshTokenDto.make(userId, token, 15,
                member.convertRole());
        RefreshToken refreshToken = refreshTokenService.findById(refreshTokenDto.getId());
        refreshToken.compare(refreshTokenDto.getToken());
        JwtDto jwtDto = JwtDto.make(refreshTokenDto.getId(), refreshTokenDto.getRole());
        String accessToken = jwtUtils.issueAccessToken(jwtDto);
        String issueRefreshToken = jwtUtils.issueRefreshToken(jwtDto);
        RefreshTokenDto tokenDto = RefreshTokenDto.make(refreshTokenDto.getId(), issueRefreshToken,
                refreshToken.getExpiration(),
                refreshTokenDto.getRole());
        refreshTokenService.register(tokenDto);
        return accessToken;
    }
}
