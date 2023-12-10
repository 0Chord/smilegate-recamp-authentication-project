package recamp.authenticationproject.global.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import recamp.authenticationproject.global.dto.CodeDto;
import recamp.authenticationproject.global.dto.MessageDto;
import recamp.authenticationproject.global.exception.IllegalCodeException;
import recamp.authenticationproject.global.redis.Phone;
import recamp.authenticationproject.global.service.IdentityVerificationService;
import recamp.authenticationproject.global.service.MessageService;
import recamp.authenticationproject.global.service.PhoneService;
import recamp.authenticationproject.global.utility.ValueGenerator;

@Slf4j
@RequiredArgsConstructor
public class IdentityVerificationServiceImpl implements IdentityVerificationService {

    private static final String message = "잘못된 인증번호입니다. 다시 시도해주세요";
    private final MessageService messageService;
    private final PhoneService phoneService;

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
        if (!phone.getToken().equals(codeDto.getCode())) {
            throw new IllegalCodeException(message);
        }
    }
}
