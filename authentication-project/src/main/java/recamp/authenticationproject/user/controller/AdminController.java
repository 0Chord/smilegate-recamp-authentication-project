package recamp.authenticationproject.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import recamp.authenticationproject.global.dto.DeleteUserDto;
import recamp.authenticationproject.global.dto.MembersResponseDto;
import recamp.authenticationproject.global.dto.SuspendUserDto;
import recamp.authenticationproject.user.service.AdminService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/find/user")
    public ResponseEntity<MembersResponseDto> findAll() {
        MembersResponseDto result = adminService.findMembers();
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/suspend/user")
    public ResponseEntity<String> suspendUser(@RequestBody @Validated SuspendUserDto suspendUserDto) {
        adminService.updateSuspendAt(suspendUserDto);
        return ResponseEntity.ok().body("SUCCESS");
    }

    @PostMapping("/delete/user")
    public ResponseEntity<String> deleteUser(@RequestBody @Validated DeleteUserDto deleteUserDto) {
        Long userId = deleteUserDto.getUserId();
        adminService.deleteMember(userId);
        return ResponseEntity.ok().body("SUCCESS");
    }
}
