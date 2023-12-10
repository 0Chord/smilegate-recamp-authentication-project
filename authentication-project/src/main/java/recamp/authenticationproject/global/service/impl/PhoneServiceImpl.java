package recamp.authenticationproject.global.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import recamp.authenticationproject.global.redis.Phone;
import recamp.authenticationproject.global.repository.PhoneRepository;
import recamp.authenticationproject.global.service.PhoneService;

@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PhoneServiceImpl implements PhoneService {

    private final PhoneRepository phoneRepository;

    @Override
    @Transactional
    public void save(String number, String code) {
        Phone phone = Phone.builder().number(number).token(code).expiration(5).build();
        phoneRepository.save(phone);
    }

    @Override
    public Phone findById(String number) {
        return phoneRepository.findById(number).orElseThrow();
    }

    @Override
    public Iterable<Phone> findAll() {
        return phoneRepository.findAll();
    }
}
