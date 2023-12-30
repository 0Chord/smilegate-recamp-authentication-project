package recamp.authenticationproject.global.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import recamp.authenticationproject.global.dto.EmailDto;
import recamp.authenticationproject.global.dto.LoginDto;
import recamp.authenticationproject.global.service.IdentityVerificationService;
import recamp.authenticationproject.global.service.LoginService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/verified")
@CrossOrigin(origins = "*", allowedHeaders = "*",exposedHeaders = "*")
public class AuthenticationController {
    private final IdentityVerificationService identityVerificationService;
    private final LoginService loginService;

    @PostMapping("/refreshToken/valid")
    public ResponseEntity<String> valid(@RequestHeader(name = "Authorization") String bearerToken) {
        String accessToken = identityVerificationService.refreshTokenValidation(bearerToken);
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
        headers.add("user-id", tokens.get(2));
        return ResponseEntity.ok().headers(headers).body("SUCCESS");
    }

    @PostMapping("/duplication/email")
    public ResponseEntity<String> duplication(@RequestBody @Validated EmailDto emailDto) {
        identityVerificationService.existsEmail(emailDto);
        return ResponseEntity.ok().body("SUCCESS");
    }
}
