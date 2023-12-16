package recamp.authenticationproject.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import recamp.authenticationproject.global.dto.MembersResponseDto;
import recamp.authenticationproject.global.service.ConvertService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final ConvertService convertService;

    @GetMapping("/find/user")
    public ResponseEntity<MembersResponseDto> findAll() {
        MembersResponseDto result = convertService.membersResponseDto();
        return ResponseEntity.ok().body(result);
    }
}
