package recamp.authenticationproject.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import recamp.authenticationproject.global.dto.MemberDto;
import recamp.authenticationproject.global.dto.PasswordDto;
import recamp.authenticationproject.user.domain.Member;
import recamp.authenticationproject.user.dto.ProfileResponseDto;
import recamp.authenticationproject.user.mapper.ProfileMapper;
import recamp.authenticationproject.user.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestPart(value = "memberDto") @Validated MemberDto memberDto,
                                       @RequestPart(name = "image", required = false) MultipartFile image) {
        memberService.save(memberDto, image);
        return ResponseEntity.ok().body("SUCCESS");
    }

    @PostMapping("/{userId}/change/password")
    public ResponseEntity<?> change(@PathVariable("userId") Long userId,
                                    @RequestBody @Validated PasswordDto passwordDto) {
        memberService.updatePassword(userId, passwordDto);
        return ResponseEntity.ok().body("SUCCESS");
    }

    @PatchMapping("/{userId}/change/profile-image")
    public ResponseEntity<?> update(@PathVariable("userId") Long userId,
                                    @RequestPart(name = "image") MultipartFile image) {
        memberService.updateImage(userId, image);
        return ResponseEntity.ok().body("SUCCESS");
    }

    @GetMapping("/{userId}/get-profile")
    public ResponseEntity<?> getProfile(@PathVariable("userId") Long userId) {
        Member member = memberService.findById(userId);
        ProfileResponseDto profileResponseDto = ProfileMapper.from(member);
        return ResponseEntity.ok().body(profileResponseDto);
    }
}
