package recamp.authenticationproject.user.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import recamp.authenticationproject.user.domain.Member;
import recamp.authenticationproject.user.domain.Role;

public interface JpaMemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByPersonalInformation_Email(String email);

    boolean existsByPersonalInformation_Email(String email);

    @Query("select m from Member m where m.role = :role")
    List<Member> findUserMember(Role role);
}
