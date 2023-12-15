package recamp.authenticationproject.user.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import recamp.authenticationproject.user.domain.Member;

public interface JpaMemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByPersonalInformation_Email(String email);

    boolean existsByPersonalInformation_Email(String email);
}
