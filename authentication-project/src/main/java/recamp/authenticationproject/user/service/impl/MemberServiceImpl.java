package recamp.authenticationproject.user.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;
import recamp.authenticationproject.global.dto.MemberDto;
import recamp.authenticationproject.global.dto.PasswordDto;
import recamp.authenticationproject.global.exception.NotFoundUserException;
import recamp.authenticationproject.global.service.ImageService;
import recamp.authenticationproject.global.utility.Validator;
import recamp.authenticationproject.user.domain.Member;
import recamp.authenticationproject.user.domain.PersonalInformation;
import recamp.authenticationproject.user.domain.Role;
import recamp.authenticationproject.user.repository.MemberRepository;
import recamp.authenticationproject.user.service.MemberService;

@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;
    private final ImageService imageService;
    @Value("${cloud.storage.url}")
    private String url;

    private static Role getRole(MemberDto memberDto) {
        if (memberDto.getRole().equals("USER")) {
            return Role.USER;
        }
        return Role.ADMIN;
    }

    @Override
    public void save(MemberDto memberDto, MultipartFile image) {
        Validator.comparePasswords(memberDto.getPassword(), memberDto.getValidationPassword());
        PersonalInformation information = PersonalInformation.make(memberDto.getEmail(), memberDto.getName(),
                memberDto.getPhone());
        String encodingPassword = encoder.encode(memberDto.getPassword());
        memberDto.changeRole();
        Role role = getRole(memberDto);
        String imageUrl = imageService.convertImage(image);
        Member member = Member.builder()
                .information(information)
                .password(encodingPassword)
                .role(role)
                .verified(memberDto.isVerified())
                .imageUrl(url + imageUrl)
                .build();
        memberRepository.save(member);
    }

    @Override
    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow(NotFoundUserException::new);
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Member findMemberByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(NotFoundUserException::new);
    }

    @Override
    public boolean existsEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

    @Override
    public void updatePassword(Long userId, PasswordDto passwordDto) {
        String newPassword = passwordDto.getPassword();
        String password = encoder.encode(newPassword);
        Member member = memberRepository.findById(userId).orElseThrow();
        member.updatePassword(password);
    }

    @Override
    public void updateImage(Long userId, MultipartFile image) {
        Member member = memberRepository.findById(userId).orElseThrow(NotFoundUserException::new);
        String imageUrl = imageService.convertImage(image);
        member.updateImage(url + imageUrl);
    }

}
