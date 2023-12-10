package recamp.authenticationproject.global.repository.impl;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import recamp.authenticationproject.global.redis.Phone;
import recamp.authenticationproject.global.repository.PhoneRedisRepository;
import recamp.authenticationproject.global.repository.PhoneRepository;

@RequiredArgsConstructor
public class PhoneRepositoryImpl implements PhoneRepository {

    private final PhoneRedisRepository phoneRedisRepository;

    @Override
    public void save(Phone phone) {
        phoneRedisRepository.save(phone);
    }

    @Override
    public Optional<Phone> findById(String number) {
        return phoneRedisRepository.findById(number);
    }

    @Override
    public Iterable<Phone> findAll() {
        return phoneRedisRepository.findAll();
    }
}
