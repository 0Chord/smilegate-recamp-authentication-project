package recamp.authenticationproject.global.repository;

import java.util.List;
import java.util.Optional;
import recamp.authenticationproject.global.redis.Phone;

public interface PhoneRepository {
    void save(Phone phone);

    Optional<Phone> findById(String number);

    Iterable<Phone> findAll();
}
