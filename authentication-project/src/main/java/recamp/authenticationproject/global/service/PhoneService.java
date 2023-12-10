package recamp.authenticationproject.global.service;

import recamp.authenticationproject.global.redis.Phone;

public interface PhoneService {

    void save(String number, String code);

    Phone findById(String number);

    Iterable<Phone> findAll();
}
