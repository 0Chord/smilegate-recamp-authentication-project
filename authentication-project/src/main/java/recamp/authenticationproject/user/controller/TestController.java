package recamp.authenticationproject.user.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import recamp.authenticationproject.global.dto.CodeDto;
import recamp.authenticationproject.global.dto.LoginDto;
import recamp.authenticationproject.global.dto.MemberDto;
import recamp.authenticationproject.global.dto.MessageDto;
import recamp.authenticationproject.global.service.IdentityVerificationService;
import recamp.authenticationproject.global.service.LoginService;
import recamp.authenticationproject.user.service.MemberService;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final IdentityVerificationService identityVerificationService;
    private final MemberService memberService;
    private final LoginService loginService;

    @PostMapping("/test/test")
    public String test(@RequestBody @Validated MessageDto messageDto) {
        identityVerificationService.sendPhoneValidation(messageDto);
        return "ok";
    }

    @PostMapping("/test/validated")
    public String test(@RequestBody @Validated CodeDto codeDto) {
        identityVerificationService.codeValidation(codeDto);
        return "ok";
    }

    @PostMapping("/test/join")
    public String join(@RequestBody @Validated MemberDto memberDto) {
        memberService.save(memberDto);
        return "ok";
    }

    @PostMapping("/test/login")
    public ResponseEntity<String> login(@RequestBody @Validated LoginDto loginDto) {
        List<String> tokens = loginService.login(loginDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("accessToken", tokens.get(0));
        headers.add("refreshToken", tokens.get(1));
        return ResponseEntity.ok().headers(headers).body("SUCCESS");
    }

    @GetMapping("/user")
    public String user() {
        return "ok";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
