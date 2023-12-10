package recamp.authenticationproject.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import recamp.authenticationproject.global.dto.CodeDto;
import recamp.authenticationproject.global.dto.MemberDto;
import recamp.authenticationproject.global.dto.MessageDto;
import recamp.authenticationproject.global.service.IdentityVerificationService;
import recamp.authenticationproject.user.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final IdentityVerificationService identityVerificationService;
    private final MemberService memberService;
    @PostMapping("/test")
    public String test(@RequestBody @Validated MessageDto messageDto) {
        identityVerificationService.sendPhoneValidation(messageDto);
        return "ok";
    }

    @PostMapping("/validated")
    public String test(@RequestBody @Validated CodeDto codeDto) {
        identityVerificationService.codeValidation(codeDto);
        return "ok";
    }

    @PostMapping("/join")
    public String join(@RequestBody @Validated MemberDto memberDto) {
        memberService.save(memberDto);
        return "ok";
    }
}
