package recamp.authenticationproject.user.service;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import recamp.authenticationproject.global.annotation.AuthorizeUser;
import recamp.authenticationproject.global.dto.ImageDto;
import recamp.authenticationproject.global.dto.MemberDto;
import recamp.authenticationproject.global.dto.PasswordDto;
import recamp.authenticationproject.user.domain.Member;

@Validated
@Transactional(readOnly = true)
public interface MemberService {

    @Transactional
    void save(@Valid MemberDto memberDto, MultipartFile image);

    Member findById(Long id);

    List<Member> findAll();

    Member findMemberByEmail(String email);

    boolean existsEmail(String email);

    @Transactional
    @AuthorizeUser
    void updatePassword(Long userId, @Valid PasswordDto passwordDto);

    @Transactional
    @AuthorizeUser
    void updateImage(Long userId, MultipartFile image);
}