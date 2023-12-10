package recamp.authenticationproject.global.repository;

import org.springframework.data.repository.CrudRepository;
import recamp.authenticationproject.global.redis.Phone;

public interface PhoneRedisRepository extends CrudRepository<Phone, String> {
}
