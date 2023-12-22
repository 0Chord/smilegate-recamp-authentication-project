package recamp.authenticationproject.user.controller;

import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import recamp.authenticationproject.global.dto.MemberDto;
import recamp.authenticationproject.global.dto.PasswordDto;
import recamp.authenticationproject.user.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class MemberController {
private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestPart(value = "memberDto") @Validated MemberDto memberDto,
                                       @RequestPart(name = "image", required = false)
                                       MultipartFile image) {
        System.out.println("memberDto = " + memberDto);
        memberService.save(memberDto, image);
        return ResponseEntity.ok().body("SUCCESS");
    }

    @PostMapping("/{userId}/change/password")
    public ResponseEntity<?> change(@PathVariable("userId") Long userId,
                                    @RequestBody @Validated PasswordDto passwordDto) {
        memberService.updatePassword(userId, passwordDto);
        return ResponseEntity.ok().body("SUCCESS");
    }
}
