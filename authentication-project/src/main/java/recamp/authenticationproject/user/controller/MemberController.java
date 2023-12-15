package recamp.authenticationproject.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import recamp.authenticationproject.global.dto.MembersResponseDto;
import recamp.authenticationproject.global.service.ConvertService;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class MemberController {

    private final ConvertService convertService;

    @GetMapping("/find/role-user")
    public ResponseEntity<MembersResponseDto> findAll() {
        MembersResponseDto result = convertService.membersResponseDto();
        return ResponseEntity.ok().body(result);
    }
}
