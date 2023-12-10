package recamp.authenticationproject.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import recamp.authenticationproject.user.domain.Member;

public interface JpaMemberRepository extends JpaRepository<Member, Long> {
}
