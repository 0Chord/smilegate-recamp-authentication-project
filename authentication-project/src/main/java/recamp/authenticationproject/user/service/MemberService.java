package recamp.authenticationproject.user.service;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import recamp.authenticationproject.global.dto.MemberDto;
import recamp.authenticationproject.user.domain.Member;

@Validated
public interface MemberService {

    void save(@Valid MemberDto memberDto);

    Member findById(Long id);

    List<Member> findAll();

    Member findMemberByEmail(String email);
}
