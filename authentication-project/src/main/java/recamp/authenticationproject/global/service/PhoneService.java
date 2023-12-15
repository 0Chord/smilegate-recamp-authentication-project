package recamp.authenticationproject.global.service;

import org.springframework.transaction.annotation.Transactional;
import recamp.authenticationproject.global.redis.Phone;

@Transactional(readOnly = true)
public interface PhoneService {

    @Transactional
    void save(String number, String code);

    Phone findById(String number);

    Iterable<Phone> findAll();

    @Transactional
    void delete(Phone phone);
}
