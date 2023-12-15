package recamp.authenticationproject.global.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import recamp.authenticationproject.global.dto.CodeDto;
import recamp.authenticationproject.global.dto.EmailDto;
import recamp.authenticationproject.global.dto.JwtDto;
import recamp.authenticationproject.global.dto.MessageDto;
import recamp.authenticationproject.global.dto.RefreshTokenDto;
import recamp.authenticationproject.global.exception.DuplicateEmailException;
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
public class IdentityVerificationServiceImpl implements IdentityVerificationService {
    private static final String EMAIL_MESSAGE = "중복된 회원 이메일입니다. 다른 이메일로 시도 부탁드립니다";

    private final MessageService messageService;
    private final PhoneService phoneService;

    private final RefreshTokenService refreshTokenService;
    private final JwtUtils jwtUtils;
    private final MemberService memberService;

    @Override
    public void sendPhoneValidation(MessageDto messageDto) {
        String code = ValueGenerator.messageCertification();
        messageService.send(messageDto.getPhone(), code);
        phoneService.save(messageDto.getPhone(), code);
    }

    @Override
    public void codeValidation(CodeDto codeDto) {
        Phone phone = phoneService.findById(codeDto.getNumber());
        phone.compare(codeDto.getCode());
        phoneService.delete(phone);
    }


    @Override
    public String refreshTokenValidation(String token) {
        Long userId = Long.parseLong(jwtUtils.getUserPk(token));
        Member member = memberService.findById(userId);
        RefreshTokenDto refreshTokenDto = RefreshTokenDto.make(userId, token, 15,
                member.convertRole());
        RefreshToken refreshToken = refreshTokenService.findById(refreshTokenDto.getId());
        refreshToken.compare(refreshTokenDto.getToken());
        refreshTokenService.deleteToken(refreshToken);
        JwtDto jwtDto = JwtDto.make(refreshTokenDto.getId(), refreshTokenDto.getRole());
        String accessToken = jwtUtils.issueAccessToken(jwtDto);
        String issueRefreshToken = jwtUtils.issueRefreshToken(jwtDto);
        RefreshTokenDto tokenDto = RefreshTokenDto.make(refreshTokenDto.getId(), issueRefreshToken,
                refreshToken.getExpiration(),
                refreshTokenDto.getRole());
        refreshTokenService.register(tokenDto);
        return accessToken;
    }

    public void existsEmail(EmailDto emailDto) {
        String email = emailDto.getEmail();
        if (memberService.existsEmail(email)) {
            throw new DuplicateEmailException(EMAIL_MESSAGE);
        }
    }
}
