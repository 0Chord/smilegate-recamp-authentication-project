package recamp.authenticationproject.global.service;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import recamp.authenticationproject.global.dto.CodeDto;
import recamp.authenticationproject.global.dto.MessageDto;
import recamp.authenticationproject.global.dto.RefreshTokenDto;

@Validated
public interface IdentityVerificationService {

    void sendPhoneValidation(@Valid MessageDto messageDto);

    void codeValidation(@Valid CodeDto codeDto);

    String refreshTokenValidation(@Valid String token);
}
