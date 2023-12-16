package recamp.authenticationproject.user.service;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import recamp.authenticationproject.global.dto.MemberDto;
import recamp.authenticationproject.user.domain.Member;

@Validated
@Transactional(readOnly = true)
public interface MemberService {

    @Transactional
    void save(@Valid MemberDto memberDto);

    Member findById(Long id);

    List<Member> findAll();

    Member findMemberByEmail(String email);

    boolean existsEmail(String email);
}
