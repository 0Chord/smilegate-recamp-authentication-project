package recamp.authenticationproject.global.service;

import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import recamp.authenticationproject.global.dto.CodeDto;
import recamp.authenticationproject.global.dto.EmailDto;
import recamp.authenticationproject.global.dto.MessageDto;
import recamp.authenticationproject.global.dto.RefreshTokenDto;

@Validated
@Transactional(readOnly = true)
public interface IdentityVerificationService {

    @Transactional
    void sendPhoneValidation(@Valid MessageDto messageDto);

    @Transactional
    void codeValidation(@Valid CodeDto codeDto);

    @Transactional
    String refreshTokenValidation(@Valid String token);

    void existsEmail(@Valid EmailDto emailDto);
}
