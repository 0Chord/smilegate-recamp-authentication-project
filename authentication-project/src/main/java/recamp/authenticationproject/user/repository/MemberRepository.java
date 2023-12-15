package recamp.authenticationproject.user.repository;

import java.util.List;
import java.util.Optional;
import recamp.authenticationproject.user.domain.Member;

public interface MemberRepository {

    void save(Member member);

    Optional<Member> findById(Long id);

    List<Member> findAll();

    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);
}
