package recamp.authenticationproject.global.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import recamp.authenticationproject.global.dto.LoginDto;
import recamp.authenticationproject.global.dto.TokenDto;
import recamp.authenticationproject.global.service.IdentityVerificationService;
import recamp.authenticationproject.global.service.LoginService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/verified")
public class AuthenticationController {
    private final IdentityVerificationService identityVerificationService;
    private final LoginService loginService;

    @PostMapping("/refreshToken/valid")
    public ResponseEntity<String> valid(@RequestBody @Validated TokenDto refreshToken) {
        String accessToken = identityVerificationService.refreshTokenValidation(refreshToken.getToken());
        HttpHeaders headers = new HttpHeaders();
        headers.add("access-token", accessToken);
        return ResponseEntity.ok().headers(headers).body("OK");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Validated LoginDto loginDto) {
        List<String> tokens = loginService.login(loginDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("access-token", tokens.get(0));
        headers.add("refresh-token", tokens.get(1));
        return ResponseEntity.ok().headers(headers).body("SUCCESS");
    }
}
