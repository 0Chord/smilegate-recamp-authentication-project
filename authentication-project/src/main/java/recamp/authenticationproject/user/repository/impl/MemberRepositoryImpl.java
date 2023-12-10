package recamp.authenticationproject.user.repository.impl;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import recamp.authenticationproject.user.domain.Member;
import recamp.authenticationproject.user.repository.JpaMemberRepository;
import recamp.authenticationproject.user.repository.MemberRepository;

@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberRepositoryImpl implements MemberRepository {
    private final JpaMemberRepository jpaMemberRepository;
    @Override
    @Transactional
    public void save(Member member) {
        jpaMemberRepository.save(member);
    }

    @Override
    public Optional<Member> findById(Long id) {
        return jpaMemberRepository.findById(id);
    }

    @Override
    public List<Member> findAll() {
        return jpaMemberRepository.findAll();
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return jpaMemberRepository.findByPersonalInformation_Email(email);
    }
}
