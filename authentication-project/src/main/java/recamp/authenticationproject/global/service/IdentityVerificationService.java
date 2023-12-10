package recamp.authenticationproject.global.service;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import recamp.authenticationproject.global.dto.CodeDto;
import recamp.authenticationproject.global.dto.MessageDto;

@Validated
public interface IdentityVerificationService {

    void sendPhoneValidation(@Valid MessageDto messageDto);

    void codeValidation(@Valid CodeDto codeDto);
}
