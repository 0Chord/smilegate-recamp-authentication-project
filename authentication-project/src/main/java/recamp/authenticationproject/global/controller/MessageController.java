package recamp.authenticationproject.global.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import recamp.authenticationproject.global.dto.CodeDto;
import recamp.authenticationproject.global.dto.MessageDto;
import recamp.authenticationproject.global.service.IdentityVerificationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/message")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MessageController {

    private final IdentityVerificationService identityVerificationService;

    @PostMapping("/send")
    public String send(@RequestBody @Validated MessageDto messageDto) {
        identityVerificationService.sendPhoneValidation(messageDto);
        return "SUCCESS";
    }

    @PostMapping("/validated")
    public String validatePhone(@RequestBody @Validated CodeDto codeDto) {
        identityVerificationService.codeValidation(codeDto);
        return "SUCCESS";
    }
}
